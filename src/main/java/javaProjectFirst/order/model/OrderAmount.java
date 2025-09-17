package javaProjectFirst.order.model;

public class OrderAmount {
	
	private double totalOrderAmount;
	private double totalOrderWat;
	private double totalOrderAmountWithWat;
	
	
	public OrderAmount(double totalOrderAmount , double totalOrderWat , double totalOrderAmountWithWat) {
		
		this.totalOrderAmount = totalOrderAmount;
		this.totalOrderWat = totalOrderWat;
		this.totalOrderAmountWithWat = totalOrderAmountWithWat;
		
	}
	
	public double getTotalOrderAmount() {
		return totalOrderAmount;
	}
	
	public double getTotalOrderWat() {
		
		return totalOrderWat;
	}
	
	public double getTotalOrderAmountWithWat() {
		
		return totalOrderAmountWithWat;
	}
	
	
}
