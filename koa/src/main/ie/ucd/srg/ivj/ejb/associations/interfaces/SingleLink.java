/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.ivj.ejb.associations.interfaces;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 * @author Alan Morkan
 */
public interface SingleLink {
	
	/**
	 * 
	 * @param k
	 * @throws java.rmi.RemoteException
	 */
	//@ requires k != null;
	//@ signals (RemoteException) true;
	public void set(EJBObject k)throws RemoteException;

	/**
	 * @param k
	 */
	//@ requires k != null;
	public void secondarySet(EJBObject k);
	
	/**
	 * @return
	 */
	//@ ensures \result != null;
	public EJBObject value();

}
