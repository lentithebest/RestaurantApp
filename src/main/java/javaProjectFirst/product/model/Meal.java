package javaProjectFirst.product.model;

public class Meal extends Product {
	
	private String description;
	
	
	public Meal(int productId , String name , double price) {
		
		super(productId , name , price);
		
		
		
	}
	
	
	public String getDescription() {
		
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
