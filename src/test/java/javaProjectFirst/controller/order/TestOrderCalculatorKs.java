package javaProjectFirst.controller.order;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javaProjectFirst.enums.OrderItemSize;
import javaProjectFirst.order.controller.OrderCalculatorKS;

public class TestOrderCalculatorKs {
	
	
	OrderCalculatorKS orderCalculator = new OrderCalculatorKS();
		
	
	@Test
	public void getVateRate() {
		
		
		
		
		
		
		double vatRate = orderCalculator.getVatRate(true);
		
		
		Assertions.assertEquals(0.18, vatRate);
		
		
		
		
		
	}
	
	@Test
	public void getSizeRateAmount() {
		
		
		double small = orderCalculator.getSizeRateAmount(OrderItemSize.SMALL);
		double medium = orderCalculator.getSizeRateAmount(OrderItemSize.MEDIUM);
		double large = orderCalculator.getSizeRateAmount(OrderItemSize.LARGE);
		double xxl = orderCalculator.getSizeRateAmount(OrderItemSize.XXL);
		
		
		Assertions.assertEquals(0.7 ,  small);
		Assertions.assertEquals(1 , medium);
		Assertions.assertEquals(1.2, large);
		Assertions.assertEquals(1.25, xxl);
	}
	
	
	

}
