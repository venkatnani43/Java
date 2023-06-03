package com.java.functional.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface MyConsumer<T> {

	public void accept(T t);
}

public class My_Consumer {

	public void show1(MyConsumer<Integer> consumer, Integer i) {

		consumer.accept(i);
	}

	public void show2(MyConsumer<String> consumer, String str) {

		consumer.accept(str);
	}

	public void show3(MyConsumer<Student> consumer, Student student) {
		consumer.accept(student);

	}

	public void show4(MyConsumer<List<Student>> consumer, List<Student> list) {

		consumer.accept(list);

	}

	public void show5(MyConsumer<Map<Integer, Student>> consumer, Map<Integer, Student> map) {

		consumer.accept(map);
	}

	public MyConsumer<List<Student>> show6() {

		MyConsumer<List<Student>> consumer = (List<Student> list) -> {

			for (Student s : list) {
				System.out.println(s.getId() + "," + s.getName());
			}
		};

		return consumer;

	}

	public static void main(String[] args) {

		My_Consumer c = new My_Consumer();
		MyConsumer<List<Student>> consumer = c.show6();

		List<Student> list = new ArrayList<>();
		list.add(new Student() {
			{
				setId(101);
				setName("venkat");
			}
		});
		list.add(new Student() {
			{
				setId(102);
				setName("krishna");
			}
		});
		consumer.accept(list);
	}

	public static void show6(String msg) {

		System.out.println(msg);

	}

	public void way6() {
		MyConsumer<String> consumer = My_Consumer::show6;
		consumer.accept("venkat");
	}

	public static void show7(Map<Integer, Student> map) {

		for (Map.Entry<Integer, Student> m : map.entrySet()) {

			System.out.println(m.getKey() + "," + m.getValue().getName());
		}
	}

	public void way7() {

		MyConsumer<Map<Integer, Student>> consumer = My_Consumer::show7;

		Map<Integer, Student> map = new HashMap<>();
		map.put(1, new Student() {
			{
				setId(101);
				setName("venkat");
			}
		});
		map.put(2, new Student() {
			{
				setId(101);
				setName("krishna");
			}
		});

		consumer.accept(map);
	}

	public void way1() {
		MyConsumer<Integer> consumer = (Integer i) -> {
			System.out.println(i);
		};

		// consumer.accept(10);

		show1(consumer, 10);

	}

	public void way2() {
		MyConsumer<String> consumer = (str) -> {
			System.out.println(str);
		};

		// consumer.accept("venkat");

		show2(consumer, "venkat");
	}

	public void way3() {

		MyConsumer<Student> consumer = (Student stu) -> {

			System.out.println(stu.getName() + "," + stu.getId());
		};

		Student student = new Student() {
			{
				setId(101);
				setName("venkat");
			}
		};

		consumer.accept(student);

		show3(consumer, student);

	}

	public void way4() {
		MyConsumer<List<Student>> consumer = (List<Student> list) -> {

			for (Student s : list) {
				System.out.println(s.getId() + "," + s.getName());
			}
		};

		List<Student> list = new ArrayList<>();
		list.add(new Student() {
			{
				setId(101);
				setName("venkat");
			}
		});
		list.add(new Student() {
			{
				setId(102);
				setName("krishna");
			}
		});

		// consumer.accept(list);

		show4(consumer, list);

	}

	public void way5() {
		MyConsumer<Map<Integer, Student>> consumer = (Map<Integer, Student> map) -> {

			for (Map.Entry<Integer, Student> m : map.entrySet()) {

				System.out.println(m.getKey() + "," + m.getValue().getName());
			}
		};

		Map<Integer, Student> map = new HashMap<>();
		map.put(1, new Student() {
			{
				setId(101);
				setName("venkat");
			}
		});
		map.put(2, new Student() {
			{
				setId(101);
				setName("krishna");
			}
		});

		// consumer.accept(map);

		show5(consumer, map);
	}

	public void way77() {

		MyConsumer<String> consumer = new MyConsumer<String>() {

			@Override
			public void accept(String str) {

				System.out.println(str);
			}
		};

		consumer.accept("working on lambda");

	}

}
