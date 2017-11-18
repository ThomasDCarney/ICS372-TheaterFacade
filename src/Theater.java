/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Description: Group Project #1, A simple theater management system. <br>
 * Due: 10/16/2015 <br><br>
 * 
 * This is the Theater class, it is a facade used to break up the UI and 
 * business logic.
 * 
 * @author Micah Sidley, Mark Scherr, Tom Carney
 * @version 1.0
 * @since 10/03/2015
 */

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;

public class Theater implements Serializable {

    public static final int OPERATION_FAILED = 0;
    public static final int OPERATION_COMPLETED = 1;
    public static final int SHOWS_PENDING = 2;
    public static final int CLIENT_NOT_FOUND = 3;
    public static final int DATES_NOT_AVAILABLE = 4;
    public static final int CUSTOMER_NOT_FOUND = 5;
    public static final int DUPLICATE_CREDIT_CARD = 6;
    public static final int CARD_NOT_ADDED = 7;
    public static final int CARD_NOT_FOUND = 8;
    public static final int NEED_PAYMENT_METHOD = 9;
    
    private String name;
    private int capacity;
    
    private ItemList<Client, Integer> clientList;
    private int nextClientID;
    
    private ItemList<Customer, Integer> customerList;
    private int nextCustomerID;
    
    private ShowList showList;
    private CreditCardList creditCardList;
    
