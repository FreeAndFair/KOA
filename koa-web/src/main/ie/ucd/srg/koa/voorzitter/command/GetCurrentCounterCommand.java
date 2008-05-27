/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.command.GetCurrentCounterCommand.java
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
import java.util.Hashtable;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.logica.eplatform.util.LogHelper;
import ie.ucd.srg.koa.constants.ComponentType;
import ie.ucd.srg.koa.constants.CounterKeys;
import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.constants.JNDIProperties;
import ie.ucd.srg.koa.controller.beans.Controller;
import ie.ucd.srg.koa.controller.beans.ControllerHome;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.utils.KOALogHelper;
import ie.ucd.srg.koa.voorzitter.command.CommandConstants;
import javax.ejb.*;
import javax.servlet.http.*;
/**
 * GetCurrentCounterCommand.
 * Use this command to get the current counters of the KOA system.
 * 
 * @author: KuijerM
 * 
 */
public class GetCurrentCounterCommand
	extends ie.ucd.srg.logica.eplatform.command.AbstractTargetableCommand
	implements ie.ucd.srg.logica.eplatform.command.http.HttpCommand
{
	private java.lang.String RESULT_JSP = "counter_information.jsp";
	private Vector g_vCounters;
	/**
	 * The execute method on the GetCurrentCounter command.
	 * This method is executed in the ejb command target.
	 * 
	 * @throws CommandException		necessary to fullfill abstract method signature
	 * @throws EPlatformException	thrown when the remote instance of the Controller can not be created.
	 *
	 */
	public void execute()
		throws ie.ucd.srg.logica.eplatform.command.CommandException, EPlatformException
	{
		LogHelper.log(
			LogHelper.INFO,
			"[GetCurrentCounterCommand.execute] start");
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
			/* get the current counters */
			g_vCounters = xController.getCurrentCounters();
		}
		catch (CreateException ce)
		{
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"GetCurrentCounterCommand.execute",
				ErrorConstants.ERR_CREATE,
				params,
				ce);
			throw new KOAException(ErrorConstants.COMMAND_GETCOUNTERS_EXEC, ce);
		}
		catch (NamingException ne)
		{
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"GetCurrentCounterCommand.execute",
				ErrorConstants.ERR_NAMING,
				params,
				ne);
			throw new KOAException(ErrorConstants.COMMAND_GETCOUNTERS_EXEC, ne);
		}
		catch (RemoteException re)
		{
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"GetCurrentCounterCommand.execute",
				ErrorConstants.ERR_REMOTE,
				params,
				re);
			throw new KOAException(ErrorConstants.COMMAND_GETCOUNTERS_EXEC, re);
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
		LogHelper.trace(LogHelper.TRACE, "[GetCurrentCounterCommand] init");
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
	 * Return the current counters,
	 * which were retrieved in the execute() method.
	 *
	 * @return Vector	A set of counters
	 */
	public Vector getCurrentCounters()
	{
		return g_vCounters;
	}
	/**
	 * Translates the technical name of a counter to a dutch equivalent
	 * 
	 * @param  String	Name of the counter to be translated
	 * @return String	If counter has been recognized, the dutch equivalent else an empty string
	 */
	public String translateCounterKey(String sKey)
	{
		return CounterKeys.getDutchTranslationForCounter(sKey);
	}
	/**
	 * Translates the technical name of a component to a dutch equivalent
	 * 
	 * @param  String	Name of the component to be translated
	 * @return STring	If componentname has been recognized, the dutch equivalent else an empty string
	 */
	public String translateComponent(String sComponent)
	{
		return ComponentType.translateComponentNameToDutch(sComponent);
	}
}