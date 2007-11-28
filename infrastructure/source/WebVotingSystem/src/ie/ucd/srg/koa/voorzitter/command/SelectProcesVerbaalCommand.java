/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.command.SelectProcesVerbaalCommand.java
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
  *  1.0		11-10-2003	PV			First implementation
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
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.utils.KOALogHelper;
import ie.ucd.srg.koa.utils.ObjectCache;
import ie.ucd.srg.koa.voorzitter.command.CommandConstants;
/**
 * SelectProcesVerbaalCommand.
 * Use this command to select the proces verbaal information.
 */
public class SelectProcesVerbaalCommand
	extends ie.ucd.srg.logica.eplatform.command.AbstractTargetableCommand
	implements ie.ucd.srg.logica.eplatform.command.http.HttpCommand
{
	private java.lang.String RESULT_JSP = "select_proces_verbaal.jsp";
	private java.lang.String NEXT_RESULT_JSP = "select_proces_verbaal_date.jsp";
	private java.lang.String g_sCurrentState = null;
	private java.lang.String g_Selection = null;
	private boolean g_FirstTime = true;
	/**
	 * The execute method on the SelectProcesVerbaal command.
	 * This method is executed in the ejb command target.
	 *
	 * @throws CommandException		necessary to fullfill abstract method signature
	 * @throws EPlatformException	thrown when the remote instance of the Controller can not be created.
	 */
	public void execute()
		throws ie.ucd.srg.logica.eplatform.command.CommandException, EPlatformException
	{
		LogHelper.log(
			LogHelper.INFO,
			"[SelectProcesVerbaalCommand.execute] start");
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
				"SelectProcesVerbaalCommand.execute",
				ErrorConstants.ERR_CREATE,
				params,
				ce);
			throw new KOAException(ErrorConstants.COMMAND_GETSTATE_EXEC, ce);
		}
		catch (RemoteException re)
		{
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"SelectProcesVerbaalCommand.execute",
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
		if (g_FirstTime)
		{
			return RESULT_JSP;
		}
		else
		{
			return NEXT_RESULT_JSP;
		}
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
		LogHelper.trace(LogHelper.TRACE, "[SelectProcesVerbaalCommand] init");
		g_Selection =
			request.getParameter(
				CommandConstants.SELECT_PROCES_VERBAAL_SELECTION);
		if (g_Selection != null)
		{
			g_FirstTime = false;
		}
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
	/**
	 * Return the selection which was retrieved in the init() method.
	 *
	 * @return String	The current state
	 */
	public String getSelection()
	{
		return g_Selection;
	}
}