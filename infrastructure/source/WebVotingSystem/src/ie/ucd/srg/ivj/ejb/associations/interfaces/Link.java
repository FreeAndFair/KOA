/*
 * Created on 26-Oct-2004
 */
package ie.ucd.srg.ivj.ejb.associations.interfaces;

import javax.ejb.FinderException;

/**
 * @author Alan Morkan
 */
public interface Link {
	
	/**
	 * @throws FinderException
	 */
	//@ signals (FinderException) true;
	public void remove() throws FinderException;

}
