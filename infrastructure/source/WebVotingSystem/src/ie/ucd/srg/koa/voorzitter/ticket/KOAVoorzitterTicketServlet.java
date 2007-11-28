/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.ticket.KOATicketServlet.java
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
package ie.ucd.srg.koa.voorzitter.ticket;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.logica.eplatform.ticket.Ticket;
import ie.ucd.srg.logica.eplatform.ticket.TicketConstants;
import ie.ucd.srg.logica.eplatform.ticket.TicketFactory;
import ie.ucd.srg.logica.eplatform.ticket.TicketRequest;
import ie.ucd.srg.koa.ticket.KOAVoorzitterTicketFactory;
import ie.ucd.srg.koa.ticket.PrincipalTicketRequest;
import ie.ucd.srg.koa.utils.KOALogHelper;
/**
 * Servlet to handle the management of tickets for the web channel.
 * 
 * @author: KuijerM
 */
public class KOAVoorzitterTicketServlet
	extends ie.ucd.srg.logica.eplatform.servlet.UtilServlet
{
	protected /*@ non_null @*/ TicketFactory factory;
	/**
	 * init the ticket factory
		*/
	//@ signals (Exception) false;
	public void init()
	{
		/* get the singleton implementation of the ticket factory */
		factory = KOAVoorzitterTicketFactory.getTicketFactory();
		super.init();
	}
	/**
	 * Handle the GET request
	 * 
	 * @param HttpServletRequest	Object that encapsulates the request to the servlet
	 * @param HttpServletResponse	Object that encapsulates the response from the servlet
	 * 
	 * @throws ServletException thrown by performTask(request, response)
	 * @throws IOException		thrown by performTask(request, response)
	 */
	//@ requires request != null;
	//@ requires response != null;
	//@ signals (Exception) false;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		performTask(request, response);
	}
	/**
	 * Handle the POST request
	 * 
	 * @param HttpServletRequest	Object that encapsulates the request to the servlet
	 * @param HttpServletResponse	Object that encapsulates the response from the servlet
	 * 
	 * @throws ServletException thrown by performTask(request, response)
	 * @throws IOException		thrown by performTask(request, response)
	 */
	//@ requires request != null;
	//@ requires response != null;
	//@ signals (Exception) false;
	public void doPost(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException
	{
		performTask(request, response);
	}
	/**
	 * Handle the GET or POST request
	 * 
	 * @param HttpServletRequest	Object that encapsulates the request to the servlet
	 * @param HttpServletResponse	Object that encapsulates the response from the servlet
	 * 
	 * @throws ServletException thrown by super.redirect
	 * @throws IOException		thrown by super.redirect
	 */
	//@ requires request != null;
	//@ requires response != null;
	//@ signals (Exception) false;
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException
	{
		//java.util.Enumeration enum = request.getParameterNames();
		System.out.println(request.getQueryString());
		//while(enum.hasMoreElements()){
			//System.out.println(request.getAttribute((String) enum.nextElement()));
		//}
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[KOAVoorzitterTicketServlet.performTask] try to get ticket for chairman");
		TicketRequest tr = new PrincipalTicketRequest(request);
		Ticket ticket = null;
		try
		{
			ticket = factory.getTicket(tr);
		}
		catch (EPlatformException ep)
		{
		KOALogHelper.logError(
				"KOAVoorzitterTicketServlet.performTask",
				"Could Eplatform exception during get ticket",
				ep);
			redirect(
				request,
				response,
				props.getProperty(
					"ie.ucd.srg.logica.eplatform.error.DefaultErrorPage"));
			return;
		}
		if (ticket == null)
		{
			KOALogHelper.logError(
				"KOAVoorzitterTicketServlet.performTask",
				"No ticket found",
				null);
			redirect(
				request,
				response,
				props.getProperty(
					"ie.ucd.srg.logica.eplatform.error.DefaultErrorPage"));
			return;
		}
		request.getSession(true).setAttribute(
			TicketConstants.TICKET_IN_SESSION,
			ticket);
		
		//request.getSession(true).setAttribute(TicketConstants.REQUESTED_RESOURCE_IN_SESSION, "/Index");
		
		redirect(
			request,
			response,
			(String) request.getSession(true).getAttribute(
				TicketConstants.REQUESTED_RESOURCE_IN_SESSION));
	}
}