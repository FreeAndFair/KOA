/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.util;

import java.util.Properties;

/**
 * @author Alan Morkan
 */
public class XMLProperties extends ie.ucd.srg.koa.scheduler.XMLProperties{
	
	/**
	 * 
	 * @param props
	 */
	//@ requires props != null;
	public XMLProperties(Properties props){
		//@ assert false;
		super(props);
	}
	
	/**
	 * 
	 * @param s1
	 * @param s2
	 */
	//@ requires s1 != null && s2 != null;
	public void put(String s1, String s2){
		//@ assert false;
	}
}
