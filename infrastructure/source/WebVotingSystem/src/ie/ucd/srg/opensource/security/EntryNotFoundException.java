/*
 * Created on 27-Oct-2004
 */

package ie.ucd.srg.opensource.security;

/**
 * @author Alan Morkan
 */
public class EntryNotFoundException extends Exception{

	/**
	 * @param s
	 */
	//@ requires s!= null;
	public EntryNotFoundException(String s){
		//@ assert false;
	}

}
