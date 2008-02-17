/*
 * Created on 26-Oct-2004
 */
package ie.ucd.srg.logica.eplatform.util;

import ie.ucd.srg.logica.eplatform.eventhandling.*;

/**
 * @author Alan E. Morkan
 */

public class LogHelper implements java.io.Serializable {

	/**
	 * 
	 */
    public final static int FATAL = 1;
    
    /**
     * 
     */
    public final static int ERROR = 2;
    
    /**
     * 
     */
    public final static int WARNING = 3;
    
    /**
     * 
     */
    public final static int INFO = 4;
    
    /**
     * 
     */
    public final static int TRACE = 5;
	
    /**
     * 
     * @param iLevel
     * @param sMessage
     */
    //@ requires 0 <= iLevel && iLevel <=5;
    //@ requires sMessage != null;
    public static void log(int iLevel, String sMessage){
    	//@ assert false;
    	DefaultEvent event = new DefaultEvent(sMessage, iLevel);
    	EventHandler.handleEvent(event, "LOG");
    }
    
    /**
     *
     * @param iLevel
     * @param sMessage
     */
    //@ requires 0 <= iLevel && iLevel <=5;
    //@ requires sMessage != null;
    public static void trace(int iLevel, String sMessage){
    	//@ assert false;
    }
    
}
