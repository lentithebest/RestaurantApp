package javaProjectFirst.main.controller.tables;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javaProjectFirst.tables.Table;

public class TableReservationMenager {
	
	
	
	private Map<Integer , List<LocalDateTime>> reservationMap = new HashMap<>();
	
	
	
	
	List<Table> availableTables = null;
	public TableReservationMenager (List <Table> tableList) {
		availableTables = tableList;
		initializeTableReservation(tableList);
		
	}
	private void initializeTableReservation(List <Table> tablesList) {
		
		for (Table table : tablesList) {
			
			int tableId = table.getTableId();
			List<LocalDateTime> reservations = new ArrayList<>();
			reservationMap.put(tableId, reservations);
		}
	}
	
	
	public List<LocalDateTime> getTableReservationByTableId (int tableIdToReserve) {
		Table tableToReserve = getTableByTableId(tableIdToReserve , availableTables);
		
		if (tableToReserve == null) {
			System.out.println("Table with ID:" + tableIdToReserve + "was not found!");
			
		}
		else {
			System.out.println("Table with ID:" + tableIdToReserve + "was found!");
			return reservationMap.get(tableIdToReserve);
		}
		return null;
		
	}
	
	
	public Table getTableByTableId(int tableIdToReserve , List<Table> tablesList) {
		
		for (Table table : tablesList) {
			if (table.getTableId() == tableIdToReserve) {
				
				return table;
			}
		}
		return null;
		
	}
	
	
	public void addReservation(int tableId , LocalDateTime reservationDateTime) {
		
		reservationMap.putIfAbsent(tableId, new ArrayList<>());
		List<LocalDateTime> currentReservations = reservationMap.get(tableId);
		currentReservations.add(reservationDateTime);
		reservationMap.put(tableId, currentReservations);
		
		
	}
	public boolean isTableFreeAt(int tableId , LocalDateTime checkDateTime) {
		
		List<LocalDateTime> reservations = reservationMap.get(tableId);
		return reservations.isEmpty() || reservations.contains(checkDateTime);
		
	}
	
	public Table findTableById (int tableId) {
		for (Table table : availableTables) {
			if (table.getTableId() == tableId) {
				return table;
			}
			
		}
		return null;
		
	}
	
	
	
	
}
