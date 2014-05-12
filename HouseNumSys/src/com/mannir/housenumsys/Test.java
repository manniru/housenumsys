package com.mannir.housenumsys;

import static org.junit.Assert.*;

import java.util.List;

public class Test {
	Db db;
	
	public Test() {
		db = new Db("sqlite","localhost","admin","13131","housenumsys");
		db.connect();

	}


	@org.junit.Test
	public void test() {
		assertEquals(1,1);
		
	}

	@org.junit.Test
	public void testAdd() {
		Db db = new Db("sqlite","localhost","admin","13131","housenumsys");
		db.connect();
		
		//String id = 1;
		int houseno = 100;
		String lganame = "Gwarzo";
		String district = "Kofar Gabas";
		String ward = "Zango";
		String street = "Bakin titi";
		String holdername = "Alhaji Ibrahim";
		String holderaddress = "No 3, Karaye Road, Kano";
		String mobileno = "2348069354904";
		String nationality = "Nigerian";
		String landuse = "Residential";
		String date = "04-24-2014";
		
		House house = new House();
		house.setHouseno(houseno);
		house.setLganame(lganame);
		house.setDistrict(district);
		house.setWard(ward);
		//house.setStreet(street);
		house.setHoldername(holdername);
		house.setHolderaddress(holderaddress);
		house.setMobileno(mobileno);
		house.setNationality(nationality);
		house.setLanduse(landuse);
		house.setDate(date);
		db.addHouse(house);
		System.out.println("Record Added!");
		

		
		//assertEquals(house2.getHouseno(),houseno);
	}
	
	@org.junit.Test
	public void testGet() {
		House house2 = db.getHouse(100);
		System.out.println(house2.getHoldername());
	}
	
	@org.junit.Test
	public void testListHouse() {
		List<House> houses = db.listHouse();
		for(House hs: houses) { System.out.println(hs.getId()); }
	}

}
