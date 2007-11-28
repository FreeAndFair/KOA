/*
 * Created on 29-Oct-2004
 */

package ie.ucd.srg.ejs.container;


/**
 * @author Alan Morkan
 */

public class CreateFailureException extends RuntimeException{
	
	/**
	 * @param ex
	 */
	//@ requires ex != null;
	public CreateFailureException(Throwable ex){
		//@ assert false;
	}

}
