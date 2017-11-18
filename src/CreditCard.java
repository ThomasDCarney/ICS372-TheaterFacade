/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Description: Group Project #1, A simple theater management system. <br>
 * Due: 10/16/2015 <br><br>
 * 
 * This class represents a single credit card. Customers use credit cards to
 * pay for tickets in order to attend shows.
 * 
 * @author Micah Sidley, Mark Scherr, Tom Carney
 * @version 1.0
 * @since 10/03/2015
 */

import java.io.Serializable;
import java.util.Calendar;

public class CreditCard implements Serializable, Matchable<String> {

    private String      ccNumber;
    private Calendar    expDate;
    private Customer    cardHolder;

    
    /**
     * Represents a single credit card.
     * 
     * @param ccNumber - The credit cards number, unique id given by the backer.
     * @param expDate - The date the card expires.
     * @param cardHolder - A reference to the cardHolder.
     */
    public CreditCard(String ccNumber, Calendar expDate, Customer cardHolder) {

        this.ccNumber = ccNumber;
        this.expDate = expDate;
        this.cardHolder = cardHolder;

    } // end Client constructor
    
    
    /**
     * This method returns the credit cards unique identifier.
     * 
     * @return - The credit card's unique identifier.
     */
    public String getNumber() {
        
        return ccNumber;
        
    } // end getNumber
    
    
    /**
     * This method will return a reference to the credit card's owner.
     * 
     * @return - A reference to the credit card's owner.
     */
    public Customer getCardHolder() {
        
        return cardHolder;
        
    } // end getCardHolder
    
    
    /**
     * This method will determine if an element with provided identifier matches this one.
     * 
     * @param identifier - The identifier to check against.
     */
    @Override
    public boolean matches(String identifier) {
        
        return this.ccNumber.equals(identifier);
        
    } // end matches
    
    
    /**
     * This method will return a string representation of the credit card.
     * 
     * @return - A string representation of the credit card.
     */
    @Override public String toString() {
        
        return "Card Number: " + ccNumber + ", Expiration Date: " + 
                expDate.get(Calendar.YEAR) + "/" +
                (expDate.get(Calendar.MONTH) + 1) + "/" +
                expDate.get(Calendar.DAY_OF_MONTH) + ".";
        
    } // end toString

} // end CreditCard