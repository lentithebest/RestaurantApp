package javaProjectFirst.mainn;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import javaProjectFirst.enums.ApplicationMode;
import javaProjectFirst.enums.Location;
import javaProjectFirst.main.controller.LocationMenager;
import javaProjectFirst.main.controller.MenuPrinter;
import javaProjectFirst.main.controller.tables.TablePrinter;
import javaProjectFirst.main.controller.tables.TableReservationMenager;
import javaProjectFirst.main.model.Client;
import javaProjectFirst.main.model.Menu;
import javaProjectFirst.main.model.Restaurant;
import javaProjectFirst.order.controller.MenuImporter;
import javaProjectFirst.order.controller.OrderProcessor;
import javaProjectFirst.tables.Table;
import javaProjectFirst.tables.TableProvider;

public class RestaurantApp {
	
	private static Scanner  scanner = new Scanner(System.in);
	
	private static Location currentLocation;
	private static TableProvider tableProvider = new TableProvider();
	private static List<Table> tableList = tableProvider.getTableList();
	private static TableReservationMenager tableReservationMenager = new TableReservationMenager(tableList);
	private final static String MENU_FILE_PATH = "/menu.txt"; 
	public static void main(String args[]) {
		ApplicationMode selectedApplicationMode;
		
		try {
			do {
			selectedApplicationMode = getApplicationMode();
			if (selectedApplicationMode == ApplicationMode.ORDER) {
				
				getCurrentLocation();
				
			}
			
		    validateApplicationMode(selectedApplicationMode);
			} while (selectedApplicationMode != ApplicationMode.EXIT);
		} 
		catch (Exception e) {
			
		e.printStackTrace();
		}
		finally {
			scanner.close();
		}
		
	   }
		
		
		private static ApplicationMode getApplicationMode() {
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Please select a location (type a number): ").append(System.lineSeparator());
			stringBuilder.append("1. " + ApplicationMode.ORDER.name()).append(System.lineSeparator());
			stringBuilder.append("2. " + ApplicationMode.TABLERESERVATION.name() ).append(System.lineSeparator())
			.append("3. ").append(ApplicationMode.EXIT.name());
			System.out.println(stringBuilder);
			int selectedApplicationModeNumber = scanner.nextInt();
			ApplicationMode selectedApplicationMode = getApplicationModeFromId(selectedApplicationModeNumber);
			return selectedApplicationMode;
		}
		
		private static void getCurrentLocation() {
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Please select a location (type a number)") .append(System.lineSeparator()) .append("1 " + Location.KOSOVA.name()).append(System.lineSeparator()).append("2 " + Location.GERMANY.name()).append(System.lineSeparator());
			System.out.println(stringBuilder);
			int selectedLocationId = scanner.nextInt();
			currentLocation = LocationMenager.getLocationAsId(selectedLocationId);
			
		}
		private static ApplicationMode getApplicationModeFromId(int selectedApplicationModeNumber) {
			switch (selectedApplicationModeNumber) {
			
			case 1 : return ApplicationMode.ORDER;
			case 2 : return ApplicationMode.TABLERESERVATION;
			case 3 : return ApplicationMode.EXIT;
			default : return null;
			
			}
		}

	
	
	   private static void validateApplicationMode(ApplicationMode applicationMode) {
	      switch (applicationMode) {
			
			case  ORDER -> runProcessOrder();
			case TABLERESERVATION -> runTableProcess();
			case EXIT -> System.out.println("the application has been stopped running");
			}
			
			
		}
		
		
		public static void runProcessOrder() {
			MenuImporter menuImporter = new MenuImporter();
			Restaurant restaurant = new Restaurant("KFC" , "Mitrovic");
			Client client = new Client("Florent " , "+3432943294");
			Menu menu = menuImporter.importMenu(MENU_FILE_PATH);
			MenuPrinter menuPrinter = new MenuPrinter();
			
			
			
			menuPrinter.printMenuItems(menu);
			
			
			OrderProcessor orderProcessor = new OrderProcessor();
			orderProcessor.processOrder(restaurant, client, menu, currentLocation);
			
		}
		
		
		
		public static void runTableProcess() {
			
			
		
			TablePrinter tablePrinter = new TablePrinter();
			tablePrinter.printAvailableTables(tableList);
			
			int tableIdToReserve = getTableIdFromUserInput();
			
			Table table = tableReservationMenager.findTableById(tableIdToReserve);
			if(table != null) {
			    List<LocalDateTime> tableReservations = tableReservationMenager.getTableReservationByTableId(tableIdToReserve);
			    tablePrinter.printTableReservations(tableIdToReserve, tableReservations);

			    LocalDateTime reservationDateTime = getReservationDateTimeFromUserInput();

			    boolean isTableFree = tableReservationMenager.isTableFreeAt(tableIdToReserve, reservationDateTime);
			    if(isTableFree) {
			        tableReservationMenager.addReservation(tableIdToReserve, reservationDateTime);
			        System.out.println("Table" + tableIdToReserve + " has been reserved for " + formatDateTime(reservationDateTime));
			    } else {
			        System.out.println("Table" + tableIdToReserve + " is already reserved at " + formatDateTime(reservationDateTime));
			    }
			} else {
			    System.out.println("Table has not been found!");
			}
			
			
			
			
			
		}
		
		
		
		private static int getTableIdFromUserInput() {
		    System.out.println("Please enter the table Id: ");
		    int tableIdToReserve = scanner.nextInt();
		    scanner.nextLine();
		    return tableIdToReserve;
		}

		private static LocalDateTime getReservationDateTimeFromUserInput() {
		    System.out.println("Please enter the date and time (yyyy-MM-dd HH:mm): ");
		    String input = scanner.nextLine();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		    return LocalDateTime.parse(input, formatter);
		}
		private static String formatDateTime(LocalDateTime dateTime) {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		    return dateTime.format(formatter);
		}
		
}
