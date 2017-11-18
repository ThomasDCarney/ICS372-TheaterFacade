import java.io.Serializable;
import java.util.Calendar;

public class Ticket implements Serializable, Matchable {

    private int serialNumber;
    private Calendar date;
    private float price;
    private String type;
    
    @Override
    public boolean matches(Object identifier) {
        // TODO Auto-generated method stub
        return false;
    }
    
    
}