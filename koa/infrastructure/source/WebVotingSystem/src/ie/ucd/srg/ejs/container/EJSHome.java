/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.ejs.container;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.Enumeration;
import ie.ucd.srg.koa.koaschema.Districten;
import ie.ucd.srg.koa.controller.beans.*;
import ie.ucd.srg.ejs.persistence.*;

/**
 * @author Alan Morkan
 */

public class EJSHome {
	
	/** 
	 * 
	 */
	public static Object home;
	
	/**
	 * 
	 */
	public static Object persister;
	
	/**
	 * 
	 * @return
	 */
	//@ ensures \result != null;
	public EJBMetaData getEJBMetaData(){
		//@ assert false;
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	//@ ensures \result != null;
	public HomeHandle getHomeHandle(){
		//@ assert false;
		return null;
	}
	
	/**
	 * 
	 * @param o
	 */
	//@ requires o != null;
	public void remove(Object o){
		//@ assert false;
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	//@ requires e != null;
	//@ ensures \result != null;
	public Enumeration getEnumeration(EJSFinder e){
		//@ assert false;
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	//@ ensures \result != null;
	public BeanO createBeanO(){
		//@ assert false;
		return null;
	}
	
	/**
	 * 
	 * @param b
	 * @param o
	 * @return
	 */
	//@ requires b != null;
	//@ requires o != null;
	//@ ensures \result != null;
	//@ signals (RemoteException) true;
	//@ diverges false;
	public Districten postCreate(BeanO b, Object o)throws RemoteException{
		//@ assert false;
		return null;
	}
	
	/**
	 * 
	 * @param b
	 */
	//@ requires b != null;
	public void createFailure(BeanO b){
		//@ assert false;
	}
	
	/**
	 * 
	 * @param b
	 * @throws javax.ejb.CreateException
	 * @throws java.rmi.RemoteException
	 * @throws CreateFailureException
	 * @return
	 */
	//@ requires b != null;
	//@ ensures \result != null;
	//@ signals (CreateException) true;
	//@ signals (RemoteException) true;
	//@ diverges false;
	public Controller createWrapper(BeanId b)throws CreateException, RemoteException, CreateFailureException{
		//@ assert false;
		return null;
	}
}
