package com.java.functional.interfaces;

import java.util.function.BiPredicate;

public class My_BiPredicate {

	public static void main(String[] args) {

		BiPredicate<Integer, Integer> predicate = (a, b) -> {
			return a > b;
		};
		
	boolean b=	predicate.test(10, 20);
	
	System.out.println(b);

	}

}
