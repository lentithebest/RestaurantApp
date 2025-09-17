package javaProjectFirst.order.controller;

import javaProjectFirst.enums.Location;
import javaProjectFirst.main.model.Client;
import javaProjectFirst.main.model.Menu;
import javaProjectFirst.main.model.Restaurant;
import javaProjectFirst.order.model.Order;
import javaProjectFirst.order.model.OrderAmount;

public class OrderProcessor {
	
	
	
	public void processOrder(Restaurant restaurant , Client client , Menu menu , Location location ){
		
		OrderMenager orderMenager = new OrderMenager();
		Order order =  orderMenager.createOrder(menu);
		
		
		IOrderCalculator orderCalculator = OrderCalculatorFactory.getCalculator(location);
		OrderAmount orderAmount = orderCalculator.calculateOrderAmount(order);
		
		
		int vatRate = (int)orderCalculator.getVatRate(true);
		OrderPrinter orderPrinter = new OrderPrinter();
		orderPrinter.printTotalInfo(client, restaurant, order , orderAmount, vatRate);
		
	}
	
	public String prepareInvoice(Restaurant restaurant , Client client , Order order , Location location) {
		IOrderCalculator orderCalculator = OrderCalculatorFactory.getCalculator(location);
		OrderAmount orderAmount = orderCalculator.calculateOrderAmount(order);
		int vatRate = (int)orderCalculator.getVatRate(true);
		OrderPrinter orderPrinter = new OrderPrinter();
		return  orderPrinter.printTotalInfo(client, restaurant, order ,  orderAmount, vatRate);
	}

}
