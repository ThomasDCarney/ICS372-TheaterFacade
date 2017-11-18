/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Description: Group Project #1, A simple theater management system. <br>
 * Due: 10/16/2015 <br><br>
 * 
 * An adapter class used by the theater to organize sales for any given date.
 * 
 * @author Micah Sidley, Mark Scherr, Tom Carney
 * @version 1.0
 * @since 10/03/2015
 */

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;

public class SalesList extends ItemList<Sales, Calendar> {

    /**
     * This class maintains a collection of credit cards
     */
    public SalesList() {

        super();

    } // end CreditCardList constructor
    
    
    /**
     * This method is used to check how many tickets have been sold for a 
     * particular date.
     * 
     * @param date - The date we want the number of ticket sales for.
     * 
     * @return - An integer stating the number of sales.
     */
    public int checkHeadCount(Calendar date) {
        
        Iterator<Sales> iterator = list.iterator(); 
        
        while(iterator.hasNext()) {
            
            Sales tempSales = iterator.next();
            
            if (tempSales.matches(date)) {

                return tempSales.getHeadCount();

            }
            
        }
        
        return 0;

    } // end removeCustomerCards
    
    
} // end CreditCardList