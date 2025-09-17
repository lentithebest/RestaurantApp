package javaProjectFirst.controller.order;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javaProjectFirst.enums.OrderItemSize;
import javaProjectFirst.main.model.Menu;
import javaProjectFirst.order.controller.AbstractOrderCalculator;
import javaProjectFirst.order.model.Order;
import javaProjectFirst.order.model.OrderItem;
import javaProjectFirst.product.model.Product;

public class AbstractOrderCalculatorTest {
	
	
	private Menu menu = new Menu();
	
	
	
	AbstractOrderCalculator orderCalculatorMock = new AbstractOrderCalculator() {

		@Override
		public double getSizeRateAmount(OrderItemSize orderItemSize) {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		protected double getVatRate() {
			// TODO Auto-generated method stub
			return 0.12;
		}
		
		
		
		
	};
	@Test
	public void testCalculateOrderItemPrice() {
		Product hamburger = menu.getMenuItems().get(100);
		OrderItem orderItem = new OrderItem(hamburger , OrderItemSize.MEDIUM , 2);
		double orderItemPrice =  orderCalculatorMock.calculateOrderItemPrice(orderItem);
		Assertions.assertEquals(9.0 , orderItemPrice);
		
		
		
	}
	
	@Test
	public void testOrderItemPrice() {
		
		Product hamburger = menu.getMenuItems().get(100);
		Product fries = menu.getMenuItems().get(105);
		Product coffe = menu.getMenuItems().get(205);
		Product iceCream = menu.getMenuItems().get(300);
		Product brownie = menu.getMenuItems().get(302);
		
		OrderItem hamburgerr = new OrderItem(hamburger , OrderItemSize.MEDIUM , 2);
		OrderItem friess = new OrderItem(fries  , OrderItemSize.LARGE , 3);
		OrderItem coffee = new OrderItem(coffe , OrderItemSize.XXL , 4);
		OrderItem iceCreamm = new OrderItem(iceCream , OrderItemSize.LARGE , 3);
		OrderItem browniee = new OrderItem(brownie , OrderItemSize.LARGE , 4);
		
		
		Order order = new Order();
		order.getOrderItems().add(hamburgerr);
		order.getOrderItems().add(friess);
		order.getOrderItems().add(coffee);
		order.getOrderItems().add(iceCreamm);
		order.getOrderItems().add(browniee);
		
		
		double orderItemPrice = orderCalculatorMock.calculateTotalOrderAmount(order);
		
		Assertions.assertEquals(26.0 , orderItemPrice );
		
		
	}
	
}
