/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Description: Group Project #1, A simple theater management system. <br>
 * Due: 10/16/2015 <br><br>
 * 
 * This is the UI to the theater facade. 
 * 
 * PLEASE NOTE, some of this code used in this project was pulled or referenced 
 * from the book example. We've tried to indicate which snippets directly belong 
 * to the books authors and do not claim it as our own.
 * 
 * @author Micah Sidley, Mark Scherr, Tom Carney
 * @version 1.0
 * @since 10/03/2015
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

public class UserInterface {
    
    private Theater theater;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(
            System.in));
    
    private static final int    EXIT                = 0;
    private static final int    ADD_CLIENT          = 1;
    private static final int    REMOVE_CLIENT       = 2;
    private static final int    LIST_CLIENTS        = 3;
    private static final int    ADD_CUSTOMER        = 4;
    private static final int    REMOVE_CUSTOMER     = 5;
    private static final int    LIST_CUSTOMERS      = 6;
    private static final int    ADD_CC              = 7;
    private static final int    REMOVE_CC           = 8;
    private static final int    ADD_SHOW            = 9;
    private static final int    LIST_SHOWS          = 10;
    private static final int    SAVE                = 11;
    private static final int    LOAD                = 12;
    private static final int    SELL_REGULAR_TICKET = 13;
    private static final int    SELL_ADVANCE_TICKET = 14;
    private static final int    SELL_STUDENT_TICKET = 15;
    private static final int    PAY_CLIENT          = 16;
    private static final int    PRINT_TICKETS       = 17;
    private static final int    HELP                = 18;
    //private static final int    LIST_CC             = 19;  // secret option used to debug
    
    
    /**
     * This will create the user interface and import data if requested.
     */
    public UserInterface() {
        
        if (yesOrNo("Look for saved data and  use it?")) {
            retrieveData();
        } else {
            theater = new Theater("Really Insanely Super Huge", 550);
        }
        
    } // end UserInterface constructor
    
    
    /**
     * Queries for a yes or no and returns true for yes and false for no
     * 
     * @param prompt
     *            The string to be prepended to the yes/no prompt
     * @return true for yes and false for no
     * 
     * @author Brahma Dathan and Sarnath Ramnath
     * @Copyright (c) 2010
     */
    private boolean yesOrNo(String prompt) {
        String more = getToken(prompt + " (Y|y)[es] or anything else for no");
        if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
            return false;
        }
        return true;
    }
    
    
    /**
     * This method is used to display a list of options to the user.
     */
    private void displayMenu() {

        System.out.println("Please select from below:\n");

        System.out.println(EXIT + "  to exit");
        System.out.println(ADD_CLIENT + "  to add a client");
        System.out.println(REMOVE_CLIENT + "  to remove a client");
        System.out.println(LIST_CLIENTS + "  to list clients");
        System.out.println(ADD_CUSTOMER + "  to add a customer");
        System.out.println(REMOVE_CUSTOMER + "  to remove a customer");
        System.out.println(LIST_CUSTOMERS + "  to list customers");
        System.out.println(ADD_CC + "  to add a credit card");
        System.out.println(REMOVE_CC + "  to remove a credit card");
        System.out.println(ADD_SHOW + "  to add a show");
        System.out.println(LIST_SHOWS + " to list shows");
        System.out.println(SAVE + " to save data");
        System.out.println(LOAD + " to load data");
        System.out.println(SELL_REGULAR_TICKET + "  to sell regular ticket");
        System.out.println(SELL_ADVANCE_TICKET + "  to sell advanced ticket");
        System.out.println(SELL_STUDENT_TICKET + " to sell student advanced ticket");
        System.out.println(PAY_CLIENT + " to pay client");
        System.out.println(PRINT_TICKETS + " to print tickets");
        System.out.println(HELP + " for help\n");

    } // displayMenu
    
    
    /**
     * Prompts for a command from the keyboard
     * 
     * @return a valid command
     * 
     * @author Brahma Dathan and Sarnath Ramnath
     * @Copyright (c) 2010
     */
    public int getCommand() {
        do {
            try {
                int value = Integer.parseInt(getToken("Enter command:" + HELP
                        + " for help"));
                if (value >= EXIT && value <= HELP + 1) {
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number");
            }
        } while (true);
    
    } // end getCommand
    
    
    /**
     * Gets a token after prompting
     * 
     * @param prompt
     *            - whatever the user wants as prompt
     * @return - the token from the keyboard
     * 
     * @author Brahma Dathan and Sarnath Ramnath
     * @Copyright (c) 2010
     */
    public String getToken(String prompt) {
        do {
            try {
                System.out.println(prompt);
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } catch (IOException ioe) {
                System.exit(0);
            }
        } while (true);
    
    } // end getToken
    
    
    /**
     * Converts the string to a number
     * 
     * @param prompt
     *            the string for prompting
     * @return the integer corresponding to the string
     * 
     * @author Brahma Dathan and Sarnath Ramnath
     * @Copyright (c) 2010
     */
    public int getNumber(String prompt) {
        do {
            try {
                String item = getToken(prompt);
                Integer number = Integer.valueOf(item);
                return number.intValue();
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a number ");
            }
        } while (true);
    } // end getNumber
    
    
    /**
     * Converts the string to a float, taken to represent a dollar value.
     * A modified version of Brahma Dathan and Sarnath Ramnath's getNumber
     * method.
     * 
     * @param prompt - the string for prompting.
     * 
     * @return - The float corresponding to the string.
     */
    public float getPrice(String prompt) {
        do {
            try {
                String item = getToken(prompt);
                Float number = Float.valueOf(item);
                return number.floatValue();
            } catch (NumberFormatException nfe) {
                System.out.println("Ticket Price: ");
            }
        } while (true);
    } // end getNumber
    
    
    /**
     * Prompts for a date and gets a date object
     * 
     * @param prompt
     *            the prompt
     * @return the data as a Calendar object
     * 
     * @author Brahma Dathan and Sarnath Ramnath
     * @Copyright (c) 2010
     */
    public Calendar getDate(String prompt) {
        do {
            try {
                Calendar date = new GregorianCalendar();
                String item = getToken(prompt);
                DateFormat dateFormat = SimpleDateFormat
                        .getDateInstance(DateFormat.SHORT);
                date.setTime(dateFormat.parse(item));
                return date;
            } catch (Exception fe) {
                System.out.println("Please input a date as mm/dd/yy");
            }
        } while (true);
    }
    
    
    /**
     * This method is used to prompt the user and add a client.
     */
    public void addClient() {
        
        System.out.println("Please enter the following Client information,");
        String name = getToken("Name: ");
        String address = getToken("Address: ");
        String phone = getToken("Phone: ");
        
        Client client = theater.addClient(name, address, phone);
        
        if(client == null) {
            
            System.out.println("Could not add client");
            
        } else {
            
            System.out.println(client);
            
        }
        
    } // end addClient
    
    
    /**
     * This method is used to prompt the user and remove a client.
     */
    public void removeClient() {
        
        System.out.println("To remove a client please provide the following,");
        int clientID = getNumber("Client ID: ");
        int result = theater.removeClient(clientID);
        
        switch (result) {
        case Theater.SHOWS_PENDING:
            System.out.println("Client has show's pending, cannot remove client!");
            break;
        case Theater.CLIENT_NOT_FOUND:
            System.out.println("No client found with ID provided!");
            break;
        case Theater.OPERATION_COMPLETED:
            System.out.println("Client has been removed!");
            break;
        default:
            System.out.println("An error has occurred");
        }
        
        
    } // end removeClient
    
    
    /** 
     * This method is used to output a list of all clients.
     */
    public void listClients() {

        Iterator<Client> iterator = theater.getClients();
        
        while(iterator.hasNext()) {
            
            System.out.println(iterator.next());
            
        }
        
    } // end listClients
    
    
    /**
     * This method is used to prompt the user and add a show.
     */
    public void addShow() {
        
        System.out.println("Please enter the following Show information,");
        String name = getToken("Title: ");
        int clientID = getNumber("Client ID: ");
        Calendar startDate = getDate("Start date mm/dd/yy: ");
        Calendar endDate = getDate("End date mm/dd/yy: ");
        float price = getPrice("Ticket Price: ");
        
        Show show = theater.addShow(name, clientID, startDate, endDate, price);
        
        if(show == null) {
            
            System.out.println("Could not add show!");
            
        } else {
            
            System.out.println(show);
            
        }
        
    } // end addShow
    
    
    /**
     * This method is used to output a list of all shows.
     */
    public void listShows() {
        
        Iterator<Show> iterator = theater.getShows();
        
        while(iterator.hasNext()) {
            
            System.out.println(iterator.next());
            
        }

    } // end listShows
    
    
    /**
     * This method is used to prompt the user and add a customer.
     */
    public void addCustomer() {
        
        System.out.println("Please enter the following Customer information,");
        String name = getToken("Name: ");
        String address = getToken("Address: ");
        String phone = getToken("Phone: ");
        String ccNumber = getToken("Credit Card Number: ");
        Calendar expDate = getDate("Expiration date mm/dd/yy:");
        
        Customer customer = theater.addCustomer(name, address, phone, ccNumber, expDate);
        
        if(customer == null) {
            
            System.out.println("Could not add customer!");
            
        } else {
        
            System.out.println(customer);
            
        }
        
    } // end addCustomer
    
    
    /**
     * This method is used to prompt the user and remove a customer.
     */
    public void removeCustomer() {
        
        System.out.println("Please provide the following, ");
        int customerID = getNumber("Customer ID: ");
        int result = theater.removeCustomer(customerID);
        
        switch (result) {
        case Theater.CUSTOMER_NOT_FOUND:
            System.out.println("No customer found with ID provided!");
            break;
        case Theater.OPERATION_COMPLETED:
            System.out.println("Customer has been removed!");
            break;
        default:
            System.out.println("An error has occurred");
        }
        
    } // end removeCustomer
    
    
    /**
     * This method is used to print a list of all customers.
     */
    public void listCustomers() {
        
        Iterator<Customer> iterator = theater.getCustomers();
        
        while(iterator.hasNext()) {
            
            System.out.println(iterator.next());
            
        }
        
    } // end listCustomers
    
    
    /**
     * This method is used to prompt the user and add a credit card.
     */
    public void addCreditCard() {
        
        System.out.println("Please provide the following, ");
        int customerID = getNumber("Customer ID: ");
        String ccNumber = getToken("Credit Card Number: ");
        Calendar expDate = getDate("Expiration date mm/dd/yy:");
        
        int result = theater.addCreditCard(customerID, ccNumber, expDate);
        
        switch (result) {
        case Theater.DUPLICATE_CREDIT_CARD:
            System.out.println("Card already exists, duplicates not allowed!");
            break;
        case Theater.CUSTOMER_NOT_FOUND:
            System.out.println("Customer not found with ID provided!");
            break;
        case Theater.CARD_NOT_ADDED:
            System.out.println("Card could not be added!");
            break;
        case Theater.OPERATION_COMPLETED:
            System.out.println("Card added successfully!");
            break;
        default:
            System.out.println("An error has occurred");
        }
        
    } // end addCreditCard
    
    
    /**
     * This method is used to prompt the user and remove a credit card.
     */
    public void removeCreditCard() {
        
        System.out.println("Please provide the following, ");
        String ccNumber = getToken("Credit Card Number: ");
        int result = theater.removeCreditCard(ccNumber);
        
        switch (result) {
        case Theater.CARD_NOT_FOUND:
            System.out.println("Card not found with that number!");
            break;
        case Theater.NEED_PAYMENT_METHOD:
            System.out.println("Please enter new card before removing last!");
            break;
        case Theater.OPERATION_COMPLETED:
            System.out.println("Card removed successfully!");
            break;
        default:
            System.out.println("An error has occurred");
        }
        
    } // removeCreditCard
    
    
    /**
     * This method is used to print a list of all credit cards.
     */
    public void listCreditCards() {
        
        Iterator<CreditCard> iterator = theater.getCreditCards();
        
        while(iterator.hasNext()) {
            
            System.out.println(iterator.next());
            
        }
        
    } // end listCreditCards
    
    
    /**
     * This method is used to backup/store current data.
     */
    public void storeData() {
        
        try {
            
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("theater.dat"));
            output.writeObject(theater);
            output.close();
            
            System.out.println("Data saved!");
            
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
            
        }
        
    } // storeData
    
    
    /**
     * This method is used to retrieve data from disk.
     */
    public void retrieveData() {
        
        try{
            
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("theater.dat"));
            theater = (Theater) input.readObject();
            
            System.out.println("Data has been restored!");
            input.close();
        
        } catch(FileNotFoundException e) {
            
            System.out.println("Stored data file not found.");
            
        } catch(IOException e) {
            
            System.out.println(e.getMessage());
            
        } catch (ClassNotFoundException e) {
            
            System.out.println(e.getMessage());
        }
        
        
    } // end retieveData
    
    
    /**
     * This method is used to sell a regular full priced ticket.
     */
    public void sellRegularTicket() {
        
        System.out.println("Please provide the following, ");
        int quantity = getNumber("Number of Tickets: ");
        int customerID = getNumber("customerID: ");
        String ccNumber = getToken("Credit Card Number: ");
        Calendar expDate = getDate("Date of show mm/dd/yy:");
        
        Customer result = theater.sellRegularTicket(quantity, customerID, 
                                                    ccNumber, expDate);
        
        
    } // end sellRegularTicket
    
    
    /**
     * This method is used to sell an advance discount priced ticket.
     */
    public void sellAdvanceTicket() {
        
        System.out.println("Not yet implemented.");
        
    } // end sellAdvanceTicket
    
    
    /**
     * This method is used to sell a discounted student priced ticket.
     */
    public void sellStudentTicket() {
        
        System.out.println("Not yet implemented.");
        
    } // end sellStudentTicket
    
    
    /**
     * This method is used to pay a client an amount up to the balance on 
     * their account.
     */
    public void payClient() {
        
        System.out.println("Not yet implemented.");
        
    } // end payClient
    
    
    /**
     * This method is used to output all tickets sold for a specific date.
     */
    public void printTickets() {
        
        System.out.println("Not yet implemented.");
        
    } // end sellRegularTicket
    
    
    /**
     * This method is used to process the action selected by the user.
     */
    public void processEntry() {

        int command;
        displayMenu();
        while ((command = getCommand()) != EXIT) {
            switch (command) {
    
            case EXIT:
                //theater.storeData();
                System.exit(0);
    
            case ADD_CLIENT:
                addClient();
                break;
    
            case REMOVE_CLIENT:
                removeClient();
                break;
    
            case LIST_CLIENTS:
                listClients();
                break;
    
            case ADD_CUSTOMER:
                addCustomer();
                break;
    
            case REMOVE_CUSTOMER:
                removeCustomer();
                break;
    
            case LIST_CUSTOMERS:
                listCustomers();
                break;
    
            case ADD_CC:
                addCreditCard();
                break;
    
            case REMOVE_CC:
                removeCreditCard();
                break;
    
            //case LIST_CC:             // case used to debug only
                //listCreditCards();
                //break;
            
            case ADD_SHOW:
                addShow();
                break;
    
            case LIST_SHOWS:
                listShows();
                break;
    
            case SAVE:
                storeData();
                break;
    
            case LOAD:
                retrieveData();
                break;
    
            case HELP:
                displayMenu();
                break;
                
            case SELL_REGULAR_TICKET:
                sellRegularTicket();
                break;
            
            case SELL_ADVANCE_TICKET:
                sellAdvanceTicket();
                break;
                
            case SELL_STUDENT_TICKET:
                sellStudentTicket();
                break;
                
            case PAY_CLIENT:
                payClient();
                break;
                
            case PRINT_TICKETS:
                printTickets();
                break;
                
            default:
                System.out.println("You chose poorly\n");

            } // end switch
            
        } // end while loop

    } // end processEntry
    
    public static void main(String[] args) {
        
        UserInterface userInterface = new UserInterface();
        userInterface.processEntry( ); // loops until exit is requested
        
        userInterface.storeData();
        
    }
    
    
}