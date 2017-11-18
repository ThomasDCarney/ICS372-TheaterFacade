import java.io.Serializable;
import java.util.Calendar;

public class Sales implements Serializable, Matchable<Calendar> {

    private int headCount;
    
    public Sales() {
        
        headCount = 0;
        
    } // end Sales constructor
    
    
    
    public int getHeadCount() {
        
        return headCount;
        
    } // end getHeadCount
    
    @Override
    public boolean matches(Calendar identifier) {
        // TODO Auto-generated method stub
        return false;
    }
    
    
    
    
}