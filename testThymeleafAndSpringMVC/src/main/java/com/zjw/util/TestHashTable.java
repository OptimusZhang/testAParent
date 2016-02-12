package com.zjw.util;

import java.util.Hashtable;

public class TestHashTable {
	
	public String name;
	
	public static void main(String[] args){
		TestHashTable test = new TestHashTable();
		test.name = "tom";
		
		Hashtable<String , TestHashTable> ht = new Hashtable<String , TestHashTable>();
		ht.put("key", test);
		System.out.println("1" + ht.get("key").name);
		
		test.name = "cat";
		System.out.println("2" + ht.get("key").name);
	}
}

