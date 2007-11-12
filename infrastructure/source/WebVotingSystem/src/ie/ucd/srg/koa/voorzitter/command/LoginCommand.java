/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.command.LoginCommand.java
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
  *  1.0		07-04-2003	MKu			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.voorzitter.command;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import javax.servlet.http.*;
import ie.ucd.srg.logica.eplatform.util.LogHelper;
import ie.ucd.srg.koa.voorzitter.command.CommandConstants;
/**
 * LoginCommand. Only function of this command is to logon somebody.
 * Only use this command if you want to explicitly log somebody on to the 
 * system without performing a specific task.
 * 
 * @author: KuijerM
 */
public class LoginCommand
	extends ie.ucd.srg.logica.eplatform.command.AbstractTargetableCommand
	implements ie.ucd.srg.logica.eplatform.command.http.HttpCommand
{
	private java.lang.String RESULT_JSP = null;
	private boolean logout = false;
	/**
	 * The execute method on the Login command.
	 * This method is executed in the ejb command target.
	 * For the LoginCommand the execute method doesn't do anything.
	 * 
	 * @throws CommandException		necessary to fullfill abstract method signature
	 * @throws EPlatformException	necessary to fullfill abstract method signature
	 */
	public void execute()
		throws ie.ucd.srg.logica.eplatform.command.CommandException, EPlatformException
	{
		LogHelper.log(LogHelper.INFO, "[LoginCommand] execute");
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
		LogHelper.trace(LogHelper.TRACE, "[LoginCommand] init");
		RESULT_JSP = request.getParameter("page");
		if (RESULT_JSP == null)
			RESULT_JSP = "index.jsp";
	}
	/**
	 * Update the current session.
	 * 
	 * @param HttpSession	The session to be updated
	 */
	public void updateSession(javax.servlet.http.HttpSession session)
	{
	}
}