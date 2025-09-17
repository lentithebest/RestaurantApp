package javaProjectFirst.controller.order;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javaProjectFirst.enums.OrderItemSize;
import javaProjectFirst.order.controller.OrderCalculatorGER;

public class testOrderCalculatorGER {
	
	
	OrderCalculatorGER orderCalculatorGER = new OrderCalculatorGER();
	
	
	@Test
	public void testVatRate() {
		
		
		double vatRate = orderCalculatorGER.getVatRate(true);
		Assertions.assertEquals( 0.19, vatRate);
		
		
	}
	
	@Test
	public void testSizeRateAmount() {
		
		
		double sizeRateAmountSmall = orderCalculatorGER.getSizeRateAmount(OrderItemSize.SMALL);
		Assertions.assertEquals(0.8, sizeRateAmountSmall);
	}
	
	
}
