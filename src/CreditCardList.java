/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Description: Group Project #1, A simple theater management system. <br>
 * Due: 10/16/2015 <br><br>
 * 
 * An adapter class used by the theater to organize a collection of credit cards
 * used to pay for attending performances.
 * 
 * @author Micah Sidley, Mark Scherr, Tom Carney
 * @version 1.0
 * @since 10/03/2015
 */

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class CreditCardList extends ItemList<CreditCard, String> {

    /**
     * This class maintains a collection of credit cards
     */
    public CreditCardList() {

        super();

    } // end CreditCardList constructor
    
    
    /**
     * This method is used to remove all credit cards for a specific customer.
     * 
     * @param customerID - The customers unique identifier.
     * 
     * @return - A boolean true if the customer were removed. 
     */
    public boolean removeCustomerCards(int customerID) {
        
        Iterator<CreditCard> iterator = list.iterator(); 
        
        while(iterator.hasNext()) {
            
            if (iterator.next().getCardHolder().matches(customerID)) {

                iterator.remove();

            }
            
        }
        
        return true;

    } // end removeCustomerCards
    
    
} // end CreditCardList