package com.java.functional.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface MySupplier<T> {

	public T get();

}

public class My_Supplier {

	public void factor1(MySupplier<? extends Number> supplier) {

		System.out.println(supplier.get());
	}

	public static void factor2(MySupplier<String> supplier) {

		System.out.println(supplier.get());
	}

	public void factor3(MySupplier<? extends Student> s) {

		Student student = s.get();
		System.out.println(student.getName());
	}

	public MySupplier<? extends Student> factory4() {

		return Student::new;
	}

	public MySupplier<? extends Student> factory5() {

		return () -> new Student() {
			{
				setId(101);
				setName("venkat");
			}
		};
	}

	public static Student display2() {

		return new Student() {
			{

				setId(101);
				setName("venkat");

			}
		};
	}

	public static List<Student> dispaly() {

		List<Student> list = new ArrayList<Student>();
		list.add(new Student() {
			{
				setId(101);
				setName("krishna");
			}
		});

		return list;

	}

	public void mymethodReference() {

		MySupplier<Student> s = My_Supplier::display2;
		Student s2 = s.get();
		System.out.println(s2.getName());

		MySupplier<List<Student>> ss = My_Supplier::dispaly;
		List<Student> list = ss.get();
		System.out.println(list);

	}

	public static void main(String[] args) {

	}

	public void show() {

		factor1(() -> 6);
		factor1("venkat"::length);

		factor2(() -> {
			return "im working suppliers";

		});

		factor3(() -> new Student() {
			{
				setId(101);
				setName("venkat");
			}
		});

		MySupplier<? extends Student> suppiler = factory5();
		Student s = suppiler.get();
		System.out.println(s.getId());

		MySupplier<? extends Student> suppiler2 = factory4();
		Student s2 = suppiler2.get();

	}

	@SuppressWarnings("serial")
	public void way4() {
		MySupplier<Map<Integer, List<Student>>> supplier = () -> {

			return new HashMap<Integer, List<Student>>() {

				{

					put(1, new ArrayList<Student>() {
						{
							add(new Student() {
								{
									setId(101);
									setName("krishna");
								}
							});

						}
					});

				}
			};

		};

		Map<Integer, List<Student>> result = supplier.get();

		for (Map.Entry<Integer, List<Student>> m : result.entrySet()) {
			System.out.println(m.getKey() + "," + m.getValue().get(0).getName());
		}
	}

	public void way3() {
		MySupplier<List<Student>> supplier = () -> {

			List<Student> list = new ArrayList<Student>();
			list.add(new Student() {
				{
					setId(101);
					setName("krishna");
				}
			});

			return list;

		};

		List<Student> resultList = supplier.get();

		for (Student s : resultList) {
			System.out.println(s.getName());
		}
	}

	public void way2() {

		MySupplier<Student> student = () -> {

			return new Student() {
				{
					setId(101);
					setName("venkat");

				}
			};
		};

		Student s = student.get();

		System.out.println(s.getName());
	}

	public void way1() {
		MySupplier<String> supplier = () -> {
			return "im working suppliers";

		};

		String result = supplier.get();
		System.out.println(result);
	}

	public void basic() {

		MySupplier<String> supplier = new MySupplier<String>() {

			@Override
			public String get() {
				return "im working on supplier";
			}
		};

		String result = supplier.get();
		System.out.println(result);

	}

}
