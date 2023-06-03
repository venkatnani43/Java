package com.java.functional.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.java.model.OrderLineItemsDto;

public class Test {
	
	private Predicate<String>pre=(str)->"venkat".contains(str);

	private Function<String, Integer> func = (str) -> str.length();
	
	
	
	

	private Consumer<String> cons = (str) -> System.out.println(str);

	private Supplier<Integer> sup = () -> "venkat".length();
	
	
	
	public void perform(Function<String, Integer> funcc) {

		int length = funcc.apply("venkat");
		System.out.println(length);
	}

	public Function<String, Integer> perform2() {

		return func;
	}

	public void perform3(Consumer<String> conss) {

		conss.accept("venkat");
	}

	public Consumer<String> perform4() {

		return cons;
	}

	public void perform5(Supplier<Integer> supp) {

		int length = supp.get();
		System.out.println(length);
	}

	public Supplier<Integer> perform6() {

		return sup;
	}
	public void show() {
		perform(func);
		int length = perform2().apply("krishna");
		System.out.println(length);
	}

	public static void main(String[] args) {

		Test t=new Test();t.test();
		
	}
	
	public void test() {
		 Function<String, Predicate<OrderLineItemsDto>> func2 = str-> (orderitem) -> orderitem.getProductname().endsWith(str);

		String productname="123";
		
		List<OrderLineItemsDto> list=new ArrayList<>();
		
		OrderLineItemsDto d1=new OrderLineItemsDto();
		d1.setId(101l);
		d1.setQuantity(2);
		d1.setProductname("apple123");
		OrderLineItemsDto d2=new OrderLineItemsDto();
		d2.setId(101l);
		d2.setQuantity(2);
		d2.setProductname("apple123");
		
		OrderLineItemsDto d3=new OrderLineItemsDto();
		d3.setId(101l);
		d3.setQuantity(2);
		d3.setProductname("apple");
		
		list.add(d1);
		list.add(d2);
		list.add(d3);
		
		//OrderLineItemsDto resp=list.stream().filter(str->str.getProductname().endsWith(productname)).findFirst().orElse(null);
		
		OrderLineItemsDto resp=list.stream().filter(func2.apply(productname)).findFirst().orElse(null);
		System.out.println(resp);
		
		
		
	}


}
