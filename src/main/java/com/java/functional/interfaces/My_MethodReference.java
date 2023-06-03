package com.java.functional.interfaces;

interface Sayable {
	int say();

}

interface Sayable2 {

	Sample obj(String msg);
}

class Sample {

	Sample(String msg) {
		System.out.println(msg);
	}

	public static int show() {

		return 10;
	}
}

public class My_MethodReference {

	public static void main(String[] args) {

		Sayable i = Sample::show;
		int a = i.say();
		System.out.println(a);

		Sayable2 s = Sample::new;
		s.obj("venkat");
	}
}
