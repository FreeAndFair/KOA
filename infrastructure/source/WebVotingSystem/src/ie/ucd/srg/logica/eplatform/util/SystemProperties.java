/*
 * Created on 27-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.util;

/**
 * @author Alan Morkan
 */
public class SystemProperties {

	/**
	 * looks like it might be a singleton!
	 * @return
	 */
	//@ ensures \result != null;
	public static SystemProperties getSystemProperties(){
		//@ assert false;
		return null;
	}
	
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
}
