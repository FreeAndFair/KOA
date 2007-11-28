/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.eventhandling;

import ie.ucd.srg.logica.eplatform.eventhandling.DefaultEvent;
/**
 * @author Alan Morkan
 */
public class EventHandler {
	
	/**
	 * 
	 */
	public EventHandler(){
		//@ assert false;
	}
	
	/**
	 * @param e
	 * @param s
	 */
	//@ requires e != null && s != null;
	public static void handleEvent(DefaultEvent e, String s){
		//@ assert false;
	}
}
