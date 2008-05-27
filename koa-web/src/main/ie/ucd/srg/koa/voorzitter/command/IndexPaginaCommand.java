/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.command.IndexPaginaCommand.java
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
  *  0.1		15-05-2003	AGr			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.voorzitter.command;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.servlet.http.*;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.logica.eplatform.util.LogHelper;
import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.controller.beans.Controller;
import ie.ucd.srg.koa.controller.beans.ControllerHome;
import ie.ucd.srg.koa.databeheer.command.CommandConstants;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.utils.KOALogHelper;
import ie.ucd.srg.koa.utils.ObjectCache;
/**
 * IndexPaginaCommand.
 * Use this command to get the current systemstate, special for the index page.
 * Also can be used to return all the possible state changes 
 * that are valid from this state.
 */
public class IndexPaginaCommand
	extends ie.ucd.srg.logica.eplatform.command.AbstractTargetableCommand
	implements ie.ucd.srg.logica.eplatform.command.http.HttpCommand
{
	private java.lang.String RESULT_JSP = "mainpage.jsp";
	private java.lang.String g_sCurrentState = null;
	/**
	 * The execute method on the IndexPaginaCommand command.
	 * This method is executed in the ejb command target.
	 *
	 * @throws CommandException		necessary to fullfill abstract method signature
	 * @throws EPlatformException	thrown when the remote instance of the Controller can not be created.
	 */
	public void execute()
		throws ie.ucd.srg.logica.eplatform.command.CommandException, EPlatformException
	{
		LogHelper.log(LogHelper.INFO, "[IndexPaginaCommand.execute] start");
		try
		{
			ControllerHome xControllerHome =
				ObjectCache.getInstance().getControllerHome();
			/* create remote instance from the home interface */
			Controller xController = xControllerHome.create();
			/* get the current state */
			g_sCurrentState = xController.getCurrentState();
		}
		catch (CreateException ce)
		{
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"IndexPaginaCommand.execute",
				ErrorConstants.ERR_CREATE,
				params,
				ce);
			throw new KOAException(ErrorConstants.COMMAND_GETSTATE_EXEC, ce);
		}
		catch (RemoteException re)
		{
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"IndexPaginaCommand.execute",
				ErrorConstants.ERR_REMOTE,
				params,
				re);
			throw new KOAException(ErrorConstants.COMMAND_GETSTATE_EXEC, re);
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
		LogHelper.trace(LogHelper.TRACE, "[IndexPaginaCommand] init");
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
	 * Return the state which was retrieved in the execute() method.
	 *
	 * @return String	The current state
	 */
	public String getCurrentState()
	{
		return g_sCurrentState;
	}
}