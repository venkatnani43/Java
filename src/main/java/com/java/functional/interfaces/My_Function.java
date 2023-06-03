package com.java.functional.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;



interface MyFunction<T, R> {
	R apply(T t);
}

public class My_Function {

	@SuppressWarnings("serial")
	public void show1(MyFunction<List<Student>, List<Student>> func) {

		List<Student> result = func.apply(new ArrayList<Student>() {
			{
				add(new Student() {
					{
						setId(101);
						setName("venkat");
					}
				});
			}
		});

		for (Student a : result) {
			System.out.println(a.getName());
		}
	}

	public void show2(MyFunction<String, Integer> func) {

		int len = func.apply("venkat");

		System.out.println(len);

	}

	public MyFunction<String, Integer> show3() {

		return (String s) -> s.length();

	}

	public static Integer show4(String s) {

		return s.length();

	}

	public static List<Student> show6(Student[] student) {

		List<Student> list = new ArrayList<>();

		for (Student s : student) {
			s.setName(s.getName().toUpperCase());
			list.add(s);
		}

		return list;

	}

	public static void display2() {

		Function<Student[], List<Student>> func3 = My_Function::show6;

		List<Student> res = func3.apply(new Student[] { new Student() {
			{
				setId(101);
				setName("venkat");
			}
		}, new Student() {
			{
				setId(102);
				setName("krishna");
			}
		} });

		for (Student s : res) {
			System.out.println(s.getName());
		}
		

		Function<String, Integer> func2 = My_Function::show4;
		int result = func2.apply("cherry thalli");
		System.out.println(result);

	}

	public void display() {

		MyFunction<String, Integer> func = show3();
		int len = func.apply("nani");
		System.out.println(len);

		show2((String s) -> s.length());

		show1((List<Student> list) -> {

			for (Student s : list) {

				s.setName(s.getName().toUpperCase());

			}
			return list;
		});

	}

	public static void main(String[] args) {

		My_Function t = new My_Function();
		t.display2();

	}

	@SuppressWarnings("serial")
	public void way2() {

		MyFunction<List<Student>, List<Student>> func = (List<Student> list) -> {

			for (Student s : list) {

				s.setName(s.getName().toUpperCase());

			}
			return list;
		};

		List<Student> result = func.apply(new ArrayList<Student>() {
			{
				add(new Student() {
					{
						setId(101);
						setName("venkat");
					}
				});
			}
		});

		for (Student a : result) {
			System.out.println(a.getName());
		}
	}

	public void way1() {

		MyFunction<String, Integer> func = (String s) -> {
			return s.length();
		};

		int len = func.apply("venkat");

		System.out.println(len);

	}

	public void basic() {

		MyFunction<String, Integer> func = new MyFunction<String, Integer>() {

			@Override
			public Integer apply(String t) {

				return t.length();
			}
		};

		int len = func.apply("venkat");

		System.out.println(len);
	}

}
