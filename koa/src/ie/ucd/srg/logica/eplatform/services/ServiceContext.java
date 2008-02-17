/*
 * Created on 27-Oct-2004
 */
package ie.ucd.srg.logica.eplatform.services;

/**
 * @author Alan Morkan
 */
public class ServiceContext {
	
	/**
	 * @param s
	 * @return
	 */
	//@ requires s != null;
	//@ ensures \result != null;
	public String getProperty(String s){
		//@ assert false;
		return null;
	}
	
	/**
	 * @return
	 */
	//@ ensures \result != false;
	public String getServiceName(){
		//@ assert false;
		return null;
	}
}
