/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.ivj.ejb.associations.links;

import java.rmi.RemoteException;
import java.util.Enumeration;
import javax.ejb.*;
import ie.ucd.srg.ivj.ejb.associations.interfaces.*;

/**
 * @author Alan Morkan
 */
public class SingleToManyLink extends Link implements ManyLink{

	/**
	 * @param e
	 */
	public SingleToManyLink(EntityBean e){
		//@ assert false;
	}
	
	/**
	 * @param s
	 * @param c
	 */
	//@ ensures \result != null;
	public EJBObject lookupTargetHome(String s, Class c){	
		//@ assert false;
		return null;
	}
	
	/**
	 * @param e
	 * @throws RemoteException
	 */
	public void addElement(EJBObject e)throws RemoteException{
		//@ assert false;
	}
	
	/**
	 * 
	 * @param e
	 * @throws java.rmi.RemoteException
	 * @return
	 */
	protected boolean currentlyContains(EJBObject e)throws java.rmi.RemoteException{	
		//@ assert false;
		return false;
	}
	
	/**
	 * @param e
	 */
	public void secondaryAddElement(EJBObject e){
		//@ assert false;
	}
	
	/**
	 * @param e
	 */
	public void secondaryRemoveElement(EJBObject e){
		//@ assert false;
	}
	
	/**
	 * @return
	 */
	//@ ensures \result != null;
	public Enumeration enumerationValue(){	
		//@ assert false;
		return null;
	}
	
	/**
	 * @param e
	 */
	public void removeElement(EJBObject e){
		//@ assert false;
	}

}
