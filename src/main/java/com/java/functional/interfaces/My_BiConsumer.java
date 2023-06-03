package com.java.functional.interfaces;

import java.util.function.BiConsumer;

public class My_BiConsumer {

	public static void main(String[] args) {

		BiConsumer<Integer, Integer> consumer = (a, b) -> {
			System.out.println(a + b);
		};

		consumer.accept(10, 20);
	}
}