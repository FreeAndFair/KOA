/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.databeheer.ticket.KOATicketServlet.java
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
  *  1.0		11-04-2003	MRo		First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.databeheer.ticket;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.logica.eplatform.servlet.UtilServlet;
import ie.ucd.srg.logica.eplatform.ticket.Ticket;
import ie.ucd.srg.logica.eplatform.ticket.TicketConstants;
import ie.ucd.srg.logica.eplatform.ticket.TicketFactory;
import ie.ucd.srg.logica.eplatform.ticket.TicketRequest;
import ie.ucd.srg.koa.ticket.KOADatabeheerTicketFactory;
import ie.ucd.srg.koa.ticket.PrincipalTicketRequest;
import ie.ucd.srg.koa.utils.KOALogHelper;
/**
 * Servlet to handle the management of tickets for the web channel.
 * Creation date: (07-04-2003 14:07:30)
 * @author: KuijerM
 */
public class KOADatabeheerTicketServlet	extends UtilServlet{
	
	protected TicketFactory factory;
	/**
	 * init the ticket factory
		 */
	public void init()
	{
		/* get the singleton implementation of the ticket factory */
		factory = KOADatabeheerTicketFactory.getTicketFactory();
		super.init();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		performTask(request, response);
	}
	public void doPost(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException
	{
		performTask(request, response);
	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException
	{
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[KOADatabeheerTicketServlet.performTask] try to get ticket for chairman");
		TicketRequest tr = new PrincipalTicketRequest(request);
		Ticket ticket = null;
		try
		{
			ticket = factory.getTicket(tr);
		}
		catch (EPlatformException ep)
		{
			KOALogHelper.logError(
				"KOADatabeheerTicketServlet.performTask",
				"Could Eplatform exception during get ticket",
				ep);
			request.setAttribute(
				"ERROR",
				"You are not authorized to use this application.");
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
				"KOADatabeheerTicketServlet.performTask",
				"No ticket found",
				null);
			request.setAttribute(
				"ERROR",
				"You are not authorized to use this application.");
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
		redirect(
			request,
			response,
			(String) request.getSession(true).getAttribute(
				TicketConstants.REQUESTED_RESOURCE_IN_SESSION));
	}
}