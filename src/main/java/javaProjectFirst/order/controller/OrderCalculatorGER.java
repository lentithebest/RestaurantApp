package javaProjectFirst.order.controller;

import javaProjectFirst.enums.OrderItemSize;
import javaProjectFirst.exceptions.InvalidOrderItemSizeException;

public class OrderCalculatorGER extends AbstractOrderCalculator {
	
	
	private final double VAT_RATE = 0.19;
	
	
	
	public double getSizeRateAmount(OrderItemSize orderItemSize) {
		
       switch(orderItemSize) {
		
		case SMALL : return 0.8;
		
		case MEDIUM : return 1;
		
		case LARGE : return 1.8;
		
		case XXL : return 1.35;
		
		default : throw new InvalidOrderItemSizeException("there was no size for: " + orderItemSize);

       }
	}

	
	protected double getVatRate() {
		
		return this.VAT_RATE;
		
		
	}

}
