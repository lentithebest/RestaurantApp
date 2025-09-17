package javaProjectFirst.order.controller;

import javaProjectFirst.enums.OrderItemSize;
import javaProjectFirst.exceptions.InvalidOrderItemSizeException;

public class OrderCalculatorKS extends AbstractOrderCalculator {
	
	
	
private final double VAT_RATE = 0.18;
	

	
	public double getSizeRateAmount(OrderItemSize orderItemSize) {
		
		switch(orderItemSize) {
		
		case SMALL : return 0.7;
		
		case MEDIUM : return 1;
		
		case LARGE : return 1.2;
		
		case XXL : return 1.25;
		
		default : throw new InvalidOrderItemSizeException("there was no valid size for: " + orderItemSize);
		
		}
		
		
	}
	
	

	
	protected double getVatRate() {
		
		return this.VAT_RATE;
	}
	

}
