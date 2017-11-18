/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Description: Group Project #1, A simple theater management system. <br>
 * Due: 10/16/2015 <br><br>
 * 
 * This class represents a single customer. Customers purchase tickets from
 * the theater and attend shows.
 * 
 * @author Micah Sidley, Mark Scherr, Tom Carney
 * @version 1.0
 * @since 10/03/2015
 */

import java.io.Serializable;
import java.util.Iterator;

public class Customer implements Serializable, Matchable<Integer> {

    private String          name;
    private String          address;
    private String          phone;
    private Integer         customerID;
    private CreditCardList  cardList;

    
    /**
     * Represents a single customer.
     * 
     * @param name - Name of the customer.
     * @param address - Address of the customer.
     * @param phone - Contact (phone) number of the customer.
     * @param clientID - A unique identification number assigned to the customer.
     */
    public Customer(String name, String address, String phone, int customerID) {

        this.name = name;
        this.address = address;
        this.phone = phone;
        this.customerID = customerID; // provided by Theater
        cardList = new CreditCardList();

    } // end Customer constructor
    
    
    /**
     * This method returns the customers unique identifier.
     * 
     * @return - The clients unique identifier.
     */
    public int getID() {
        
        return customerID;
        
    } // end getID
    
    
    /**
     * Customers pay for tickets via credit cards. This method adds a payment type
     * to the customer's account.
     * 
     * @param creditCard - The credit card to add to the customer's account.

     * @return A boolean true if show is added, false if some error occurred.
     */
    public boolean addCreditCard(CreditCard creditCard) {
        
        return cardList.insertElement(creditCard);
        
    } // end addCreditCard
    
    
    /**
     * This method will inform the caller how many cards are attached to the 
     * customer's account.
     * 
     * @return An int stating how many cards the customer has on file.
     */
    public int numberOfCreditCards() {
        
        return cardList.size();
        
    } // end numberOfCreditCards
    
    
    /**
     * This method will remove a credit card from the customer's account.
     * 
     * @param ccNumber
     * @return
     */
    public boolean removeCreditCard(String ccNumber) {
        
        return cardList.removeElement(ccNumber);
        
    } // end removeCreditCard
    
    
    /**
     * This method will determine if an element with provided identifier matches this one.
     * 
     * @param identifier - The identifier to check against.
     */
    @Override
    public boolean matches(Integer identifier) {
        
        return this.customerID.equals(identifier);
        
    } // end matches
    
    
    /**
     * This method will return a string representation of the customer.
     * 
     * @return - A string representation of the customer.
     */
    @Override
    public String toString() {
        
        Iterator<CreditCard> iterator = cardList.getItemIterator();
        
        String answer = "Name: " + name + ", Address: " + address + ", Phone: " + phone + 
                        ", ID: " + customerID;
        
        while(iterator.hasNext()) {
            
            answer += "\n\t" + iterator.next();
            
        }
        
        return  answer;
        
    } // end toString

} // end CreditCard