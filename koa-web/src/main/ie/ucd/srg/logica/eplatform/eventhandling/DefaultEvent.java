/*
 * Created on 27-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.eventhandling;

/**
 * @author Alan Morkan
 */
public class DefaultEvent extends Event{
	
	/**
	 * 
	 * @param s
	 * @param i
	 */
	//@ requires s != null;
	//@ requires i > 0;
    public DefaultEvent(String s, int i){
    	//@ assert false;
    	super(s,i);
    }

}
