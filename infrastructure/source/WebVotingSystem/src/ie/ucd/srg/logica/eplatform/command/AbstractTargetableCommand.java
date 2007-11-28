/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ie.ucd.srg.logica.eplatform.command.http.*;
import ie.ucd.srg.logica.eplatform.error.*;
import ie.ucd.srg.logica.eplatform.ticket.*;
import ie.ucd.srg.logica.eplatform.command.CommandException;

/**
 * @author Alan Morkan
 */
public class AbstractTargetableCommand implements HttpCommand{
	
	/**
	 * 
	 * @throws EPlatformException
	 */
	public void preExecution() throws EPlatformException{
		//@ assert false;
	}
	
	/**
	 * 
	 * @throws EPlatformException
	 */
	public void postExecution() throws EPlatformException{
		//@ assert false;
	}
	
	/**
	 * @param t
	 * @return
	 */
	/*@ requires t != null;
	  	ensures \result != null; @*/ 
	public Object performExecute(Ticket t){
		try{
			execute();
		}catch(EPlatformException e){
			//@ assert false;
		}catch(ie.ucd.srg.logica.eplatform.command.CommandException c){
			//@ assert false;
		}
		return this; //is this correct?
	}
	
	/**
	 * 
	 * @param h
	 * @throws EPlatformException
	 */
	//@ requires h != null;
	public void updateSession(HttpSession h)throws EPlatformException{
		//@ assert false;
	}
	
	/**
	 * 
	 * @return
	 */
	//@ ensures \result != null;
	public String getResultJSP(){
		//assert false;
		return null;
	}
	
	/**
	 * @return
	 */
	//@ ensures \result != null;
	public String getErrorJSP(){
		//assert false;
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @throws EPlatformException
	 */
	public void init(HttpServletRequest request)throws EPlatformException{
		//assert false;
	}
	
	/**
	 * 
	 * @param request
	 */
	public void setCommandTarget(HttpServletRequest request){
		//@ assert false;
	}
	
	/**
	 * @throws CommandException
	 */
	public void execute()throws CommandException, EPlatformException{
		//@ assert false;
	}
}
