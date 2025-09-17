package javaProjectFirst.main.model;

public class Restaurant {
	
	
	private String name;
	private String address;
	
	public Restaurant(String name , String address) {
		
		this.name = name;
		this.address = address;
	}
	
	public String getName() {
		
		return name;
	}
	
	public String getAddress() {
		
		return address;
	}
	
}
