/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.ticket;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alan Morkan
 */
public class TicketRequest extends PrincipalTicketRequest {

	/**
	 * 
	 */
    public static final String KEY_USERNAME = "";
    
    /**
     * 
     */
    public static final String KEY_PASSWORD = "";
    
    /**
     * 
     */
    public TicketRequest(){
    	//@ assert false;
    }
    
    /**
     * 
     * @param request
     */
    //@ requires request != null;
    public TicketRequest(HttpServletRequest request){
    	//@ assert false;
    }
    
    /**
     * 
     * @param s
     * @return
     */
    //@ requires s != null;
    //@ ensures \result != null;
    public String getCredential(String s){
    	//@ assert false;
    	return null;
    }
    
    /**
     * @param s1
     * @param s2
     */
    //@ requires s1 != null && s2 != null;
    public void setCredential(String s1, String s2){
    	//@ assert false;
    }
}
