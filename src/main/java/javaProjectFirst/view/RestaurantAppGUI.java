package javaProjectFirst.view;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import javaProjectFirst.enums.Location;
import javaProjectFirst.enums.OrderItemSize;
import javaProjectFirst.main.model.Client;
import javaProjectFirst.main.model.Menu;
import javaProjectFirst.main.model.Restaurant;
import javaProjectFirst.order.controller.MenuImporter;
import javaProjectFirst.order.controller.OrderProcessor;
import javaProjectFirst.order.model.Order;
import javaProjectFirst.order.model.OrderItem;
import javaProjectFirst.product.model.Drink;
import javaProjectFirst.product.model.Meal;
import javaProjectFirst.product.model.Product;
import java.util.ArrayList;


public class RestaurantAppGUI {

	private JFrame frame;
	private final String MENU_FILE_PATH = "/menu.txt";
	private DefaultTableModel menuDataTableModel = new DefaultTableModel();
	private JTable orderItemTable;
	private MenuImporter menuImporter = new MenuImporter();
	private JComboBox<String> orderItemSizeSelector;
	private DefaultTableModel orderDataTableModel = new DefaultTableModel();
	private JTable menuTable;
	private JTextField quantityTextField;
	private JRadioButton mealRadioButton;
	private JRadioButton drinkRadioButton;
	private JRadioButton allRadioButton;
    private JComboBox<Location> locationSelector;
    private JTextField clientNameInputTextField;
    private JTextField phoneNumberInputTextField;
    private JLabel invoiceJLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestaurantAppGUI window = new RestaurantAppGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public RestaurantAppGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		JLabel applicationNameLabel = new JLabel("KFC");
		applicationNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.add(applicationNameLabel, BorderLayout.NORTH);
		frame.getContentPane().add(createOrderInputPanel());
		frame.getContentPane().add(createOrderPanel());
		frame.getContentPane().add(createInvoicePanel());
		frame.getContentPane().add(createAddButton());
		frame.getContentPane().add(createOrderButton());
		frame.getContentPane().add(createDeleteButton());
		frame.getContentPane().add(createRefreshButton());
	}

	private JPanel createOrderInputPanel() {
		JPanel orderInputPanel = new JPanel();
		orderInputPanel.setLayout(null);
		orderInputPanel.setBounds(10, 10, 340, 340);
	
		orderInputPanel.add(createOrderItemSizeLabel());
		orderInputPanel.add(createQuantityInputLabel());
		orderInputPanel.add(createOrderItemSizeSelector());
		orderInputPanel.add(createQuantityInputTextField());
		createRadioButtonsMenuFilter(orderInputPanel);
		orderInputPanel.add(createMenuTablePanel());
		return orderInputPanel;
	}

	private JLabel createOrderItemSizeLabel() {

		JLabel orderItemSizeLabel = new JLabel("Order Item Size: ");
		orderItemSizeLabel.setBounds(10, 0, 120, 30);
		return orderItemSizeLabel;
	}

	private JLabel createQuantityInputLabel() {

		JLabel quantityInputLabel = new JLabel("Quantity: ");
		quantityInputLabel.setBounds(180, 0, 120, 30);
		return quantityInputLabel;
	}

	private JComboBox<String> createOrderItemSizeSelector() {

		orderItemSizeSelector = new JComboBox<>();

		orderItemSizeSelector.setBounds(10, 30, 140, 30);
		for (OrderItemSize orderItemSize : OrderItemSize.values()) {
			orderItemSizeSelector.addItem(orderItemSize.name());
		}
		return orderItemSizeSelector;

	}

	private JTextField createQuantityInputTextField() {
		quantityTextField = new JTextField();
		quantityTextField.setBounds(180, 30, 120, 30);
		return quantityTextField;
	}

	private void createRadioButtonsMenuFilter(JPanel orderInputPanel) {
		mealRadioButton = new JRadioButton("Meal");
		drinkRadioButton = new JRadioButton("Drink");
		allRadioButton = new JRadioButton("All");

		allRadioButton.setBounds(10, 70, 100, 30);
		mealRadioButton.setBounds(110, 70, 100, 30);
		drinkRadioButton.setBounds(210, 70, 100, 30);
		allRadioButton.setSelected(false);

		ButtonGroup menuFilterRadioButtonGroup = new ButtonGroup();
		menuFilterRadioButtonGroup.add(allRadioButton);
		menuFilterRadioButtonGroup.add(mealRadioButton);
		menuFilterRadioButtonGroup.add(drinkRadioButton);

		orderInputPanel.add(allRadioButton);
		orderInputPanel.add(mealRadioButton);
		orderInputPanel.add(drinkRadioButton);
		
		
		
		
		
		allRadioButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        prepareMenuDataForTable("All");
		    }
		});

		mealRadioButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        prepareMenuDataForTable("Meal");
		    }
		});

		drinkRadioButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        prepareMenuDataForTable("Drink");
		    }
		});	
		
	}

	private JScrollPane createMenuTablePanel() {
		String selectedCategory = getSelectedCategory();
		prepareMenuDataForTable(selectedCategory);
		menuTable = new JTable(menuDataTableModel);

		JScrollPane scrollPane = new JScrollPane(menuTable);
		scrollPane.setBorder(BorderFactory.createTitledBorder("KFC MENU"));
		scrollPane.setBounds(10, 100, 300, 200);
		return scrollPane;

	}

	private String getSelectedCategory() {

		if (allRadioButton.isSelected()) {
			return "All";
		} else if (mealRadioButton.isSelected()) {
			return "Meal";
		} else if (drinkRadioButton.isSelected()) {
			return "Drink";
		}

		return "All";
	}

	private DefaultTableModel prepareMenuDataForTable(String category) {

		String[][] menuArray = createMenuArray(category);

		String[] tableHeader = { "Id", "Name", "Price" };
		menuDataTableModel.setDataVector(menuArray, tableHeader);
		return menuDataTableModel;
	}

	private String[][] createMenuArray(String category) {
	    Menu menu = menuImporter.importMenu(MENU_FILE_PATH);
	    HashMap<Integer, Product> menuItems = menu.getMenuItems();
	    ArrayList<String[]> filteredMenu = new ArrayList<>();
	    
	    for (Entry<Integer, Product> menuItem : menuItems.entrySet()) {
	        Product product = menuItem.getValue();
	        if (category == null || category.equals("All") || (category.equals("Meal")  
	        		&& product instanceof Meal) || (category.equals("Drink") && product instanceof Drink)) {
	        	
	        	 String[] productData = new String[3];
	        	
	        	productData[0] = Integer.toString(product.getProductId());
	        	productData[1] = product.getName();
	        	productData[2] = Double.toString(product.getPrice());
	        	filteredMenu.add(productData);	
	        }
	        	
	    }
	    
	    
	    String[][] menuArray = new String[filteredMenu.size()][3];
	   
	    return filteredMenu.toArray(menuArray);
	}
	

	
	private JButton createAddButton() {
	    JButton addButton = new JButton("Add");
	    addButton.setBounds(20, 370, 100, 40);

	    addButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            int selectedRow = menuTable.getSelectedRow();

	            if (selectedRow != -1) {
	            	if (validQuantity() ) {
	            		
	            	
	                String[] menuItemData = new String[5];
	                menuItemData[0] = menuTable.getValueAt(selectedRow, 0).toString(); 
	                menuItemData[1] = menuTable.getValueAt(selectedRow, 1).toString(); 
	                menuItemData[2] = menuTable.getValueAt(selectedRow, 2).toString(); 

	               
	                menuItemData[3] = quantityTextField.getText(); 
	                menuItemData[4] = orderItemSizeSelector.getSelectedItem().toString(); 

	                orderDataTableModel.addRow(menuItemData);
	                quantityTextField.setText("");
	                orderItemSizeSelector.setSelectedIndex(0);
	                menuTable.clearSelection();
	            	}
	            } else {
	                JOptionPane.showMessageDialog(null, "Select any row to add");
	            }
	        }
	    });

	    return addButton;
	}
	private JButton createDeleteButton() {
	    JButton deleteButton = new JButton("Delete");
	    deleteButton.setBounds(450, 370, 100, 40);
	    deleteButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            int selectedRow = orderItemTable.getSelectedRow();
	            if (selectedRow != -1) {
	                orderDataTableModel.removeRow(selectedRow);
	            } else {
	                JOptionPane.showMessageDialog(null, "Select any row to delete!");
	            }
	        }
	    });
	    return deleteButton;
	}
	
	
	private JPanel createOrderPanel() {
		JPanel orderPanel = new JPanel();
		orderPanel.setLayout(null);
		orderPanel.setBounds(350 , 10 , 420 , 350);
		orderPanel.add(createClientNameLabel());
		orderPanel.add(createPhoneNumberLabel());
		orderPanel.add(createLocationLabel());
		orderPanel.add(createClientNameInputTextField());
		orderPanel.add(createPhoneNumberInputTextField());
		orderPanel.add(createLocationSelector());
		orderPanel.add( createOrderTableScrollPane());
		
		return orderPanel;
	}
	private JLabel createClientNameLabel() {
		JLabel clientNameLabel = new JLabel("Name: ");
		clientNameLabel.setBounds(10 , 10 , 100 , 30);
		return clientNameLabel;
	}
	private JLabel createPhoneNumberLabel() {
		JLabel phoneNumberLabel = new JLabel("Phone Nr: ");
		phoneNumberLabel.setBounds(10 , 60 , 100 , 30);
		return phoneNumberLabel;
	}
	private JLabel createLocationLabel() {
		JLabel locationLabel = new JLabel("Location: ");
		locationLabel.setBounds(300 , 10 , 100 , 30);
		return locationLabel;
	}
	
	
	private JTextField createClientNameInputTextField() {
	    clientNameInputTextField = new JTextField();
	    clientNameInputTextField.setBounds(70, 10, 200, 30);
	    return clientNameInputTextField;
	}

	private JTextField createPhoneNumberInputTextField() {
	    phoneNumberInputTextField = new JTextField();
	    phoneNumberInputTextField.setBounds(70, 60, 200, 30);
	    return phoneNumberInputTextField;
	}

	private JComboBox<Location> createLocationSelector() {
	    locationSelector = new JComboBox<>();

	  
	    for (Location location : Location.values()) {
	        locationSelector.addItem(location);
	    }

	    
	    locationSelector.setBounds(300, 50, 100, 30);

	    return locationSelector;
	}
	private JScrollPane createOrderTableScrollPane() {
		prepareOrderDataForTable();
		orderItemTable = new JTable(orderDataTableModel);
		JScrollPane scrollPane = new JScrollPane(orderItemTable);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Order Items: "));
		scrollPane.setBounds(10 , 100 , 400 , 200);
		return scrollPane;
		
	}
	private void prepareOrderDataForTable() {
		
		String[][] orderItemsArray = new String[0][5];
		String [] tableHeader = {"ID" , "Name" , "Price" ,  "Quantity" , "Size"};
		orderDataTableModel.setDataVector(orderItemsArray, tableHeader);
	}
	private JButton createOrderButton() {
	    JButton orderButton = new JButton("Order");
	    orderButton.setBounds(340, 370, 100, 40);
	    orderButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if(orderDataTableModel.getRowCount() == 0) {
	                JOptionPane.showMessageDialog(null, "No products in the order. Please add products to proceed!");
	                return;
	            }

	            Restaurant restaurant = new Restaurant("KFC", "Mitrovice");
	            Client client = new Client(clientNameInputTextField.getText(), phoneNumberInputTextField.getText());

	            OrderProcessor orderProcessor = new OrderProcessor();
	            Order order = new Order();
	            for(int i = 0; i < orderDataTableModel.getRowCount(); i++) {
	                int orderItemId = Integer.valueOf((String)orderDataTableModel.getValueAt(i, 0));
	                String orderItemName = (String)orderDataTableModel.getValueAt(i, 1);
	                Double orderItemPrice = Double.valueOf((String)orderDataTableModel.getValueAt(i, 2));

	                Product product = new Meal(orderItemId, orderItemName, orderItemPrice  );
	                int quantity = Integer.valueOf((String)orderDataTableModel.getValueAt(i, 3));
	                OrderItemSize orderItemSize = getOrderItemSize(orderDataTableModel.getValueAt(i, 4).toString());
	                
	                OrderItem orderItem = new OrderItem(product , orderItemSize , quantity);
	                order.getOrderItems().add(orderItem);
	                
	            }
	            invoiceJLabel.setText(orderProcessor.prepareInvoice(restaurant, client, order, getCurrentLocation()));
	            JOptionPane.showMessageDialog(null, "Order was done succesfully");
	            
	        }
	    });
	    return orderButton;
	}
	private OrderItemSize getOrderItemSize(String string) {
	    switch(string) {
	        case "SMALL":
	            return OrderItemSize.SMALL;
	        case "MEDIUM":
	            return OrderItemSize.MEDIUM;
	        case "LARGE":
	            return OrderItemSize.LARGE;
	        case "XXL":
	            return OrderItemSize.XXL;
	        default:
	            return OrderItemSize.MEDIUM;
	    }
	}
	
	private Location getCurrentLocation() {
		
		return (Location)locationSelector.getSelectedItem();
	}
	
	
	
	private JPanel createInvoicePanel() {
	    JPanel panel = new JPanel();
	    panel.setLayout(null);
	    panel.setBounds(770, 10, 330, 350);
	  
	    panel.add(createInvoiceLabelScrollPane());
	    return panel;
	}
	
	private JScrollPane createInvoiceLabelScrollPane() {
	    invoiceJLabel = new JLabel("The invoice will be printed here!");
	    invoiceJLabel.setBounds(15, 10, 300, 330);
	    JScrollPane labelScrollPane = new JScrollPane(invoiceJLabel);
	    labelScrollPane.setBounds(15, 10, 300, 330);
	    return labelScrollPane;
	}
	
	
	private JButton createRefreshButton() {
	    JButton refreshButton = new JButton("Refresh");
	    refreshButton.setBounds(750, 370, 100, 40);
	    refreshButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            prepareOrderDataForTable();
	            invoiceJLabel.setText("The invoice will be printed here!");
	            clientNameInputTextField.setText("");
	            phoneNumberInputTextField.setText("");
	            locationSelector.setSelectedIndex(0);
	            JOptionPane.showMessageDialog(null, "All inputs have been cleared!");
	        }
	    });
	    return refreshButton;
	}
	private boolean validQuantity() {
	    if(!quantityTextField.getText().isBlank()) {
	        if(quantityTextField.getText().matches("[0-9]+")) {
	            return true;
	        } else {
	            JOptionPane.showMessageDialog(null, "The text in quantity text field must contain only positive numbers!");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Please provide the quantity!");
	    }
	    return false;
	}
	
}
