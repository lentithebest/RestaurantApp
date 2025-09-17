package javaProjectFirst.order.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javaProjectFirst.main.model.Client;
import javaProjectFirst.main.model.Restaurant;
import javaProjectFirst.order.model.Order;
import javaProjectFirst.order.model.OrderAmount;
import javaProjectFirst.order.model.OrderItem;
import javaProjectFirst.product.model.Product;





public class OrderPrinter {
	
	
	
	private StringBuilder stringBuilder = new StringBuilder();
	
	
	
	public String printTotalInfo(Client client , Restaurant restaurant , Order order , OrderAmount orderAmount , int vatRate) {
		
		
		
		ArrayList<OrderItem> orderItems = order.getOrderItems();
		
		
		printOrderInfoHeader(client);	
		
		/*for (OrderItem orderItem : orderItems) {
			
			printOrderItemInfo(orderItem);
		} */
		
		Iterator <OrderItem> iterator = orderItems.iterator();
		while (iterator.hasNext()) {
			OrderItem orderItem = iterator.next();
			printOrderItemInfo(orderItem);
			
		}
		
		printOrderInfoFooter(orderAmount, restaurant , vatRate);
		return stringBuilder.toString();
		
	}
	private  void printOrderInfoHeader(Client client) {
		
		stringBuilder.append("<html> <br>Order from").append(": ").append(client.getName()).append("<br>");
		stringBuilder.append("Contact Number: ").append(client.getNumOfPhone()).append("<br>");
		stringBuilder.append("<hr>").append("<br>");
	}
	
	private void printOrderInfoFooter(OrderAmount orderAmount, Restaurant restaurant, int vatRate) {
	    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	    stringBuilder.append("<hr>").append("<br>")
	        .append("The total price of the order is: ").append("€").append(orderAmount.getTotalOrderAmount()).append(" (SUB TOTAL: ")
	        .append(decimalFormat.format(orderAmount.getTotalOrderAmount())).append(")").append("<br>")
	        .append("VAT ").append(vatRate).append("%: ").append(decimalFormat.format(orderAmount.getTotalOrderAmount()))
	        .append("€").append("<br>").append("TOTAL: ").append(decimalFormat.format(orderAmount.getTotalOrderAmountWithWat()))
	      
	        .append("<br>").append(restaurant.getName()).append(" in ").append(restaurant.getAddress()).append("<br>")
	        .append("Copyright: MAKERMINDS");
	    System.out.println(stringBuilder);
	}
	private void printOrderItemInfo(OrderItem orderItem) {
	    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	    Product product = orderItem.getProduct();

	    double totalOrderItemPrice = orderItem.getOrderItemPrice() * orderItem.getQuantity();
	    try {
	        stringBuilder.append(orderItem.getQuantity()).append(" x | ")
	            .append(product.getProductId()).append(" | ")
	            .append(product.getName()).append(" | ")
	            .append(decimalFormat.format(orderItem.getOrderItemPrice())).append(" € | ")
	            .append(decimalFormat.format(totalOrderItemPrice)).append(" €")
	            .append("<br>");
	    } catch (NullPointerException e) {
	        System.err.println("Product was null so we could not get the name, price and quantity!");
	    }
	}
	
	

}
