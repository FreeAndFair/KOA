/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.ejs.container;

import java.rmi.RemoteException;

import javax.ejb.*;

/**
 * @author Alan Morkan
 */
public class EJSWrapper {
	
	/**
	 * 
	 */
	public static EJSWrapper container;
	
	/**
	 * 
	 */
	public void remove(){
		//@ assert false;
	}
	
	/**
	 * @return
	 */
	//@ ensures \result != null;
	public EJBHome getEJBHome(){
		//@ assert false;
		return null;
	}
	
	/**
	 * @return
	 */
	//@ ensures \result != null;
	public Object getPrimaryKey(){
		//@ assert false;
		return null;
	}
	
	/**
	 * 
	 * @param arg
	 * @return
	 */
	//@ requires arg != null;
	public boolean isIdentical(EJBObject arg){
		//@ assert false;
		return false;
	}
	
	/**
	 * @return
	 */
	//@ ensures \result != null;
	public Handle getHandle(){
		//@ assert false;
		return null;
	}
	
	/**
	 * 
	 * @param f
	 * @param i
	 * @param e
	 * @throws java.rmi.RemoteException
	 * @return
	 */
	//@ requires f != null;
	//@ requires i >= 0;
	//@ requires e != null;
	//@ ensures \result != null;	
	//@ signals (RemoteException) true;
	//@ diverges false;
	public Object postInvoke(EJSWrapper f, int i, EJSDeployedSupport e) throws RemoteException{
		//@ assert false;
		return null;
	}
	
	/**
	 * @param f
	 * @param i
	 * @param e
	 * @throws java.rmi.RemoteException
	 * @throws RemoveException
	 * @return
	 */
	//@ requires f != null;
	//@ requires i >= 0;
	//@ requires e != null;
	//@ ensures \result != null;
	//@ signals (RemoteException) true;
	//@ signals (RemoveException) true;
	//@ diverges false;
	public Object preInvoke(EJSWrapper f, int i, EJSDeployedSupport e)throws RemoteException, RemoveException{	
		//@ assert false;
		return null;
	}
}
