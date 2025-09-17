package javaProjectFirst.order.model;

import javaProjectFirst.enums.OrderItemSize;
import javaProjectFirst.product.model.Product;

public class OrderItem {
	
	private Product product;
	private OrderItemSize orderItemSize;
	private int quantity;
	private double orderItemPrice;
	
	
	public OrderItem(Product product , OrderItemSize orderItemSize , int quantity) {
		this.product = product;
		this.orderItemSize = orderItemSize;
		this.quantity = quantity;
	}
	
	public void setProductId(Product product) {
		this.product = product;
	}
	public Product getProduct() {
		return product;
	}
	public void setOrderItemSize(OrderItemSize orderItemSize) {
		this.orderItemSize = orderItemSize;
	}
	public OrderItemSize getOrderItemSize() {
		return orderItemSize;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setOrderItemPrice(double orderItemPrice) {
		this.orderItemPrice = orderItemPrice;
	}
	public double getOrderItemPrice() {
		return orderItemPrice;
	}
}
