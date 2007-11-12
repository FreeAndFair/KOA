/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.servlet.KOAServlet.java
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
  *  1.0		07-04-2003	KuijerM		First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.voorzitter.servlet;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.*;
import javax.servlet.*;
import ie.ucd.srg.logica.eplatform.command.http.HttpCommand;
import ie.ucd.srg.logica.eplatform.error.ErrorMessageFactory;
import ie.ucd.srg.logica.eplatform.util.LogHelper;
import ie.ucd.srg.logica.eplatform.util.SystemProperties;
import ie.ucd.srg.koa.voorzitter.command.CommandConstants;
import ie.ucd.srg.koa.voorzitter.command.KOACommandFactory;
import ie.ucd.srg.logica.eplatform.ticket.*;
/**
 * The servlet that is used for all incoming requests that require 
 * a command. This servlet is called through different servlet aliases
 * that enables to differentation of commands.
 * 
 * @author: KuijerM
 */
public class KOAServlet
	extends ie.ucd.srg.logica.eplatform.servlet.AbstractBaseServlet
{
	/* the instance of the commandfactory to get the commands */
	private /*@ non_null @*/ ie.ucd.srg.logica.eplatform.command.http.HttpCommandFactory commandFactory;
	/** 
	 * Initializes the command factory.
	 */
	//@ signals (Exception) false;
	public void initCommandFactory()
	{
		LogHelper.trace(LogHelper.TRACE, "[KOAServlet] initCommandFactory");
		commandFactory = KOACommandFactory.getHttpCommandFactory();
	}
	/**
	 * Initializes the Misc.
	 */
	//@ signals (Exception) false;
	public void initMisc()
	{
		LogHelper.trace(LogHelper.TRACE, "[KOAServlet] initMisc");
	}
	/**
	 * Initializes the services.
	 */
	//@ signals (Exception) false;
	public void initServices()
	{
		LogHelper.trace(LogHelper.TRACE, "[KOAServlet] initServices");
		// initServices is no langer needed. Use the EventHandler or the LogHelper
	}
	/**
	 * Called when no ticket is yet available in the session.
	 *
	 * @param HttpServletRequest	Object that encapsulates the request to the servlet
	 * @param HttpServletResponse	Object that encapsulates the response from the servlet
	 *
	 * @throws ServletException		thrown by super.redirect
	 * @throws IOException			thrown by super.redirect
	 */
	//@ requires request != null;
	//@ requires resonse != null;
	//@ signals (Exception) false;
	public void noTicket(
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException
	{
		LogHelper.trace(LogHelper.TRACE, "[KOAServlet] noTicket");
		/* filter the current requested location */
		Enumeration e = request.getParameterNames();
		StringBuffer buf = new StringBuffer();
		String element;
		while (e.hasMoreElements())
		{
			element = ((String) e.nextElement());
			buf.append(element + "=");
			buf.append(request.getParameter(element) + "&");
		}
		/* store the current requested location */
		request.getSession(true).setAttribute(
			TicketConstants.REQUESTED_RESOURCE_IN_SESSION,
			request.getServletPath() + "?" + buf.toString());
		LogHelper.trace(
			LogHelper.TRACE,
			"[KOAServlet] request saved in session");
		/* redirect the user to the ticket login page, to obtain a ticket */
		redirect(request, response, "/Ticket");
	}
	/**
	 * Process incoming HTTP GET requests
	 *
	 * @param HttpServletRequest	Object that encapsulates the request to the servlet
	 * @param HttpServletResponse	Object that encapsulates the response from the servlet
	 *
	 * @throws ServletException		thrown by performTask
	 * @throws IOException			thrown by performTask
	 */
	//@ requires request != null;
	//@ requires resonse != null;
	//@ signals (Exception) false;
	public void performGetTask(
		javax.servlet.http.HttpSerdvletRequest request,
		javax.servlet.http.HttpServletResponse response)
		throws javax.servlet.ServletException, java.io.IOException
	{
		performTask(request, response);
	}
	/**
	 * Process incoming HTTP POST requests
	 *
	 * @param HttpServletRequest	Object that encapsulates the request to the servlet
	 * @param HttpServletResponse	Object that encapsulates the response from the servlet
	 *
	 * @throws ServletException		thrown by performTask
	 * @throws IOException			thrown by performTask
	 */
	//@ requires request != null;
	//@ requires resonse != null;
	//@ signals (Exception) false;
	public void performPostTask(
		javax.servlet.http.HttpServletRequest request,
		javax.servlet.http.HttpServletResponse response)
		throws javax.servlet.ServletException, java.io.IOException
	{
		LogHelper.trace(LogHelper.TRACE, "[KOAServlet] PerformPostTask Post");
		performTask(request, response);
	}
	/**
	 * Process incoming HTTP GET or POST requests
	 *
	 * @param HttpServletRequest	Object that encapsulates the request to the servlet
	 * @param HttpServletResponse	Object that encapsulates the response from the servlet
	 *
	 * @throws ServletException		thrown by super.redirect
	 * @throws IOException			thrown by super.redirect
	 */
	//@ requires request != null;
	//@ requires resonse != null;
	//@ signals (Exception) false;
	public void performTask(
		javax.servlet.http.HttpServletRequest request,
		javax.servlet.http.HttpServletResponse response)
		throws javax.servlet.ServletException, java.io.IOException
	{
		LogHelper.trace(LogHelper.TRACE, "[KOAServlet] performTask");
		HttpCommand command = null;
		try
		{
			/* Determine the command Factory */
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAServlet] Getting command from CommandFactory");
			command = commandFactory.getCommand(request);
			/* set command target */
			command.setCommandTarget(commandTarget);
			/* pre execute command */
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAServlet] Starting preExecution on Command:" + command);
			command.preExecution();
			/* execute command */
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAServlet] Starting performExecute on Command: " + command);
			command = (HttpCommand) command.performExecute(getTicket(request));
			/* post execute command */
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAServlet] Starting postExecution on Command: " + command);
			command.postExecution();
			/* update the current http session */
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAServlet] Starting updateSession on Command: " + command);
			command.updateSession(request.getSession());
			/* store the current command in the session */
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAServlet] Save command in Request on Command: " + command);
			request.setAttribute(
				CommandConstants.COMMAND_IN_REQUEST_KEY,
				command);
			/* redirect the user to the result page for the command */
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAServlet]: Forwarding to: " + command.getResultJSP());
			redirect(request, response, command.getResultJSP());
		}
		catch (Exception e)
		{
			/* display errormessages made by the ErrorMessageFactory */
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAServlet] A "
					+ e.getClass().getName()
					+ " was thrown with message: "
					+ e.getMessage());
			e.printStackTrace();
			ErrorMessageFactory emf =
				ErrorMessageFactory.getErrorMessageFactory();
			request.setAttribute("ERROR", emf.getErrorMessage(e));
			if (command == null)
			{
				SystemProperties props = SystemProperties.getSystemProperties();
				LogHelper.trace(
					LogHelper.TRACE,
					"[KOAServlet] EPlatformException: Forwarding Error to default errorpage ");
				redirect(
					request,
					response,
					props.getProperty(
						"ie.ucd.srg.logica.eplatform.error.DefaultErrorPage"));
			}
			else
			{
				LogHelper.trace(
					LogHelper.TRACE,
					"[KOAServlet] Forwarding Error to: "
						+ command.getErrorJSP());
				redirect(request, response, command.getErrorJSP());
			}
		}
	}
}