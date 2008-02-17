/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.command.CompareKieslijstFingerprints.java
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
  *  0.1.8		15-05-2003	Mku			Compare of the fingerprints...
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.voorzitter.command;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.servlet.http.*;
import ie.ucd.srg.logica.eplatform.command.AbstractTargetableCommand;
import ie.ucd.srg.logica.eplatform.command.CommandException;
import ie.ucd.srg.logica.eplatform.command.http.HttpCommand;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.logica.eplatform.util.LogHelper;
import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.dataobjects.KiesLijstFingerprintCompareResult;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.kieslijst.beans.KiesLijst;
import ie.ucd.srg.koa.kieslijst.beans.KiesLijstHome;
import ie.ucd.srg.koa.utils.KOALogHelper;
import ie.ucd.srg.koa.utils.ObjectCache;
import ie.ucd.srg.koa.voorzitter.command.CommandConstants;
/**
 * CompareKieslijstFingerprints.
 * Use this command to get the compare the fingerprints
 * of the Kieslijst.
 * 
 * @author KuijerM
 * 
 */
public class CompareKieslijstFingerprints
	extends AbstractTargetableCommand
	implements HttpCommand
{
	private java.lang.String RESULT_JSP = "comparefingerprints_result.jsp";
	private java.lang.String g_sCurrentState;
	private KiesLijstFingerprintCompareResult g_xCompareResult = null;
	/**
	 * The execute method on the CompareKieslijstFingerprints command.
	 * This method is executed in the ejb command target.
	 * 
	 * @throws CommandException		necessary to fullfill abstract method signature
	 * @throws EPlatformException	thrown when the remote instance of the Kieslijst can not be created.
	 */
	public void execute() throws CommandException, EPlatformException
	{
		LogHelper.log(
			LogHelper.INFO,
			"[CompareKieslijstFingerprints.execute] start");
		try
		{
			KiesLijstHome xHome = ObjectCache.getInstance().getKiesLijstHome();
			/* create remote instance from the home interface */
			KiesLijst xKiesLijst = xHome.create();
			/* get the current state */
			g_xCompareResult = xKiesLijst.compareKieslijstFingerprint();
		}
		catch (CreateException ce)
		{
			String[] params = { "KiesLijst" };
			KOALogHelper.logErrorCode(
				"CompareKieslijstFingerprints.execute",
				ErrorConstants.ERR_CREATE,
				params,
				ce);
			throw new KOAException(
				ErrorConstants.CMD_COMPARE_FINGERPRINTS_ERR,
				ce);
		}
		catch (RemoteException re)
		{
			String[] params = { "KiesLijst" };
			KOALogHelper.logErrorCode(
				"CompareKieslijstFingerprints.execute",
				ErrorConstants.ERR_REMOTE,
				params,
				re);
			throw new KOAException(
				ErrorConstants.CMD_COMPARE_FINGERPRINTS_ERR,
				re);
		}
	}
	/**
	 * Return the JSP to display errors.
	 * 
	 * @return String The error JSP
	 */
	public String getErrorJSP()
	{
		return CommandConstants.DEFAULT_ERROR_JSP;
	}
	/**
	 * Return the JSP page to display the result.
	 *
	 * @return String The result JSP
	 */
	public String getResultJSP()
	{
		return RESULT_JSP;
	}
	/**
	 * Initialises the command. Here the parameters are
	 * extracted from the request.
	 *
	 * @param HttpServletRequest	Object that encapsulates the request to the servlet
	 * 
	 * @throws EPlatformException	necessary to fullfill abstract method signature
	 */
	public void init(HttpServletRequest request) throws EPlatformException
	{
		LogHelper.trace(LogHelper.TRACE, "[CompareKieslijstFingerprints] init");
	}
	/**
	 * Update the current session.
	 * 
	 * @param HttpSession	The session to be updated
	 */
	public void updateSession(javax.servlet.http.HttpSession session)
	{
	}
	/**
	 * Return the compare result which was retrieved in the execute() method.
	 *
	 * @return KiesLIjstFingerprintCompareResult	The result of comparing the kieslijst fingerprint
	 */
	public KiesLijstFingerprintCompareResult getCompareResult()
	{
		return g_xCompareResult;
	}
}
