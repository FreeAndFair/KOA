/*
 * Created on 26-Oct-2004
 */
package ie.ucd.srg.logica.eplatform.ticket;

import java.util.Vector;
import java.util.Date;

/**
 * 
 * @author Alan Morkan
 *
 */
public class Ticket {
	
	/**
	 * 
	 */
	private /*@ non_null @*/ String userID;
    
	/**
	 * 
	 */
	private /*@ non_null @*/ Vector roles;
    
	/**
	 * 
	 */
	private /*@ non_null @*/ Date expiry;
    
	/**
	 * @param s
	 * @param v
	 * @param d
	 */
	//@ requires s != null;
	//@ requires v != null;
	//@ requires d != null;
    public Ticket(String s, Vector v, Date d){
    	//@ assert false;
    	userID = s;
    	roles = v;
    	expiry = d;
    }

    /**
     * 
     * @param s
     */
    //@ requires s != null;
    public boolean hasRole(String s){
    	//@ assert false;
    	int i = 0;
    	while(i<roles.size()){
    		if(s==roles.elementAt(i)){
    			return true;
    		}
    	}	
    	return false;
    }
    
    /**
     * @return
     */
    //@ ensures \result != null;
    public /*@ pure @*/ String getUserID(){
    	return userID;
    }
    
    /**
     * 
     * @return
     */
    public boolean isExpired(){
    	if(System.currentTimeMillis() > expiry.getTime()){
    		return true;
    	}else{
    		return false;
    	}
    }
}
