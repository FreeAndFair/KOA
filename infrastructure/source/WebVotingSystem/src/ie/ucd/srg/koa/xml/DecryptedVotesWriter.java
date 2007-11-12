/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.xml.DecryptedVotesWriter.java
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
  *  0.1.10		12-08-2003	MKu			First implementation for performance decrypting votes
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.xml;
import java.io.IOException;
import java.io.Writer;

import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.constants.FunctionalProps;
import ie.ucd.srg.koa.constants.TechnicalProps;
import ie.ucd.srg.koa.dataobjects.DecryptedStem;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.utils.KOALogHelper;
/**
 * Class for creating a decrypted votes export
 */
public class DecryptedVotesWriter{
	
	private /*@ non_null @*/ Writer xWriter;
	/**
	 * Constructor 
	 * writes an open tag to the writer
	 * 
	 * @param String	The current state of the system
	 * @param Writer	de xml data wil be writen to this writer
	 * 
	 * @throws KOAException	thrown when something goes wrong
	 */
	//@ requires sCurrentState != null;
	//@ requires writer != null;
	//@ signals (KOAException) false;
	public DecryptedVotesWriter(String sCurrentState, Writer writer)
		throws KOAException
	{
		this.xWriter = writer;
		/* init variables */
		String sStembureau =
			FunctionalProps.getProperty(FunctionalProps.VOTING_OFFICE);
		String sElectionDesc =
			FunctionalProps.getProperty(FunctionalProps.ELECTION_DESCRIPTION);
		String sCurrentTime =
			new java.sql.Timestamp(System.currentTimeMillis()).toString();
		/* write the start of the document */
		this.startDocument();
		this.writeHeader(
			sCurrentState,
			sCurrentTime,
			sStembureau,
			sElectionDesc);
		this.startStemmen();
	}
	/**
	 * writes the start of the document (xml header etc.)
	 * 
	 * @throws KOAException	thrown when something goes wrong
	 */
	//@ signals (KOAException) false;
	private void startDocument() throws KOAException
	{
		try
		{
			xWriter.write(
				"<?xml version=\"1.0\" encoding=\""
					+ TechnicalProps.getProperty(
						TechnicalProps.KL_EXPORT_XML_ENCODING)
					+ "\"?>\n");
			xWriter.write("<resultaat>\n");
		}
		catch (IOException ioe)
		{
			String[] params = { "writer" };
			KOALogHelper.logErrorCode(
				"DecryptedVotesWriter",
				ErrorConstants.ERR_IO,
				params,
				ioe);
			throw new KOAException(
				ErrorConstants.XML_DECRYPT_VOTES_WRITER_INIT,
				ioe);
		}
	}
	/**
	 * writes the header containing general information to the writer 
	 *
	 * @throws KOAException thrown when something goes wrong 
	 */
	//@ requires sCurrentState != null;
	//@ requires sCurTime != null;
	//@ requires sStembureau != null;
	//@ requires sVerkiezing != null;
	//@ signals (KOAException) false;
	private void writeHeader(
		String sCurrentState,
		String sCurTime,
		String sStembureau,
		String sVerkiezing)
		throws KOAException
	{
		try
		{
			xWriter.write(
				"\t<header starttijd=\""
					+ sCurTime
					+ "\" status=\""
					+ sCurrentState
					+ "\" stembureau=\""
					+ sStembureau
					+ "\" verkiezing=\""
					+ sVerkiezing
					+ "\" />\n");
		}
		catch (IOException ioe)
		{
			String[] params = { "writer" };
			KOALogHelper.logErrorCode(
				"DecryptedVotesWriter.writeHeader",
				ErrorConstants.ERR_IO,
				params,
				ioe);
			throw new KOAException(ErrorConstants.ERR_IO, ioe);
		}
	}
	
	/**
	 * writes the start tag of stemmen 
	 * 
	 * @throws KOAException thrown when something goes wrong
	 */
	//@ signals (KOAException) false;
	private void startStemmen() throws KOAException
	{
		try
		{
			xWriter.write("\t<stemmen>\n");
		}
		catch (IOException ioe)
		{
			String[] params = { "writer" };
			KOALogHelper.logErrorCode(
				"DecryptedVotesWriter.startStemmen",
				ErrorConstants.ERR_IO,
				params,
				ioe);
			throw new KOAException(ErrorConstants.ERR_IO, ioe);
		}
	}
	
	/**
	 * writes an close stemmen tag to the writer
	 * 
	 * @throws KOAException thrown when something goes wrong
	 */
	//@ signals (KOAException) false;
	private void endStemmen() throws KOAException
	{
		try
		{
			xWriter.write("\t</stemmen>\n");
		}
		catch (IOException ioe)
		{
			String[] params = { "writer" };
			KOALogHelper.logErrorCode(
				"DecryptedVotesWriter.endStemmen",
				ErrorConstants.ERR_IO,
				params,
				ioe);
			throw new KOAException(ErrorConstants.ERR_IO, ioe);
		}
	}
	
	/**
	 * writes a stem tag with attributes to the writer 
	 * 
	 * @param int				the vote number
	 * @param DecryptedStem		the actual decrypted vote
	 * 
	 * @throws KOAException 	thrown when something goes wrong
	 */
	//@ requires iStemnummer >= 0;
	//@ requires xStem != null;
	//@ signals (KOAException) false;
	public void writeStem(int iStemnummer, DecryptedStem xStem)
		throws KOAException
	{
		try
		{
			xWriter.write(
				"\t\t<stem stemnummer=\""
					+ Integer.toString(iStemnummer)
					+ "\" kandidaatcode=\""
					+ xStem.getKandidaatCode()
					+ "\" kieskringnummer=\""
					+ xStem.getKieskringnummer()
					+ "\" kieslijstnummer=\""
					+ xStem.getKieslijstnummer()
					+ "\" positienummer=\""
					+ xStem.getPositienummer()
					+ "\" lijstnaam=\""
					+ xStem.getLijstnaam()
					+ "\" achternaam=\""
					+ xStem.getAchternaam()
					+ "\" voorletters=\""
					+ xStem.getVoorletters()
					+ "\" />\n");
		}
		catch (IOException ioe)
		{
			String[] params = { "writer" };
			KOALogHelper.logErrorCode(
				"DecryptedVotesWriter.writeStem",
				ErrorConstants.ERR_IO,
				params,
				ioe);
			throw new KOAException(ErrorConstants.ERR_IO, ioe);
		}
	}
	
	//@ signals (KOAException) false;
	private void endDocument() throws KOAException
	{
		try
		{
			xWriter.write("</resultaat>");
		}
		catch (IOException ioe)
		{
			String[] params = { "writer" };
			KOALogHelper.logErrorCode(
				"DecryptedVotesWriter.endDocument",
				ErrorConstants.ERR_IO,
				params,
				ioe);
			throw new KOAException(ErrorConstants.ERR_IO, ioe);
		}
	}
	
	/**
	 * writes a close tag to the writer 
	 * and closes the writer.
	 */
	//@ signals (KOAException) false;
	public void close() throws KOAException
	{
		try
		{
			/* write the closing tags for the document */
			this.endStemmen();
			this.endDocument();
			/* close the writer */
			xWriter.flush();
			xWriter.close();
		}
		catch (IOException ioe)
		{
			String[] params = { "writer" };
			KOALogHelper.logErrorCode(
				"DecryptedVotesWriter.close",
				ErrorConstants.ERR_IO,
				params,
				ioe);
			throw new KOAException(ErrorConstants.ERR_IO, ioe);
		}
	}
}