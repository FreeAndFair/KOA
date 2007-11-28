/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.error;

import java.io.IOException;
import ie.ucd.srg.koa.exception.*;

/**
 * @author Alan E. Morkan
 */
public class ErrorMessageFactory {
	
	/**
	 * 
	 */
	private static ErrorMessageFactory factory;
	
	/**
	 * 
	 * @throws IOException
	 */
	private ErrorMessageFactory()throws IOException{
		//@ assert false;
	}
	
	/**
	 * 
	 * @throws IOException
	 * @return
	 */
	//@ ensures \result = factory;
	public static ErrorMessageFactory getErrorMessageFactory()throws IOException{
		//@ assert false;
		if (factory == null){
			try{
				/* if we dont have a command factory yet, create one */
				factory = new ErrorMessageFactory();
			}catch (IOException ioe){
				/* if we cant find the command factory class, throw a runtime exception */
				throw new RuntimeException(
					"Error creating CommandFactory: " + ioe.getMessage());
			}
		}
		/* return the singleton instance */
		return factory;	
	}
	
	/**
	 * 
	 * @param errorcode
	 * @param params
	 * @throws KOAException
	 * @return
	 */
	/*@ requires errorcode != null && params != null;
	  	ensures \result != null; @*/
	public String getErrorMessage(String errorcode, String[] params)throws KOAException{
		//@ assert false;
		String errorMessage = "Error code: " + errorcode + "Parameters:";
		for(int i=0; i<params.length; i++){
			errorMessage = errorMessage + params[i] + "\n";
		}
		return errorMessage;
	}
	
	/**
	 * 
	 * @param e
	 * @throws IOException
	 * @return
	 */
	/*@ requires e != null;
	   	ensures \result != null; @*/
	public String getErrorMessage(Exception e)throws IOException{
		//@ assert false;
		return e.toString();
	}
}
