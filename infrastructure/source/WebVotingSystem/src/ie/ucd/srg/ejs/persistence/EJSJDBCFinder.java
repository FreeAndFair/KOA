/*
 * Created on 27-Oct-2004
 */
package ie.ucd.srg.ejs.persistence;

import java.sql.*;


/**
 * @author Alan Morkan
 */
public class EJSJDBCFinder extends EJSFinder{
	
	/**
	 * @param rs
	 * @param e
	 * @param p
	 */
	//@ requires rs != null;
	//@ requires e != null;
	//@ requires p != null;
	public EJSJDBCFinder(ResultSet rs, EJSJDBCPersister e, PreparedStatement p){
		//@ assert false;
	}
	
	/**
	 * @return
	 */
	public boolean hasMoreElements(){
		//@ assert false;
		return false;
	}
	
	/**
	 * 
	 */
	public void close(){
		//@ assert false;
	}
	
	/**
	 * @return
	 */
	//@ ensures \result != null;
	public Object nextElement(){
		//@ assert false;
		return null;
	}
}
