/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.voorzitter.command.JobTypesAdministrationCommand.java
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
  *  0.1		28-04-2003	MKu	        First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.databeheer.command;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.koa.databeheer.command.AbstractAdministrationCommand;
import ie.ucd.srg.koa.databeheer.command.CommandConstants;
import ie.ucd.srg.koa.dataobjects.JobTypesAdministrationObject;
import ie.ucd.srg.koa.utils.KOALogHelper;
/**
 * Command for administration of the Job Types for the scheduler
 * 
 * @author KuijerM
 * 
 */
public class JobTypesAdministrationCommand
	extends AbstractAdministrationCommand
{
	/**
	 * Initialize the command and read all parameters from request
	 * if an overview object is set in the session, the object is read from session
	 * parameters that can be provided: 
	 * 	'id' Selected key
	 *  'page' The page number
	 * 
	 * @param request javax.servlet.http.HttpServletRequest
	 */
	public void init(javax.servlet.http.HttpServletRequest request)
		throws EPlatformException
	{
		KOALogHelper.log(
			KOALogHelper.INFO,
			"[JobTypesAdministrationCommand] init");
		super.init(request);
		// retrieve the overview if available
		//
		Object obj =
			request.getSession().getAttribute(
				CommandConstants.ADMIN_OBJECT_IN_SESSION);
		if (obj != null && obj instanceof JobTypesAdministrationObject)
			overview = (JobTypesAdministrationObject) obj;
	}
	/**
	 * Executes the command in the container
	 * 
	 */
	public void execute()
		throws ie.ucd.srg.logica.eplatform.command.CommandException, EPlatformException
	{
		KOALogHelper.log(
			KOALogHelper.INFO,
			"[JobTypesAdministrationCommand] execute");
		// else, show the appropriate key of the current one if available
		if (overview != null && pageNo != 1)
		{
			// do nothing
			KOALogHelper.log(KOALogHelper.TRACE, "Overview object reused");
		}
		// refresh
		else if (overview != null && pageNo == 1)
		{
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Refresh Overview, with current Settings");
			overview.search();
		}
		// New one needed
		else
		{
			overview = new JobTypesAdministrationObject();
			overview.search();
		}
	}

}