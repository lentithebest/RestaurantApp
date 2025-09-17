package javaProjectFirst.order.controller;

import javaProjectFirst.enums.OrderItemSize;
import javaProjectFirst.order.model.Order;
import javaProjectFirst.order.model.OrderAmount;
import javaProjectFirst.order.model.OrderItem;

public interface IOrderCalculator {
	
	
	public double calculateOrderItemPrice(OrderItem orderItem);
	public double calculateTotalOrderAmount(Order order);
	public double getSizeRateAmount(OrderItemSize orderItemSize);
    public double calculateTotalOrderWat(double totalOrderAmount);
    public OrderAmount calculateOrderAmount(Order order);
    public double getVatRate(boolean decimal);
	

}
