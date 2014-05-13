package com.mannir.housenumsys;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Db {
	// db handler
	Db db;
	private String dbtype, dbhost, dbuser, dbpass, dbname;
	private static Connection cn = null;

	public Connection connect() {
		if(dbtype.equals("sqlite")) {
			try {  Class.forName("org.sqlite.JDBC");
		      cn = DriverManager.getConnection("jdbc:sqlite:housenumsys.db");
		      System.out.println("Sqlite database successfully connected!");
	
		} catch (Exception e) { System.out.println(e); }
		
		}
		else {
		try { Class.forName("com.mysql.jdbc.Driver").newInstance();
			cn = DriverManager.getConnection("jdbc:mysql://"+dbhost+"/"+dbname, dbuser, dbpass);
			if(!cn.isClosed()) System.out.println("connected to Mysql");
		} catch (Exception e) { System.out.println(e); }
		}
		return cn;
	}

	public Db() {
		db = new Db("sqlite","localhost","admin","13131","housenumsys");
		db.connect();
	}
	public static void main(String[] args) {
		Db db = new Db("sqlite","localhost","admin","13131","housenumsys");
		db.connect();
		
		House hs = db.getHouse(5);
		System.out.println(hs.getHouseno());
		hs.setHouseno(100);
		db.editHouse(hs);
		System.out.println(hs.getHouseno());


		
	}
	

	public void listFields(String tb) {
		Db db = new Db("sqlite","localhost","admin","13131","payrolldb");
		db.connect();
		
		String[] st = db.getColumns(tb);
		for(String s: st) {
			System.out.println("public int "+s.toLowerCase()+";");
		}
	}
	

	
	public Db(String dbtype,String dbhost, String dbuser, String dbpass, String dbname) {
		this.dbtype = dbtype;
		this.dbhost = dbhost;
		this.dbuser = dbuser;
		this.dbpass = dbpass;
		this.dbname = dbname;
		///this.connect();
	}
	
	public static String[] getColumns(String tb) {
		String[] simpleArray = null;
		   try { Statement st = cn.createStatement(); 
		   	ResultSet rs = st.executeQuery("SELECT * FROM "+tb);
		     ResultSetMetaData md = rs.getMetaData();
		     int columns = md.getColumnCount();     
		     List<String> where = new ArrayList();
		     for (int i = 1; i <= columns; i++) {where.add(md.getColumnName(i)); }
		     simpleArray = new String[where.size()];
		     where.toArray(simpleArray);
		   }
		   catch (Exception e1) { System.out.println(e1); }
		   return simpleArray;
	}
	
	public static Object[][] getData(String tb) {
		Object[][] oo = null;
	
		try {
		Statement st2 = cn.createStatement();
		ResultSet r3 = st2.executeQuery("SELECT * FROM "+tb);
		ResultSetMetaData metaData = r3.getMetaData();
		int colCount = metaData.getColumnCount();
		ArrayList rows = new ArrayList();
		Object[] row = null;
		while (r3.next()) {
		row = new Object[colCount];
		for (int a = 0; a < colCount; a++) {
		row[a] = r3.getObject(a + 1);
		}
		rows.add(row);
		}
		oo = (Object[][])rows.toArray(new Object[0][0]);
		} catch(Exception e3) { System.out.println("getObjects()"+e3);
		
		}
		return oo;
	}
	
	public void runSql(Connection cn, String sql) {
		try {
		Statement st = cn.createStatement();
		st.execute(sql);
		System.out.println(sql);
		} catch(Exception e) { System.out.println(e); }
		
	}
	
	public void save(Object obj) {
		String tb = obj.getClass().getSimpleName().toLowerCase();
		try {
		Field[] fl = Class.forName(obj.getClass().getName()).getFields();
		//System.out.println(s.getName()); 
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO "+tb + "(");
		int n1=1;
		for(Field s : fl) { if(n1>1) { sb.append(s.getName()+","); }
			n1++;
		}
	
		sb.append(")");
		sb.deleteCharAt(sb.length()-2);
		sb.append(" VALUES(");
		for(int a=1;a<=n1-2;a++) { sb.append("?,"); }
		sb.deleteCharAt(sb.length()-1).toString();
		sb.append(")");
		
		String sql = sb.toString(); //"INSERT INTO "+tb + "(pin, type, amount) VALUES" + "(?,?,?)";
		System.out.println(sql);
		PreparedStatement ps = cn.prepareStatement(sql);
		
		int n=1;
		for(Field s : fl) {
			
			if(n>1) {
			String nm = s.getName();
			String vl = s.get(obj).toString();
			System.out.println(nm+"="+vl);
			try {ps.setString(n-1, vl);} catch(Exception e1) { ps.setInt(n-1, 1);  System.out.println("m1:"+e1); } 
			}
			n++;
		}


		ps .executeUpdate();
	
		} catch(Exception e) { System.out.println("m2:"+e); }
	//catch(NullPointerException ne) { System.out.println("m2:"+ne); }
		
	//	String sql = "INSERT INTO ADMIN.EMPLOYEE(empid, forceno, apfno, firstname, surname, rank, steps, amount, bank, accno) VALUES("+
	//e.getEmpid()+","+e.getForceno()+",'"+e.getApfno()+"','"+e.getFirstname()+"','"+e.getSurname()+"','"+e.getRank()+"','"+
	//			e.getSteps()+"',"+e.getAmount()+",'"+e.getBank()+"','"+e.getAccno()+"')";
		//try { cn.createStatement().execute(sql); } catch (Exception ex) { System.err.println(ex.getMessage()); }
	}
	
	public Object get(Object obj, int id) {
		String tb = obj.getClass().getSimpleName().toLowerCase();

		try {
			PreparedStatement ps = cn.prepareStatement("SELECT * FROM "+tb.toUpperCase()+" WHERE ID = ?");
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
		Field[] fl = Class.forName(obj.getClass().getName()).getFields();
		for(Field f : fl) {
			//System.out.println(f.getName());
			if(f.getType().toString().equals("int")) { set(obj, f.getName(), rs.getInt(f.getName())); }
			else { set(obj, f.getName(), rs.getString(f.getName())); }
		}
		} catch(Exception e) { System.out.println(e); }
		return obj;
	}
	
	public static boolean set(Object object, String fieldName, Object fieldValue) {
	    Class<?> clazz = object.getClass();
	    while (clazz != null) {
	        try {
	            Field field = clazz.getDeclaredField(fieldName);
	            field.setAccessible(true);
	            field.set(object, fieldValue);
	            return true;
	        } catch (NoSuchFieldException e) {
	            clazz = clazz.getSuperclass();
	        } catch (Exception e) {
	            return false;
	        }
	    }
	    return false;
	}
	
	public LinkedHashMap getEmp(int id) {
		LinkedHashMap mp = new LinkedHashMap();
		try { Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE WHERE ID="+id); rs.next();						
			ResultSetMetaData md = rs.getMetaData();
			int col = md.getColumnCount();		
			for (int a = 1; a < col; a++) {
				mp.put(md.getColumnName(a), rs.getObject(a).toString());
			} } catch(Exception e3) { System.out.println(e3);			
		} return mp;
	}
	

	
	public void query(Connection cn, String sql) {
		try {
		Statement st = cn.createStatement();
		st.execute(sql);
		System.out.println(sql);
		} catch(Exception e) { System.out.println(e); }
		
	}
	
	public List<String> gets(String tb) {
		List<String> str = new ArrayList<String>();		
		try { Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT "+tb+" FROM SC ORDER BY "+tb); rs.next();
			while (rs.next()) { str.add(rs.getString(1)); }
		} catch(Exception e3) { System.out.println(e3);			
		} return str;
	}

	public ArrayList<String> getBanks() {
		ArrayList<String> list = new ArrayList<String>();
		try { ResultSet rs = cn.createStatement().executeQuery("SELECT DISTINCT BANK FROM EMPLOYEE ORDER BY BANK");
		while(rs.next()) {
			System.out.println(rs.getString("BANK"));
			list.add(rs.getString("BANK")); }
		} catch(Exception e) { System.out.println(e); }
		return list;
		
	}
	
	public static void display(Object obj) {
		for (Field field : obj.getClass().getDeclaredFields()) {
		    field.setAccessible(true);
		    String name = "", value = "";
		    name = field.getName();
		    try { value = (String) field.get(obj); } catch (Exception e) { 
			    try { value = ""+(Integer) field.get(obj); } catch (Exception e1) {}

		    }
		    System.out.println(name+"="+value);
		}
		
	}
	
	public static int getTotalDd(int empid) {
		try { Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT SUM(amount) AS TOTAL FROM DEDUCTION WHERE EID="+empid); rs.next();
			return Integer.parseInt(rs.getString("TOTAL"));
		} catch(Exception e3) { System.out.println(e3); return 0; }
	}

	public static Object[][] getData(String tb, int start, int end) {
		Object[][] oo = null;
	
		try {
		Statement st2 = cn.createStatement();
		ResultSet r3 = st2.executeQuery("SELECT * FROM "+tb+" WHERE id>="+start+" AND id<="+end);
		ResultSetMetaData metaData = r3.getMetaData();
		int colCount = metaData.getColumnCount();
		ArrayList rows = new ArrayList();
		Object[] row = null;
		while (r3.next()) {
		row = new Object[colCount];
		for (int a = 0; a < colCount; a++) {
		row[a] = r3.getObject(a + 1);
		}
		rows.add(row);
		}
		oo = (Object[][])rows.toArray(new Object[0][0]);
		} catch(Exception e3) { System.out.println("getObjects()"+e3);
		
		}
		return oo;
	}
		
	public void addHouse(House house) {
        try {
            PreparedStatement preparedStatement = cn.prepareStatement("insert into house(houseno, lganame, district, ward, street, holdername, holderaddress, mobileno, nationality, landuse, date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setInt(1, house.getHouseno());
            preparedStatement.setString(2, house.getLganame());
            preparedStatement.setString(3, house.getDistrict());            
            preparedStatement.setString(4, house.getWard());            
            //preparedStatement.setString(5, house.getStreet());            
            preparedStatement.setString(6, house.getHoldername());            
            preparedStatement.setString(7, house.getHolderaddress());            
            preparedStatement.setString(8, house.getMobileno());            
            preparedStatement.setString(9, house.getNationality());            
            preparedStatement.setString(10, house.getLanduse());            
            preparedStatement.setString(11, house.getDate());            
            //preparedStatement.setDate(4, new java.sql.Date(user.getRegisteredon().getTime()));
            preparedStatement.executeUpdate();
            System.out.println("House Saved!");

        } catch (SQLException e) { e.printStackTrace(); }
	}
	
    public House getHouse(int id) {
    	House house = new House();
        try {
            PreparedStatement preparedStatement = cn.prepareStatement("select * from house where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
            	house.setId(id);
            	house.setHouseno(rs.getInt("houseno"));
            	house.setLganame(rs.getString("lganame"));
            	house.setDistrict(rs.getString("district"));
            	house.setWard(rs.getString("ward"));
            	//house.setStreet(rs.getString("street"));
            	house.setHoldername(rs.getString("holdername"));
            	house.setHolderaddress(rs.getString("holderaddress"));
            	house.setMobileno(rs.getString("mobileno"));
            	house.setNationality(rs.getString("nationality"));
            	house.setLanduse(rs.getString("landuse"));
            	house.setDate(rs.getString("date"));
            }
        } catch (SQLException e) { e.printStackTrace(); }

        return house;
    }
    
    public List<House> listHouse() {
        List<House> houses = new ArrayList<House>();
        try {
            Statement statement = cn.createStatement();
            ResultSet rs = statement.executeQuery("select * from house");
            while (rs.next()) {
            	House house = new House();
            	house.setId(rs.getInt("id"));
            	house.setHouseno(rs.getInt("houseno"));
            	house.setLganame(rs.getString("lganame"));
            	house.setDistrict(rs.getString("district"));
            	house.setWard(rs.getString("ward"));
            	//house.setStreet(rs.getString("street"));
            	house.setHoldername(rs.getString("holdername"));
            	house.setHolderaddress(rs.getString("holderaddress"));
            	house.setMobileno(rs.getString("mobileno"));
            	house.setNationality(rs.getString("nationality"));
            	house.setLanduse(rs.getString("landuse"));
            	house.setDate(rs.getString("date"));
            	houses.add(house);
            }
        } catch (SQLException e) { e.printStackTrace();}

        return houses;
    }

    public void delHouse(int id) {
        try {
            PreparedStatement preparedStatement = cn.prepareStatement("delete from house where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Record deleted!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void editHouse(House house) {
        try {
            PreparedStatement ps = cn.prepareStatement("update house set houseno=?, lganame=?, district=?"+
        ", ward=?, street=?, holdername=?, holderaddress=?, mobileno=?, nationality=?, landuse=?, date=?"
                    + "where id=?");
            ps.setInt(1, house.getHouseno());
            ps.setString(2, house.getLganame());
            ps.setString(3, house.getDistrict());
            ps.setString(4, house.getWard());
            //ps.setString(5, house.getStreet());
            ps.setString(6, house.getHoldername());
            ps.setString(7, house.getHolderaddress());
            ps.setString(8, house.getMobileno());
            ps.setString(9, house.getNationality());
            ps.setString(10, house.getLanduse());
            ps.setString(11, house.getDate());
            ps.setInt(12, house.getId());
            ps.executeUpdate();
            System.out.println("record updated");
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
