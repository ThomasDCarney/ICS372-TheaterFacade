/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Description: Group Project #1, A simple theater management system. <br>
 * Due: 10/16/2015 <br><br>
 * 
 * This class represents a single client. Clients perform shows at the theater.
 * 
 * @author Micah Sidley, Mark Scherr, Tom Carney
 * @version 1.0
 * @since 10/03/2015
 */

import java.io.Serializable;
import java.text.DecimalFormat;

public class Client implements Serializable, Matchable<Integer> {
    
    private String      name;
    private String      address;
    private String      phone;
    private Integer     clientID;
    private double      balance;
    private ShowList    showList;

    
    /**
     * Represents a single client.
     * 
     * @param name - Name of the client (possibly organization name).
     * @param address - Address of the client.
     * @param phone - Contact (phone) number of the client.
     * @param clientID - A unique identification number assigned to the client.
     */
    public Client(String name, String address, String phone, int clientID) {

        this.name = name;
        this.address = address;
        this.phone = phone;
        this.clientID = clientID; // provided by Theater
        this.balance = 0.0; // initially $0.00 per project doc
        showList = new ShowList();

    } // end Client constructor
    
    
    /**
     * This method returns the clients unique identifier.
     * 
     * @return - The clients unique identifier.
     */
    public int getID() {
        
        return clientID;
        
    } // end getID
    
    
    /**
     * This method will return the clients name.
     * 
     * @return - The client's name.
     */
    public String getName() {
        
        return name;
        
    } // end getName
    
    
    /**
     * Clients perform shows, this method will add a show to the clients
     * account.
     * 
     * @param show - The show the client will be performing.

     * @return A boolean true if show is added, false if some error occurred.
     */
    public boolean addShow(Show show) {
        
        return showList.insertElement(show);
        
    } // end addShow


    /**
     * This method will determine if an element with provided identifier matches this one.
     * 
     * @param identifier - The identifier to check against.
     */
    @Override
    public boolean matches(Integer identifier) {
        
        return this.clientID.equals(identifier);
        
    } // end matches
    
    
    /**
     * This method will return a string representation of the client.
     * 
     * @return - a string representation of the client.
     */
    @Override
    public String toString() {
        
        DecimalFormat dollars = new DecimalFormat("#0.00");
        
        return "Client Name: " + name + ", Address: " + address + ", Phone: " + phone +
                ", ID: " + clientID + ", Balance: $" + dollars.format(balance);
        
    } // end toString
    
    
} // end Client