package com.java.functional.interfaces;

import java.util.function.BiFunction;

public class My_BiFunction {

	public static void main(String[] args) {

		BiFunction<Integer, Integer, Integer> function = (a, b) -> {

			return a + b;
		};

		int sum = function.apply(10, 20);
		System.out.println(sum);

	}

}
