package javaProjectFirst.main.controller;

import javaProjectFirst.enums.Location;

public class LocationMenager {
	
	
	
	public static Location getLocationAsId(int locationId) {
		
		switch (locationId) {
		
		case 1 : return Location.KOSOVA;
		case 2 : return Location.GERMANY;
		default : 
			throw new IllegalArgumentException("there were no matching location with: " + locationId);
		}
		
		
	}
	
	
	
}
