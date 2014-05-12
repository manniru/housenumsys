package com.mannir.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;


public class StudentDAO {
	private static Connection c1 = null;
	
	public static Connection c1() {
		try {
	    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	  //  c1 = DriverManager.getConnection("jdbc:mysql://97.74.31.15/ikcoeapp", "ikcoeapp", "Mannir@13131");
	    c1 = DriverManager.getConnection("jdbc:mysql://localhost/ikcoereg", "root", "");
	    if(!c1.isClosed())
	        System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
		} catch (Exception e) { System.out.println(e); }
		
		return c1;
	}
	
	public Connection  c2() { Connection c = null; //Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
	try{ Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); System.out.println("Loaded Derby Driver");	
	}catch(Exception e){ e.printStackTrace(); System.out.println("Error loading Derby Driver. Shutting down."); } 
	String database = "jdbc:derby:ikcoedb;create=false;user=admin;password=13131";
	 try{ c = DriverManager.getConnection(database);
	 System.out.println("Connected to "+database);
	 }catch(SQLException e){ e.printStackTrace(); System.out.println("Could not connect to "+database); }
	 System.out.println("You have connect to the selected embedded database.");	 
	 return c; }
	
	public static void addStudent(Student s) {
		int id = s.getId();
		String name = s.getName();
		String address = s.getAddress();
		String sql = "INSERT INTO STUDENT(name,address) VALUES('"+name+"','"+address+"')";
		try { c1().createStatement().execute(sql);
		} catch (Exception ex) { System.err.println(ex.getMessage()); }
	}
	
	public void editStudent(Student s) {
		String sql = "UPDATE STUDENT SET NAME='" + s.getName() + "'";
	}
	
	public void deleteStudent(Student s) {
		String sql = "DELETE FROM STUDENT WHERE ID=" + s.getId() +"";
	}
	
	public Student getStudent(String id) {
		Student s = new Student();
		
		try { ResultSet rs = c1().createStatement().executeQuery("SELECT * FROM STUDENT WHERE ID=" + id + "'");
			
			if (rs.next()) {
				s.setId(Integer.parseInt(rs.getString("ID")));
				s.setName(rs.getString("NAME"));
				s.setAddress(rs.getString("ADDRESS"));
			}
			
		} catch (Exception ex) { System.err.println(ex.getMessage()); }
		
		return s;
	}
	
	public ArrayList<Student> getAllStudent() {
		ArrayList<Student> list = new ArrayList<Student>();
		String sql = "SELECT * FROM STUDENT";
		
		
		try { ResultSet rs = c1().createStatement().executeQuery(sql);
			while(rs.next()) {
				Student student = new Student();
				student.setId(Integer.parseInt(rs.getString("ID")));
				student.setName(rs.getString("NAME"));
				student.setAddress(rs.getString("ADDRESS"));
				list.add(student);
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			
		}
		
		return list;
	}

}
