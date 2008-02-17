/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.ticket.KOATicketFactory.java
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
package ie.ucd.srg.koa.ticket;
import java.util.Date;
import java.util.Vector;

import ie.ucd.srg.logica.eplatform.ticket.Ticket;
import ie.ucd.srg.logica.eplatform.ticket.TicketConstants;
import ie.ucd.srg.logica.eplatform.ticket.TicketFactory;
import ie.ucd.srg.logica.eplatform.ticket.TicketRequest;
import ie.ucd.srg.koa.ticket.KOATicket;
import ie.ucd.srg.koa.ticket.PrincipalTicketRequest;
/**
 * The factory to create tickets.
 * 
 * @author: KuijerM
 */
public class KOAVoorzitterTicketFactory
	implements ie.ucd.srg.logica.eplatform.ticket.TicketFactory
{
	/* Singleton implementation */
	static KOAVoorzitterTicketFactory instance = null;
	/**
	 * Private constructor
	 */
	private KOAVoorzitterTicketFactory()
	{
	}
	/**
	 * Get a ticket based on the ticket request
	 * 
	 * @param request the ticket request containing the username and password
	 * 
	 * @return Ticket the ticket based on the credentials
	 * 
	 */
	//@ requires request != null;
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public Ticket getTicket(TicketRequest request)
	{
		Vector roles = new Vector();
		/* get the username and password from the ticket request */
		PrincipalTicketRequest tr = (PrincipalTicketRequest) request;
		String sUser = tr.getUserName();
		
		if (sUser == null)
		{
			return null;
		}
		if (tr.isUerInRole(KOATicket.VOORZITTER_ROLE))
		{
			/* add the chairman role to the roles vector */
			roles.add(KOATicket.VOORZITTER_ROLE);
			/* return the ticket based on the credentials */
			return new KOATicket(
				sUser,
				roles,
				new Date(
					System.currentTimeMillis()
						+ (Long.valueOf(TicketConstants.TICKET_EXPIRY_TIME)).longValue()));
		}
		else
		{
			/* if the user does not have the right role, return null */
			return null;
		}
	}
	
	/**
	 * Gets the singleton implementation of the ticket factory. Static method.
	 * 
	 * @return TicketFactory The singleton implementation of the ticketfactory
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public static TicketFactory getTicketFactory()
	{
		if (instance == null)
		{
			/* if no instance is created yet, create the instance */
			instance = new KOAVoorzitterTicketFactory();
		}
		/* return the implementation */
		return instance;
	}
}