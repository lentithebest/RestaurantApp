package javaProjectFirst.main.model;

import java.util.HashMap;

import javaProjectFirst.product.model.Drink;
import javaProjectFirst.product.model.Meal;
import javaProjectFirst.product.model.Product;

public class Menu {
	
	

	
	
	private HashMap<Integer , Product> menuItems = new HashMap<>();
	
	
	public void initializeMenuProducts() {
		
		menuItems.put(100, new Meal(100, "Hamburger", 4.5 ));
		menuItems.put(101, new Meal(101, "Cheeseburger", 5 ));
		menuItems.put(102, new Meal(102, "Sandwich",3.5));
		menuItems.put(103, new Meal(103, "Hotdog", 3 ));
		menuItems.put(104, new Meal(104, "Pizza", 6 ));
		menuItems.put(105, new Meal(105, "Fries", 2 ));
		menuItems.put(200, new Drink(200, "Coca Cola", 1 , false));
		menuItems.put(201, new Drink(201, "Coca Cola Zero", 1 , true));
		menuItems.put(202, new Drink(202, "Fanta", 1 , false));
		menuItems.put(203, new Drink(203, "Sprite", 1 , false));
		menuItems.put(204, new Drink(204, "Red Bull", 2 , false));
		menuItems.put(205, new Drink(205, "Coffee", 0.5 , true));
		menuItems.put(300, new Meal(300, "Ice cream", 1 ));
		menuItems.put(301, new Meal(301, "Waffle", 2.5));
		menuItems.put(302, new Meal(302, "Brownie", 1.5 ));
		
	}
	
	public HashMap<Integer , Product> getMenuItems() {
		return menuItems;
	}
	
	
	

}
