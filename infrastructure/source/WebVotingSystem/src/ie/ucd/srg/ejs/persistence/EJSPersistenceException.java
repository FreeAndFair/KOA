/*
 * Created on 09-Nov-2004
 */

package ie.ucd.srg.ejs.persistence;

import javax.ejb.FinderException;

/**
 * @author Alan Morkan
 */
public class EJSPersistenceException extends FinderException{
	
	/**
	 * 
	 * @param s
	 * @param e
	 */
	//@ requires s != null;
	//@ requires e != null;
	public EJSPersistenceException(String s, Exception e){
		//@ assert false;
	}

}
