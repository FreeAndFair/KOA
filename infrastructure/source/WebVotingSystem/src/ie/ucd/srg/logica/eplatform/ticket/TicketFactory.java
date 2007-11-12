/*
 * Created on 27-Oct-2004
 */

package ie.ucd.srg.logica.eplatform.ticket;

import ie.ucd.srg.logica.eplatform.error.*;

/**
 * @author Alan E. Morkan
 */

public interface TicketFactory {

	/**
	 * 
	 * @param tr
	 * @throws EPlatformException
	 * @return
	 */
	//@ requires tr != null;
    public Ticket getTicket(TicketRequest tr)throws EPlatformException;

}
