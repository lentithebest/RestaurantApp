package javaProjectFirst.exceptions;

@SuppressWarnings("serial")
public class InvalidMenuFileException extends RuntimeException {
	
	
	public InvalidMenuFileException (String message) {
		
		super(message);
	}

}
