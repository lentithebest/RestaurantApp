package javaProjectFirst.order.controller;

import javaProjectFirst.enums.Location;

public class OrderCalculatorFactory {
	
	
	public static IOrderCalculator getCalculator(Location location) {
		
		switch (location) {
		
		case KOSOVA : return new OrderCalculatorKS();
		case GERMANY : return new OrderCalculatorGER();
		default : throw new IllegalArgumentException("there were no argument matching with: " + location);
		}
	}
	
	

}
