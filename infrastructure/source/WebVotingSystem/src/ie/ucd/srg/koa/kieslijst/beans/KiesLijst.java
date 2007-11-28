/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.kieslijst.beans.KiesLijst.java
  *
  * -----------------------------------------------------------------------
  * 
  *  (c) 2003  Ministerie van Binnenlandse Zaken en Koninkrijkrelaties
  *
  *  Project		: Kiezen Op Afstand (KOA)
  *  Project Number	: ECF-2651
  *  
  *  History:
  *  Version	Date		Name		Reason
  * ---------------------------------------------------------
  *  0.1		18-04-2003	AGr			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.kieslijst.beans;
import java.util.Vector;

import ie.ucd.srg.koa.dataobjects.Kandidaat;
import ie.ucd.srg.koa.dataobjects.KandidaatResponse;
import ie.ucd.srg.koa.dataobjects.KiesLijstFingerprintCompareResult;
import ie.ucd.srg.koa.exception.KOAException;
/**
 * Remote interface for Enterprise Bean: KiesLijst
 */
public interface KiesLijst extends javax.ejb.EJBObject
{
	/**
	 * This method retrieves an Kandiddaatcode object by it's primmary key.
	 * 
	 * @param sKandidaatcode - The priimary key of the kandidaat to search for.
	 * @return Kandidaatcodes object
	 */
	public Kandidaat getKandidaat(String sKandidaatcode)
		throws ie.ucd.srg.koa.exception.KOAException, java.rmi.RemoteException;
	/**
	 * This method verifys of a given candidate exists
	 * 
	 * @param sKandidaatnummer - The id of the candidate to search for.
	 * @return KandidaatResponse object that indicates that the given sKandidaatnummer is valid or not.
	 * @exception ie.ucd.srg.exception.KOAException
	 */
	public KandidaatResponse verifyKandidaat(
		String sKandidaatcode,
		String sStemcode)
		throws ie.ucd.srg.koa.exception.KOAException, java.rmi.RemoteException;
	/**
	 * This methods retrieves all the kieslijsten(partijen) foe a given kieskring
	 * 
	 * @param String sKieskringnummer The id of the kieskring to retrieve the kieslijsten for.
	 * @return Vector containing Partij objects
	 * @exception 
	 */
	public Vector getPartijen(String sKieskringnummer)
		throws ie.ucd.srg.koa.exception.KOAException, java.rmi.RemoteException;
	/**
	 * This methods retrieves all the candidates for a given kieslijst
	 * 
	 * @param String sKieslijstnummer The id of the kieslijst to retrieve the candidates for.
	 * @param String sKieskringnummer The id of the  kieskring to which the kieslijst belongs.
	 * @return Vector containing Kandidaat objects
	 * @exception ie.ucd.srg.exception.KOAException
	 */
	public Vector getKandidaten(
		String sKieslijstnummer,
		String sKieskringnummer)
		throws ie.ucd.srg.koa.exception.KOAException, java.rmi.RemoteException;
	/**
	 * Save the fingerprint of the current kieslijst database tables 
	 * in the database
	 * 
	 * @throws KOAException Exception when something goes wrong during save of the fingerprint.
	 * 
	 */
	public void saveCurrentKieslijstFingerprints()
		throws KOAException, java.rmi.RemoteException;
	/**
	 * Compare the current fingerprint of the kieslijst tables
	 * with the last saved fingeprint.
	 * 
	 * @return CallResult the result of the compare action
	 * 
	 * @throws KOAException when something goes wrong during the compare
	 * 
	 */
	public KiesLijstFingerprintCompareResult compareKieslijstFingerprint()
		throws KOAException, java.rmi.RemoteException;
}
