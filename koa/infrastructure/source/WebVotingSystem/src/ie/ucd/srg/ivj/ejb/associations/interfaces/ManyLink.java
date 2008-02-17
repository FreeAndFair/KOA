/*
 * Created on 27-Oct-2004
 */
package ie.ucd.srg.ivj.ejb.associations.interfaces;

import javax.ejb.*;

import java.rmi.RemoteException;
import java.util.Enumeration;

/**
 * @author Alan Morkan
 */
public interface ManyLink {
	
	/**
	 * 
	 * @param e
	 */
	//@ requires e != null;
	public void secondaryAddElement(EJBObject e);

	/**
	 * 
	 * @param e
	 */
	public void secondaryRemoveElement(EJBObject e);
	
	/**
	 * @return 
	 */
	//@ ensures \result != null;
	public Enumeration enumerationValue();
	
	/**
	 * @param e
	 * @throws RemoteException
	 */
	//@ requires e != null;
	//@ signals (RemoteException) true;
	public void addElement(EJBObject e)throws RemoteException;
	
	/**
	 * @param e
	 */
	//@ requires e!= null;
	public void removeElement(EJBObject e);
}
