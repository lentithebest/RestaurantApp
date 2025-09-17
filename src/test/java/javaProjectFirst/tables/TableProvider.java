package javaProjectFirst.tables;

import java.util.ArrayList;
import java.util.List;

public class TableProvider {
	
	
	private List<Table> tableList;
	
	
	
	public TableProvider() {
		createTableList();
	}
	
	
	public void createTableList() {
		
		
		tableList = new ArrayList<>();
		tableList.add(new Table(101 , 2));
		tableList.add(new Table(102 , 4));
		tableList.add(new Table(103 , 6));
		tableList.add(new Table(104 , 2));
		tableList.add(new Table(105 , 4));
		tableList.add(new Table(201 , 4));
		tableList.add(new Table(202 , 10));
		tableList.add(new Table(203 , 4));
		tableList.add(new Table(204 , 4));
		tableList.add(new Table(205 , 2));
		
	}
	
	public List<Table> getTableList() {
		return tableList;
	}
	
	

}
