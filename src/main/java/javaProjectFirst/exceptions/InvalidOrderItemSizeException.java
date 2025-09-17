package javaProjectFirst.exceptions;

@SuppressWarnings("serial")
public class InvalidOrderItemSizeException extends RuntimeException {
	
	
	public InvalidOrderItemSizeException(String message) {
		super(message);
	}
	
}
