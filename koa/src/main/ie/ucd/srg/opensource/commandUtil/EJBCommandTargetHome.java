/*
 * Created on 15-Nov-2004
 */

package ie.ucd.srg.opensource.commandUtil;

import java.rmi.RemoteException;
import javax.ejb.CreateException;

/**
 * @author Alan Morkan
 */
public class EJBCommandTargetHome {
	
	/**
	 * 
	 * @throws CreateException
	 * @throws RemoteException
	 */
	//@ ensures \result != null;
	public EJBCommandTarget create()throws CreateException, RemoteException{
		//@assert false;
		return null;
	}

}
