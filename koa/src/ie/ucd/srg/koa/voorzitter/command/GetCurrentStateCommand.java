/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.command.GetCurrentStateCommand.java
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
  *  1.0		15-04-2003	MKu			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.voorzitter.command;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.servlet.http.*;
import java.util.Hashtable;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.logica.eplatform.util.LogHelper;
import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.constants.JNDIProperties;
import ie.ucd.srg.koa.controller.beans.Controller;
import ie.ucd.srg.koa.controller.beans.ControllerHome;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.utils.KOALogHelper;
import ie.ucd.srg.koa.voorzitter.command.CommandConstants;
/**
 * GetCurrentStateCommand.
 * Use this command to get the current state of the KOA system.
 * Also can be used to return all the possible state changes 
 * that are valid from this state.
 * 
 * @author: KuijerM
 */
public class GetCurrentStateCommand
	extends ie.ucd.srg.logica.eplatform.command.AbstractTargetableCommand
	implements ie.ucd.srg.logica.eplatform.command.http.HttpCommand
{
	private java.lang.String RESULT_JSP = "state_information.jsp";
	private java.lang.String g_sCurrentState = null;
	private Vector g_vValidStateChanges = null;
	/**
	 * The execute method on the GetCurrentState command.
	 * This method is executed in the ejb command target.
	 * 
	 * @throws CommandException		necessary to fullfill abstract method signature
	 * @throws EPlatformException	thrown when the remote instance of the Controller can not be created.
	 */
	public void execute()
		throws ie.ucd.srg.logica.eplatform.command.CommandException, EPlatformException
	{
		LogHelper.log(LogHelper.INFO, "[GetCurrentStateCommand.execute] start");
		/* set the propeties for the initial context */
		Hashtable htProps = new Hashtable();
		htProps.put(
			Context.INITIAL_CONTEXT_FACTORY,
			JNDIProperties.getProperty(
				JNDIProperties.CONTROLLER_CONTEXT_FACTORY));
		htProps.put(
			Context.PROVIDER_URL,
			JNDIProperties.getProperty(JNDIProperties.CONTROLLER_PROVIDER));
		try
		{
			/* create the initial context */
			InitialContext icContext = new InitialContext(htProps);
			/* look up the home interface */
			Object obj =
				icContext.lookup(
					JNDIProperties.getProperty(JNDIProperties.CONTROLLER_NAME));
			ControllerHome xControllerHome =
				(ControllerHome) PortableRemoteObject.narrow(
					obj,
					ControllerHome.class);
			/* create remote instance from the home interface */
			Controller xController = xControllerHome.create();
			/* get the current state */
			g_sCurrentState = xController.getCurrentState();
			/* get available state changes, based on the current state */
			g_vValidStateChanges =
				xController.getAvailableStates(g_sCurrentState);
		}
		catch (CreateException ce)
		{
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"GetCurrentStateCommand.execute",
				ErrorConstants.ERR_CREATE,
				params,
				ce);
			throw new KOAException(ErrorConstants.COMMAND_GETSTATE_EXEC, ce);
		}
		catch (NamingException ne)
		{
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"GetCurrentStateCommand.execute",
				ErrorConstants.ERR_NAMING,
				params,
				ne);
			throw new KOAException(ErrorConstants.COMMAND_GETSTATE_EXEC, ne);
		}
		catch (RemoteException re)
		{
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"GetCurrentStateCommand.execute",
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
		LogHelper.trace(LogHelper.TRACE, "[GetCurrentStateCommand] init");
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
	 * Return the valid state changes,
	 * which were retrieved from the controller in the execute() method
	 *
	 * @return Vector	The valid state changes
	 */
	public Vector getValidStateChanges()
	{
		return g_vValidStateChanges;
	}
}