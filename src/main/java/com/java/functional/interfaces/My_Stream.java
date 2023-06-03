package com.java.functional.interfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class My_Stream {

	public void sort_Integer_list() {

		List<Integer> list = new ArrayList<>();
		list.add(7);
		list.add(4);
		list.add(9);
		// way1
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {

				return o2 - o1;
			}
		});

		// way2
		Collections.sort(list, (o1, o2) -> {
			return o1 - o2;
		});

		// way3
		Collections.sort(list, (o1, o2) -> o1 - o2);

		System.out.println(list);
	}

	public void sort_String_list() {

		List<String> list = new ArrayList<>();
		list.add("venkat");
		list.add("krishna");
		list.add("nani");
		list.add("john");

		List<String> newList = list.stream().filter(item -> {

			if (item.equals("john")) {
				return false;
			}
			return true;
		}).sorted((o1, o2) -> o1.compareTo(o2)).map(String::toUpperCase).collect(Collectors.toList());

		System.out.println(newList);

	}

	public void sort_Key_Integer_map() {

		Map<Integer, Integer> map = new HashMap<>();
		map.put(7, 30);
		map.put(5, 20);
		map.put(2, 30);
		
		//sort by key
		Map<Integer,Integer>newMap=map.entrySet().stream().filter(x->x.getKey()>2).sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(x->x.getKey(), x->x.getValue(), ((oldValue, newValue) -> oldValue), LinkedHashMap::new));
		
		//sort by key desc order
		Map<Integer,Integer>newMap4=map.entrySet().stream().filter(x->x.getKey()>2).sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).collect(Collectors.toMap(x->x.getKey(), x->x.getValue(), ((oldValue, newValue) -> oldValue), LinkedHashMap::new));
				
		
		//sort by value
		Map<Integer,Integer>newMap2=map.entrySet().stream().filter(x->x.getKey()>2).sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(x->x.getKey(), x->x.getValue(), ((oldValue, newValue) -> oldValue), LinkedHashMap::new));
		
		// sort by valye desc order
		Map<Integer,Integer>newMap3=map.entrySet().stream().filter(x->x.getKey()>2).sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue, ((oldValue, newValue) -> oldValue), LinkedHashMap::new));
		
		
		for(Map.Entry<Integer, Integer>m:newMap3.entrySet()) {
			System.out.println(m.getKey()+"\t,"+m.getValue());
		}

	}
	
	public static void main(String[] args) {
		
		My_Stream m=new My_Stream();
		m.sort_Key_Integer_map();
	}
}
