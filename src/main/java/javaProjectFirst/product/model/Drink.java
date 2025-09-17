package javaProjectFirst.product.model;

public class Drink extends Product {
	
	private boolean isSugarFree;
	
	public Drink(int productId , String name , double price , boolean isSugarFree) {
		
		super(productId , name , price);
		this.isSugarFree = isSugarFree;
	}
	
	
	public boolean getIsSugarFree() {
		return isSugarFree;
	}
	
	public void setIsSugarFree(boolean isSugarFree) {
		this.isSugarFree = isSugarFree;
	}

}
