/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.scheduler.Job.java
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
  *  0.1		25-04-2003	MKu			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.scheduler;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.koa.scheduler.JobContext;
/**
 * Job interface used for all jobs for the scheduler
 * 
 * @author KuijerM
 * 
 */
public interface Job
{
	public void setContext(JobContext context);
	public JobContext getContext();
	public boolean execute() throws EPlatformException;
	public boolean init() throws EPlatformException;
}