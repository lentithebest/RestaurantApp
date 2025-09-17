package javaProjectFirst.order.controller;



import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javaProjectFirst.exceptions.InvalidMenuFileException;
import javaProjectFirst.main.model.Menu;
import javaProjectFirst.product.model.Drink;
import javaProjectFirst.product.model.Meal;
import javaProjectFirst.product.model.Product;

public class MenuImporter {
	
	
	public Menu importMenu(String filePath) {
	    Menu importedMenu = new Menu();

	    try {
	        List<String> fileLines = Files.readAllLines(Paths.get(getClass().getResource(filePath).toURI()));
	        for (String menuItemAsString : fileLines) {
	            String[] menuItemStringArray = menuItemAsString.split(",");

	            int productId = Integer.valueOf(menuItemStringArray[0]); // 100
	            String productName = menuItemStringArray[1];
	            double productPrice = Double.valueOf(menuItemStringArray[2]);
	            String productCategory = menuItemStringArray[3];
	            Product product = null;

	            if ("meal".equals(productCategory)) {
	                product = new Meal(productId, productName, productPrice );
	            } else if ("drink".equals(productCategory)) {
	                boolean sugarFree = Boolean.valueOf(menuItemStringArray[4]);
	                product = new Drink(productId, productName, productPrice, sugarFree);
	            } else {
	                StringBuffer stringBuffer = new StringBuffer();
	                stringBuffer.append("The menu file could not be processed as the product category from product:")
	                            .append(productName).append(" is invalid.");
	                throw new InvalidMenuFileException(stringBuffer.toString());
	            }

	            importedMenu.getMenuItems().put(productId, product);
	        }
	    } catch (IOException | URISyntaxException e) {
	        throw new RuntimeException("Menu file could not be found", e);
	    }

	    return importedMenu;
	}
	

}
