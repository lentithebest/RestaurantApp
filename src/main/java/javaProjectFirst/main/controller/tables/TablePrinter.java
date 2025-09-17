package javaProjectFirst.main.controller.tables;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javaProjectFirst.tables.Table;

public class TablePrinter {
	
	
	
	
	
	public void printTableReservations(int tableIdToReserve, List<LocalDateTime> tableReservations) {
	    try {
	        StringBuilder stringBuilder = new StringBuilder();
	        if(tableReservations != null && !tableReservations.isEmpty()) {
	            stringBuilder.append("Existing reservations for table with ID: ").append(tableIdToReserve)
	                .append(System.lineSeparator()).append("----------------------------")
	                .append(System.lineSeparator());
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	            for (LocalDateTime tableReservation: tableReservations) {
	                String formattedDateTime = tableReservation.format(formatter);
	                stringBuilder.append(formattedDateTime).append(System.lineSeparator());
	            }
	            stringBuilder.append("----------------------------\n");
	        } else {
	            stringBuilder.append("There are no reservations for this table \n");
	        }
	        System.out.println(stringBuilder);
	    } catch(NullPointerException e) {
	        System.out.println("No reservations for this table!");
	    }
	    }
		
			
	
	public void printAvailableTables(List<Table> tableList) {
		
		
	
		
		StringBuilder stringBuilder = new StringBuilder();
		
		
		stringBuilder.append("Available Tables: ").append(System.lineSeparator())
		.append("--------------------------").append(System.lineSeparator()).append("TableId\t\t\tTableSeats").append(System.lineSeparator())
		.append("--------------------------").append(System.lineSeparator());
		
		
		for (Table table : tableList ) {
			
			stringBuilder.append(table.getTableId()).append("\t\t\t").append(table.getTableSeats()).append(System.lineSeparator());
			
		}
		stringBuilder.append("---------------------------").append(System.lineSeparator());
		System.out.println(stringBuilder);
		
	}

}
