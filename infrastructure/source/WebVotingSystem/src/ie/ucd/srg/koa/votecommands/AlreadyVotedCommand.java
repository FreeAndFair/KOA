/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.command.AlreadyVotedCommand.java
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
  *  0.1		20-05-2003	AGr			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.votecommands;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import javax.servlet.http.*;
import ie.ucd.srg.koa.constants.ComponentType;
import ie.ucd.srg.koa.constants.FunctionalProps;
import ie.ucd.srg.koa.dataobjects.Kiezer;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.utils.KOALogHelper;
import ie.ucd.srg.koa.votecommands.CommandConstants;
/**
 * Command to execute when somebody already voted.
 * 
 * @author GroenevA
 */
public class AlreadyVotedCommand
	extends ie.ucd.srg.logica.eplatform.command.AbstractTargetableCommand
	implements ie.ucd.srg.logica.eplatform.command.http.HttpCommand
{
	private final static String NEXT_JSP = "/web-inf/jsp/alreadyvoted.jsp";
	private String sErrorMessage = null;
	private Kiezer g_xKiezer = null;
	/**
	 * The execute method on the AlreadyVotedCommand
	 * This method is executed in the ejb command target.
	 * 
	 * @throws CommandException		necessary to fullfill abstract method signature
	 * @throws EPlatformException	necessary to fullfill abstract method signature
	 */
	//@ signals (Exception) false;
	public void execute()
		throws ie.ucd.srg.logica.eplatform.command.CommandException, EPlatformException
	{
		KOALogHelper.log(KOALogHelper.INFO, "[AlreadyVotedCommand] execute");
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[AlreadyVotedCommand] leaving execute()");
	}
	/**
	 * Return the JSP to display errors.
	 * 
	 * @return String The error JSP
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ String getErrorJSP()
	{
		return CommandConstants.DEFAULT_ERROR_JSP;
	}
	/**
	 * Return the JSP page to display the result.
	 * 
	 * @return String The result JSP
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ String getResultJSP()
	{
		return NEXT_JSP;
	}
	/**
	 * Initialises the command. Here the parameters are
	 * extracted from the request.
	 *
	 * @param HttpServletRequest	Object that encapsulates the request to the servlet
	 * 
	 * @throws EPlatformException	necessary to fullfill abstract method signature
	 */
	//@ requires request != null;
	//@ signals (EPlatformException) false;
	public void init(HttpServletRequest request) throws EPlatformException
	{
		KOALogHelper.log(KOALogHelper.TRACE, "[AlreadyVotedCommand] init");
		if (request.getAttribute(CommandConstants.KIEZER_OBJECT) != null)
		{
			g_xKiezer =
				(Kiezer) request.getAttribute(CommandConstants.KIEZER_OBJECT);
		}
	}
	/**
	 * Update the current session.
	 * 
	 * @param HttpSession	session the session to update
	 */
	//@ requires session != null;
	//@ signals (Exception) false;
	public void updateSession(javax.servlet.http.HttpSession session)
	{
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[AlreadyVotedCommand] entering updateSession");
	}
	/**
	 * Returns a Kiezer dataobject
	 * 
	 * @return Kiezer	The kiezer which was read from the request in the init() method
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ Kiezer getKiezer()
	{
		return g_xKiezer;
	}
	/**
	 * Translates a technical key to a user friendly string
	 * 
	 * @param String	The key to be translated
	 * 
	 * @return String	The trasnlation when key is found else a string with size = 0;
	 */
	//@ requires sKey != null;
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public String translateTechnicalKey(String sKey)
	{
		if (sKey == null)
		{
			return "";
		}
		try
		{
			if (sKey.equals(ComponentType.WEB))
			{
				return FunctionalProps.getProperty(FunctionalProps.SOURCE_WEB);
			}
			if (sKey.equals(ComponentType.TEL))
			{
				return FunctionalProps.getProperty(FunctionalProps.SOURCE_TEL);
			}
		}
		catch (KOAException xKOAExc)
		{
			KOALogHelper.logError(
				"[AlreadyVotedCommand] ",
				xKOAExc.getMessage(),
				xKOAExc);
		}
		return "";
	}
}