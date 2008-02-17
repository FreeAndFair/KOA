/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.ivj.ejb.associations.links;

import javax.ejb.*;
import javax.naming.NamingException;
import ie.ucd.srg.ivj.ejb.associations.interfaces.*;

import ie.ucd.srg.koa.koaschema.KieskringenHome;

/**
 * @author Alan Morkan
 */
public class ManyToSingleLink extends Link implements SingleLink {
	
	/**
	 * 
	 * @param anEntityBean
	 */
	public ManyToSingleLink(EntityBean anEntityBean){
		//@ assert false;
	}
	
	/**
	 * 
	 * @param e
	 * @throws java.rmi.RemoteException 
	 */
	public void set(EJBObject e) throws java.rmi.RemoteException{
		//@ assert false;
	}
	
	/**
	 * 
	 * @param s
	 * @param c
	 * @return
	 */
	//@ ensures \result != null;
	public KieskringenHome lookupTargetHome(String s, Class c){	
		//@ assert false;
		return null;
	}
	
	/**
	 * 
	 * @param l
	 * @throws NamingException
	 * @return
	 */
	//@ ensures \result != null;
	public EJBObject getTargetHome(ManyLink l)throws NamingException{	
		//@ assert false;
		return null;
	}
	
	/**
	 * 
	 * @param k
	 */
	public void secondarySet(EJBObject k){
		//@ assert false;
	}
	
	/**
	 * 
	 * @return
	 */
	//@ ensures \result != null;
	public EJBObject value(){	
		//@ assert false;
		return null;
	}
}
