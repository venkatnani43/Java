package com.java.functional.interfaces;

interface Connections {

	public String connection();
}

public class My_Interface {

	public void way1() {

		Connections i = new Connections() {

			@Override
			public String connection() {
				return "Oracle connection object";

			}
		};

		System.out.println(i.connection());
	}

	public void way2() {

		Connections i = () -> {
			return "mysql connection object";
		};

		System.out.println(i.connection());
	}
	
	public void way3() {

		Connections i = () -> "sql connection object";
	

		System.out.println(i.connection());
	}

	
	public static void main(String[] args) {
		My_Interface t = new My_Interface();
		t.way3();

	}

}