    private SalesList salesList;
    
    
    /**
     * This is a facade linking the UI to business logic, maintaining several
     * collections and providing a imple interface between the two.
     * 
     * @param name - A name for the theater.
     * @param capacity - The seating capacity
     */
    public Theater (String name, int capacity) {
        
        this.name = name;
        this.capacity = capacity;
        
        clientList = new ItemList<Client, Integer>();
        nextClientID = 1;
        
        customerList = new ItemList<Customer, Integer>();
        nextCustomerID = 1;
        
        showList = new ShowList();
        creditCardList = new CreditCardList();
        
        salesList = new SalesList();
        
    } // end Theater constructor
    
    
    /**
     * This method will return the name of the theater.
     * 
     * @return The theater's name.
     */
    public String getName() {

        return name;

    } // end getName
    
    
    /**
     * This will return the theaters capacity.
     * 
     * @return - The theater's capacity.
     */
    public int getCapacity () {
        
        return capacity;
        
    } // end getCapacity
    
    
    /**
     * This method facilitates adding a client.
     * 
     * @param name - The clients name.
     * @param address - The clients address.
     * @param phone - The clients phone.
     * 
     * @return If successfull, a reference to the client, otherwise null.
     */
    public Client addClient(String name, String address, String phone) {
        
        Client client = new Client(name, address, phone, nextClientID++);
        if(clientList.insertElement(client)) {
            
            return client;
            
        }
            
        return null;
        
    } // end addClient
    
    
    /**
     * This method facilitates the removal of a client.
     * 
     * @param clientID - Unique identifier for the client to remove.
     * 
     * @return A code representing the outcome.
     */
    public int removeClient(int clientID) {
        
        if(showList.showsPending(clientID)) {
            
           return SHOWS_PENDING; 
            
        }
        
        if(!clientList.removeElement(clientID)) {
            
            return CLIENT_NOT_FOUND;
            
        }
        
        return OPERATION_COMPLETED;
        
    } // end removeClient
    
    
    /**
     * This method facilitates outputting a list of clients.
     * 
     * @return An iterator to the collection of clients.
     */
    public Iterator<Client> getClients() {
        
        return clientList.getItemIterator();
        
    } // end getClients
    
    
    /**
     * This method facilitate adding a show.
     * 
     * @param title - The shows title.
     * @param clientID - The performing client's unique identifier.
     * @param startDate - The requested initial performance date.
     * @param endDate - The requested final performance date.
     * @param price - The full at the door ticket price.
     * 
     * @return A reference to the show if added, otherwise null.
     */
    public Show addShow(String title, int clientID, 
                        Calendar startDate, Calendar endDate, 
                        float price) {
        
        if(startDate.after(endDate)) { // if dates are in the wrong order, swap
            
            Calendar tempDate = startDate;
            startDate = endDate;
            endDate = tempDate;
            
        }
        
        if(!showList.checkDates(startDate, endDate)) {
            
            // dates not available
            return null;
            
        }
        
        Client performingClient = clientList.getElement(clientID);
        if(performingClient != null) { // client was found
            
            Show show = new Show(title, performingClient, startDate, endDate, price);
            if(showList.insertElement(show)
               && performingClient.addShow(show)) {
                
                return show;
                
            }
            
        }
        
        // dates available but something else failed
        return null;
        
    } // end addShow
    
    
    /**
     * This method facilitates outputting a list of all shows.
     * 
     * @return An iterator to the show collection.
     */
    public Iterator<Show> getShows() {
        
        return showList.getItemIterator();
        
    } // end getShows
    
    
    /**
     * This method facilitates adding a customer.
     * 
     * @param name - The customers name.
     * @param address - The customers address.
     * @param phone - The customers phone number.
     * @param ccNumber - The number for an initial payment method.
     * @param expDate - The payement methods experation date.
     * 
     * @return A reference to the customer if it was added, otherwise null.
     */
    public Customer addCustomer(String name, String address, String phone, 
                                 String ccNumber, Calendar expDate) {
        
        if(creditCardList.searchElements(ccNumber)){
            
           return null; // duplicate cards not allowed 
            
        }
        
        Customer customer = new Customer(name, address, phone, nextCustomerID++);
        if(customer != null) {

            if(customerList.insertElement(customer)) {
                
                CreditCard creditCard = new CreditCard(ccNumber, expDate, customer);
                if(creditCardList.insertElement(creditCard)) {
                
                    customer.addCreditCard(creditCard);
                    
                }
                
            }
            
        }
        
        return customer;
        
    } // end addCustomer
    
    
    /**
     * This method facilitates removal of a customer.
     * 
     * @param customerID - Unique identifier of the customer to remove.
     * 
     * @return A code representing the outcome.
     */
    public int removeCustomer(int customerID) {
        
        Customer tempCustomer = customerList.getElement(customerID);
        if(tempCustomer == null) {
            
            return CUSTOMER_NOT_FOUND;
            
        }
        
        customerList.removeElement(customerID);
        creditCardList.removeCustomerCards(customerID);
        return OPERATION_COMPLETED;
        
    } // end removeCustomer
    
    
    /**
     * This method facilitates adding a credit card to a customer account.
     * 
     * @param customerID - The customers unique identifier.
     * @param ccNumber - The payment methods unique identifier.
     * @param expDate - The payment methods experiation date.
     * 
     * @return A code representing the outcome.
     */
    public int addCreditCard(int customerID, String ccNumber, Calendar expDate) {
        
        if(creditCardList.searchElements(ccNumber)) {
           
            return Theater.DUPLICATE_CREDIT_CARD;
            
        }
        
        Customer tempCustomer = customerList.getElement(customerID);
        if(tempCustomer == null) {
            
            return Theater.CUSTOMER_NOT_FOUND;
            
        }
        
        CreditCard  creditCard = new CreditCard(ccNumber, expDate, tempCustomer);
        if(!creditCardList.insertElement(creditCard)) {
            
            return Theater.CARD_NOT_ADDED;
            
        }
        
        tempCustomer.addCreditCard(creditCard);
        return Theater.OPERATION_COMPLETED;
        
    } // end addCreditCard
    
    
    /**
     * This method facilitates removing a credit card.
     * 
     * @param ccNumber - The unique identifier for the card to remove.
     * 
     * @return A code representing the outcome.
     */
    public int removeCreditCard(String ccNumber) {
        
        CreditCard tempCard = creditCardList.getElement(ccNumber);
        if(tempCard == null) {
            
            return Theater.CARD_NOT_FOUND;
            
        }
        
        if(tempCard.getCardHolder().numberOfCreditCards() < 2) {
            
            return Theater.NEED_PAYMENT_METHOD;
            
        }
        
        creditCardList.removeElement(ccNumber);
        tempCard.getCardHolder().removeCreditCard(ccNumber);
        
        return Theater.OPERATION_COMPLETED;
        
    } // end removeCreditCard
    
    
    /**
     * This method facilitates outputting a list of all credit cards.
     * 
     * @return An iterator for the collection of credit cards.
     */
    public Iterator<CreditCard> getCreditCards() {
        
        return creditCardList.getItemIterator();
        
    } // end getCreditCards
    
    
    /**
     * This method facilitates outputting a list of all customers.
     * 
     * @return An iterator for the collection of customers.
     */
    public Iterator<Customer> getCustomers() {
        
        return customerList.getItemIterator();
        
    } // end getShows
    
    
    public Customer sellRegularTicket(int quantity, int customerID, 
                                      String ccNumber, Calendar date) {
        
        int seatsFilled = salesList.checkHeadCount(date);
        if(capacity >= (seatsFilled + quantity)) {
            
            System.out.println(seatsFilled);
            return null;
            
        } else {
            
            return null;
            
        }
        
    }
    
} // end Theater