/*
 * Created on 27-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.eventhandling;

/**
 * @author Alan E. Morkan
 */
public class Event {

	/**
	 * 
	 */
    private int severity;
    
    /**
     * 
     */
    private String message;

    /**
     * 
     * @param m
     * @param s
     */
    //@ requires m != null;
    //@ requires s >= 0;
    public Event(String m, int s){
    	severity = s;
    	message = m;    	
    }
    
    /**
     * @return
     */
    public /*@ pure @*/ int getSeverity(){
    	return severity;
    }
    
    /**
     * @return
     */
    public /*@ pure @*/ String getMessage(){
    	return message;
    }

}
