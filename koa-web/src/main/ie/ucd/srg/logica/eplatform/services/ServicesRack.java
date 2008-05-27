/*
 * Created on 27-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.services;

import javax.naming.NamingException;

/**
 * @author Alan Morkan
 */
public class ServicesRack {

	/**
	 * 
	 */
	private boolean bool;
	
	/**
	 * @param b
	 */
	public ServicesRack(boolean b){
		//@ assert false;
	    bool = b;
	}
	
	/**
	 * @throws NamingException
	 */
	public void unbindServices()throws NamingException{
		//@ assert false;
	}

}
