package com.java.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.java.model.OrderLineItemsDto;
import com.java.model.OrderRequest;

public class My_Streams {

	public void perform1(OrderRequest OrderRequest) {

		List<OrderLineItemsDto> orderLineItems = OrderRequest.getOrderLineItemsDtoList()
				.stream()
				.map(this::mapToDto)
				.collect(Collectors.toList());

		orderLineItems.forEach(x -> System.out.println(x.getQuantity()));
	}

	public void perform2(OrderRequest OrderRequest) {

		List<OrderLineItemsDto> orderLineItems = OrderRequest.getOrderLineItemsDtoList().stream()
				.map(x -> new OrderLineItemsDto() {
					{
						setId(x.getId());
						setQuantity(x.getQuantity());
					}
				}).collect(Collectors.toList());

		orderLineItems.forEach(x -> System.out.println(x.getQuantity()));
	}

	public void perform3(OrderRequest OrderRequest) {

		List<OrderLineItemsDto> orderLineItems = OrderRequest.getOrderLineItemsDtoList().stream()
				.map(request -> OrderLineItemsDto.builder()
						.id(request.getId())
						.quantity(request.getQuantity())
						.build())
				.collect(Collectors.toList());

		orderLineItems.forEach(x -> System.out.println(x.getQuantity()));
	}
	
	public void perform4(OrderRequest OrderRequest) {
		
		List<Integer>list=OrderRequest.getOrderLineItemsDtoList()
				.stream()
				.map(OrderLineItemsDto::getQuantity)
				.collect(Collectors.toList());
		
		list.forEach(x -> System.out.println(x));
	}
	
      public void perform5(Map<Long,Integer> OrderRequest) {
		
    	  List<OrderLineItemsDto> orderLineItems = OrderRequest.entrySet().stream()
  				.map(request -> OrderLineItemsDto.builder()
  						.id(request.getKey())
  						.quantity(request.getValue())
  						.build())
  				.collect(Collectors.toList());
    	  
    	  orderLineItems.forEach(x -> System.out.println(x.getQuantity()));
		
		
	}

	public OrderLineItemsDto mapToDto(OrderLineItemsDto orderLineItems) {

		OrderLineItemsDto dto = new OrderLineItemsDto();
		dto.setId(orderLineItems.getId());
		dto.setQuantity(orderLineItems.getQuantity());

		return dto;

	}
	
	

	public static void main(String[] args) {
		OrderRequest r = new OrderRequest();
		List<OrderLineItemsDto> list = new ArrayList<>();

		OrderLineItemsDto d1 = new OrderLineItemsDto();
		d1.setId(101l);
		d1.setQuantity(2);

		OrderLineItemsDto d2 = new OrderLineItemsDto();
		d2.setId(101l);
		d2.setQuantity(5);

		list.add(d1);
		list.add(d2);

		r.setOrderLineItemsDtoList(list);

		My_Streams s = new My_Streams();
		Map<Long,Integer>map=new HashMap<>();
		map.put(101l, 10);map.put(102l, 100);
		s.perform5(map);
	}

}
