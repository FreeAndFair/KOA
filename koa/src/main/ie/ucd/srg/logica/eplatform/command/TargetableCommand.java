/*
 * Created on 27-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.command;

import javax.servlet.http.*;
import ie.ucd.srg.logica.eplatform.ticket.*;
import ie.ucd.srg.logica.eplatform.error.*;
import ie.ucd.srg.opensource.commandUtil.EJBCommandTarget;

/**
 * @author Alan Morkan
 */
public interface TargetableCommand {
	
	/**
	 * @param request
	 */
	//@ requires request != null;
	public void setCommandTarget(HttpServletRequest request);
	
	/**
	 * @param target
	 */
	//@ requires target != null;
	public void setCommandTarget(EJBCommandTarget target);
	
	/**
	 * @throws EPlatformException
	 */
	public void preExecution() throws EPlatformException;
	
	/**
	 * @throws EPlatformException
	 */
	public void postExecution() throws EPlatformException;
	
	/**
	 * @param t
	 * @return
	 */
	/*@ requires t != null;
	 	ensures \result != null; @*/
	public Object performExecute(Ticket t);
}
