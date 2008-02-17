/*
 * Created on 26-Oct-2004
 */

package ie.ucd.srg.ejs.persistence;

import java.sql.*;

/**
 * @author Alan Morkan
 */
public class EJSJDBCPersister {
	
	/**
	 * 
	 */
	public static EJSJDBCPersister home;
	
	/**
	 * @param s
	 * @return
	 */
	//@ requires s != null;
	//@ ensures \result != null;
	public PreparedStatement getPreparedStatement(String s){
		//@ assert false;
		return null;
	}
	
	/**
	 * @param p
	 */
	//@ requires p != null;
	public void returnPreparedStatement(PreparedStatement p){
		//@ assert false;
	}
	
	/**
	 * 
	 */
	public void preFind(){
		//@ assert false;
	}
	
	/**
	 * 
	 * @param
	 * @return
	 */
	//@ requires o != null;
	//@ ensures \result != null;
	public Object activateBean(Object o){
		//@ assert false;
		return null;
	}
}
