package javaProjectFirst.order.controller;

import javaProjectFirst.enums.OrderItemSize;
import javaProjectFirst.main.model.Menu;
import javaProjectFirst.order.model.Order;
import javaProjectFirst.order.model.OrderItem;
import javaProjectFirst.product.model.Product;

public class OrderMenager {
	
	
	
	
	public Order createOrder(Menu menu) {
		
		Order order = new Order();
		
		try {
		addOrderItem(order, menu.getMenuItems().get(100), OrderItemSize.MEDIUM, 2);
		addOrderItem(order, menu.getMenuItems().get(102), OrderItemSize.LARGE, 3);
		addOrderItem(order, menu.getMenuItems().get(201), OrderItemSize.XXL, 4);
		addOrderItem(order, menu.getMenuItems().get(205), OrderItemSize.LARGE, 3);
		addOrderItem(order, menu.getMenuItems().get(203), OrderItemSize.LARGE, 4);
		addOrderItem(order, menu.getMenuItems().get(104), OrderItemSize.LARGE, 4);
		} catch (NullPointerException e) {
			
			System.out.println("please check one of the arguments might be null");
			
		}
		return order;
	}
	
	
	
	private OrderItem createOrderItem(Product product , OrderItemSize orderItemSize , int quantity) {
		
		
		return new OrderItem(product , orderItemSize , quantity);
	}
	
	private void addOrderItem(Order order , Product product , OrderItemSize orderItemSize, int quantity ) {
		
		
		OrderItem orderItem = createOrderItem(product, orderItemSize, quantity);
		order.getOrderItems().add(orderItem);
	}
	

}
