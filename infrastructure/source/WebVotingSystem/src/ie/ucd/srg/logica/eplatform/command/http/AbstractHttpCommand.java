/*
 * Created on 27-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.command.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.logica.eplatform.ticket.Ticket;
import ie.ucd.srg.logica.eplatform.command.*;

/**
 * @author Alan Morkan
 */
public class AbstractHttpCommand implements HttpCommand {
	
	/**
	 * @throws EPlatformException
	 */
	public void preExecution() throws EPlatformException{
		//@assert false;
	}
	
	/**
	 * @throws EPlatformException
	 */
	public void postExecution() throws EPlatformException{
		//@ assert false;
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
	//@ requires request != null;
	public void init(HttpServletRequest request)throws EPlatformException{
		//@ assert false;
	}
	
	/**
	 * @param request
	 */
	//@ requires request != null;
	public void setCommandTarget(HttpServletRequest request){
		//@ assert false;
	}
	
	/**
	 * 
	 * @throws CommandException
	 * @throws EPlatformException
	 */
	public void execute()throws CommandException, EPlatformException{
		//@ assert false;
	}
	
	/**
	 * @param t
	 * @return 
	 */
	//@ requires t != null;
	//@ requires \result != null;
	public Object performExecute(Ticket t){
		//@ assert false;
		try{
			execute();
		}catch(EPlatformException e){
			System.out.println(e);
		}catch(CommandException c){
			System.out.println(c);
		}
		return this;
	}
}