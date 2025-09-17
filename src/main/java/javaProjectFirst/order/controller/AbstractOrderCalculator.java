package javaProjectFirst.order.controller;

import java.util.List;

import javaProjectFirst.order.model.Order;
import javaProjectFirst.order.model.OrderAmount;
import javaProjectFirst.order.model.OrderItem;
import javaProjectFirst.product.model.Product;




public abstract class AbstractOrderCalculator implements IOrderCalculator {
	
	
	 public double calculateTotalOrderAmount(Order order) {
			
			List<OrderItem> orderItems = order.getOrderItems();
			
			double totalOrderAmount = orderItems.stream().mapToDouble(this::calculateOrderItemPrice).sum();
			
			
			
			
			return totalOrderAmount;
			
			
			
		}
	       

	      public double calculateTotalOrderWat(double totalOrderAmount) {
		
		
		  double totalOrderWat = totalOrderAmount * getVatRate(true);
		
		
	 	  return totalOrderWat;
	  }
	      
	  public double calculateOrderItemPrice(OrderItem orderItem) {
		
		   Product product = orderItem.getProduct();
		   try {
		   double sizeRateAmount = getSizeRateAmount(orderItem.getOrderItemSize());
		
		   double totalOrderItemPrice = product.getPrice() * sizeRateAmount;
		
		   orderItem.setOrderItemPrice(totalOrderItemPrice);
		
		
		
		   return totalOrderItemPrice * orderItem.getQuantity();
		   } catch (NullPointerException e) {
			   System.err.println("can u please check one of the products is null: ");
			   return 0;
		   }
		   
				   
	    }
	    
	    public double getVatRate(boolean decimal) {
	    	if (decimal) {
	    		return getVatRate();
	    	}
	    	else {
	    		return getVatRate() * 100;
	    	}
	    }

	    
	    public OrderAmount calculateOrderAmount(Order order) {
	    	
	    	double totalOrderAmount = calculateTotalOrderAmount(order);
	    	double totalOrderWat = calculateTotalOrderWat(totalOrderAmount);
	    	double totalOrderWithWat = totalOrderAmount + totalOrderWat;
	    	OrderAmount orderAmount = new OrderAmount(totalOrderAmount , totalOrderWat , totalOrderWithWat);
	    	return orderAmount;
	    }
	    
	    
	    
		

		protected abstract double getVatRate();
		
	
	
	

}
