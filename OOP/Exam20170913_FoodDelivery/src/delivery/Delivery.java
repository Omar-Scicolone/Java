package delivery;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

import main.Customer;
import main.Item;

public class Delivery {
    
	List<Customer> customers = new ArrayList<>();
	List<Item> items = new ArrayList<>();

	int id = 0;
	
	
    public static enum OrderStatus { NEW, CONFIRMED, PREPARATION, ON_DELIVERY, DELIVERED } 
   
    public int newCustomer(String name, String address, String phone, String email) throws DeliveryException {
    	for (Customer c : customers) {
    		if(c.getEmail().equals(email)) {
    			throw new DeliveryException();
    		}
    	}
    	id++;
    	
    	Customer c = new Customer(name, id, address, phone, email);
    	customers.add(c);
        return id;
    }
    
    
    public String customerInfo(int customerId){
   	
        return customers.get(customerId-1).getName() + ", " + 
        		customers.get(customerId-1).getAddress() + ", " +  customers.get(customerId-1).getPhone() + ", " + 
        		customers.get(customerId-1).getEmail();
    }
    
  
    public List<String> listCustomers(){
    	List <String> listaInfo = new ArrayList<>();
    	List <Customer> listaCust = new ArrayList<>();
    	
    	listaCust = customers.stream().sorted(Comparator.comparing(Customer::getName)).collect(Collectors.toList());
    	
    	for (Customer c : listaCust) {
    		listaInfo.add(c.getName() + ", " + c.getAddress() + ", " +  c.getPhone() + ", " + c.getEmail());
    		
    	}
    
        return listaInfo;
    }
    

    public void newMenuItem(String description, double price, String category, int prepTime){
        
    	Item i = new Item(description, price, category, prepTime);
    	items.add(i);
    	
    }
    
    
    public List<String> findItem(String search){
    	List <Item> oggetti = new ArrayList<>();
    	List <String> stringhe = new ArrayList<>();
    	
    	oggetti = items.stream().filter( i -> i.getDescription().toLowerCase().contains(search)).collect(Collectors.toList());    	
        oggetti = oggetti.stream().
        		sorted(Comparator.comparing(Item::getCategory).
        				thenComparing(Comparator.comparing(Item::getDescription))).
        		collect(Collectors.toList());
    	    	
    	for (Item i : oggetti) {
    		stringhe.add(  "[" + i.getCategory() + "] " + i.getDescription() + " : " + i.getPrice() );
    	}
    	
    	return stringhe;
    }
    
    /**
     * Creates a new order for the given customer.
     * Returns the unique id of the order, a progressive
     * integer number starting at 1.
     * 
     * @param customerId
     * @return order id
     */
    public int newOrder(int customerId){
        return -1;
    }
    
    /**
     * Add a new item to the order with the given quantity.
     * 
     * If the same item is already present in the order is adds the
     * given quantity to the current quantity.
     * 
     * The method returns the final total quantity of the item in the order. 
     * 
     * The item is found through the search string as in {@link #findItem}.
     * If none or more than one item is matched, then an exception is thrown.
     * 
     * @param orderId the id of the order
     * @param search the item search string
     * @param qty the quantity of the item to be added
     * @return the final quantity of the item in the order
     * @throws DeliveryException in case of non unique match or invalid order ID
     */
    public int addItem(int orderId, String search, int qty) throws DeliveryException {
        return -1;
    }
    
    /**
     * Show the items of the order using the following format
     * <pre>
     * "DESCRIPTION, QUANTITY"
     * </pre>
     * 
     * @param orderId the order ID
     * @return the list of items in the order
     * @throws DeliveryException when the order ID in invalid
     */
    public List<String> showOrder(int orderId) throws DeliveryException {
        return null;
    }
    
    /**
     * Retrieves the total amount of the order.
     * 
     * @param orderId the order ID
     * @return the total amount of the order
     * @throws DeliveryException when the order ID in invalid
     */
    public double totalOrder(int orderId) throws DeliveryException {
        return -1.0;
    }
    
    /**
     * Retrieves the status of an order
     * 
     * @param orderId the order ID
     * @return the current status of the order
     * @throws DeliveryException when the id is invalid
     */
    public OrderStatus getStatus(int orderId) throws DeliveryException {
        return null;
    }
    
    /**
     * Confirm the order. The status goes from {@code NEW} to {@code CONFIRMED}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>start-up delay (conventionally 5 min)
     * <li>preparation time (max of all item preparation time)
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code NEW}
     */
    public int confirm(int orderId) throws DeliveryException {
        return -1;
    }

    /**
     * Start the order preparation. The status goes from {@code CONFIRMED} to {@code PREPARATION}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>preparation time (max of all item preparation time)
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code CONFIRMED}
     */
    public int start(int orderId) throws DeliveryException {
        return -1;
    }

    /**
     * Begins the order delivery. The status goes from {@code PREPARATION} to {@code ON_DELIVERY}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code PREPARATION}
     */
    public int deliver(int orderId) throws DeliveryException {
        return -1;
    }
    
    /**
     * Complete the delivery. The status goes from {@code ON_DELIVERY} to {@code DELIVERED}
     * 
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code ON_DELIVERY}
     */
    public void complete(int orderId) throws DeliveryException {
    }
    
    /**
     * Retrieves the total amount for all orders of a customer.
     * 
     * @param customerId the customer ID
     * @return total amount
     */
    public double totalCustomer(int customerId){
        return -1.0;
    }
    
    /**
     * Computes the best customers by total amount of orders.
     *  
     * @return the classification
     */
    public SortedMap<Double,List<String>> bestCustomers(){
        return null;
    }
    
// NOT REQUIRED
//
//    /**
//     * Computes the best items by total amount of orders.
//     *  
//     * @return the classification
//     */
//    public List<String> bestItems(){
//        return null;
//    }
//    
//    /**
//     * Computes the most popular items by total quantity ordered.
//     *  
//     * @return the classification
//     */
//    public List<String> popularItems(){
//        return null;
//    }

}
