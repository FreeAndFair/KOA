package ie.ucd.srg.koa.esb.beans;
import java.rmi.RemoteException;
import java.util.Vector;

import ie.ucd.srg.koa.exception.KOAException;
/**
 * Remote interface for Enterprise Bean: ESBDecryptHelper
 */
public interface ESBDecryptHelper extends javax.ejb.EJBObject
{
	public int saveDecryptedVotes(Vector decryptedVotes, int stemnummerCount)
		throws KOAException, RemoteException;
}