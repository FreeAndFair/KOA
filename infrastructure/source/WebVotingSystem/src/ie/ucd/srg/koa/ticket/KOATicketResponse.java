/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.ticket.KOATicketResponse.java
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
  *  0.1		07-04-2003	Xui		First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.ticket;
import ie.ucd.srg.logica.eplatform.ticket.Ticket;
import ie.ucd.srg.koa.dataobjects.StemTransactie;
/**
 * KoaTicketResponse wraps the ticket object and the stemtransactie object
 */
public class KOATicketResponse
{
	private Ticket xTicket = null;
	private StemTransactie xStemTransactie = null;
	private String sMessage = null;
	private int iTimeToUnlock = 0;
	/**
	 * Gets the ticket
	 * 
	 * @return Ticket
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ Ticket getTicket()
	{
		return xTicket;
	}
	/**
	 * Sets the ticket
	 * 
	 * @param ticket The ticket to set
	 */
	//@ requires ticket != null;
	//@ signals (Exception) false;
	public void setTicket(Ticket ticket)
	{
		xTicket = ticket;
	}
	/**
	 * Gets the stemTransactie
	 * 
	 * @return StemTransactie
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ StemTransactie getStemTransactie()
	{
		return xStemTransactie;
	}
	/**
	 * Sets the stemTransactie
	 * 
	 * @param stemTransactie The stemTransactie to set
	 */
	//@ requires stemTransactie != null;
	//@ signals (Exception) false;
	public void setStemTransactie(StemTransactie stemTransactie)
	{
		xStemTransactie = stemTransactie;
	}
	/**
	 * Gets the message
	 * 
	 * @return String
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ String getMessage()
	{
		return sMessage;
	}
	/**
	 * Sets the message
	 * 
	 * @param message The message to set
	 */
	//@ requires message != null;
	//@ signals (Exception) false;
	public void setMessage(String message)
	{
		sMessage = message;
	}
	/**
	 * Gets the timeToUnlock
	 * 
	 * @return int
	 */
	//@ signals (Exception) false;
	public /*@ pure @*/ int getTimeToUnlock()
	{
		return iTimeToUnlock;
	}
	/**
	 * Sets the timeToUnlock
	 * 
	 * @param timeToUnlock The timeToUnlock to set
	 */
	//@ signals (Exception) false;
	public void setTimeToUnlock(int timeToUnlock)
	{
		iTimeToUnlock = timeToUnlock;
	}
}