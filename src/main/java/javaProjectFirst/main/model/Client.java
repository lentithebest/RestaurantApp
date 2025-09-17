package javaProjectFirst.main.model;

public class Client {
	
	
	private String name;
	private String numOfPhone;
	
	public Client(String name , String numOfPhone) {
		
		this.name = name;
		this.numOfPhone = numOfPhone;
	}
	public String getName() {
		
		return name;
	}
	
	public String getNumOfPhone() {
		
		return numOfPhone;
	}

}
