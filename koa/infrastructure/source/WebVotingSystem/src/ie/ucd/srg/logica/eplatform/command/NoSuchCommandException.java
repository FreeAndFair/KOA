/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.command;

/**
 * @author Alan Morkan
 */
public class NoSuchCommandException extends Exception{
	
   private static final long serialVersionUID = 0L;

	public NoSuchCommandException(){
		super();
	}

	/**
	 * @param errorCode
	 */
	//@ requires errorCode != null;
	public NoSuchCommandException(String errorCode){
	    super(errorCode);
	    System.out.println(errorCode);
	}

}
