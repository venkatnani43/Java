package com.java.functional.interfaces;

import java.util.function.Predicate;

public class My_Predicate {

	public void way3(Predicate<Integer> result) {

		System.out.println(result.test(10));

	}

	public Predicate<Integer> way() {

		return x -> x > 5;

	}

	public static void main(String[] args) {
		My_Predicate m = new My_Predicate();
		m.way3(x -> x > 5);

		Predicate<Integer> pre = m.way();
		System.out.println(pre.test(10));

	}
}
