package javaProjectFirst.main.controller;


import java.util.Map.Entry;


import javaProjectFirst.main.model.Menu;
import javaProjectFirst.product.model.Product;

public class MenuPrinter {
	
	
	
	public void printMenuItems(Menu menu) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("----------------MENU---------------------").append(System.lineSeparator());
		for (Entry<Integer , Product > menuItems : menu.getMenuItems().entrySet()) {
			Product product = menuItems.getValue();
			stringBuilder.append(product.getProductId()).append(". ").append(product.getName()).append(" |").append(product.getPrice()).append("$").append(System.lineSeparator());
			
		}
		stringBuilder.append("-------------------------------------------");
		System.out.println(stringBuilder);
		
		
		
	}	
	
	
	
}
