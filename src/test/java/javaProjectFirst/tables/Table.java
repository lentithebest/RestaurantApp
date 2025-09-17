package javaProjectFirst.tables;

public class Table {
	
	
	private int tableId;
	private int tableSeats;
	
	
	public Table(int tableId , int tableSeats) {
		
		
		
		this.tableId = tableId;
		this.tableSeats = tableSeats;
	}
	
	public void setTableId(int tableId) {
		
		this.tableId = tableId;
	}
	
	public int getTableId() {
		
		return tableId;
	}
	public void getTableSeats(int tableSeats) {
		this.tableSeats = tableSeats;
		
	}
	
	public int getTableSeats() {
		
		return tableSeats;
	}
	
	
	
	
}
