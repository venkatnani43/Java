package com.java.functional.interfaces;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class My_Class {

	private int id;
	private String name;

	public void way1() {

		My_Class t = new My_Class() {
			{
				setId(12);
				setName("venkat");
			}
		};

		System.out.println(t.getId() + "\t" + t.getName());
	}

	

	public static void main(String[] args) {

		My_Class t = new My_Class();
		t.way1();
		
	}
}
