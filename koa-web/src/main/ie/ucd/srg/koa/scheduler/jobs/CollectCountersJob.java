/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.scheduler.job.CollectCountersJob.java
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
package ie.ucd.srg.koa.scheduler.jobs;
import java.rmi.RemoteException;
import java.util.Hashtable;
import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.koa.constants.CounterKeys;
import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.constants.JNDIProperties;
import ie.ucd.srg.koa.controller.beans.Controller;
import ie.ucd.srg.koa.controller.beans.ControllerHome;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.scheduler.AbstractJob;
import ie.ucd.srg.koa.utils.KOALogHelper;
/**
 * Job used by the scheduler to collect the counters
 * 
 * @author KuijerM
 * 
 */
public class CollectCountersJob extends AbstractJob
{
	/**
	 * Constructor for the CollectCountersJob
	 */
	public CollectCountersJob()
	{
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[CollectCountersJob] Constructor...");
	}
	/**
	 * Executes the job
	 * 
	 * @return boolean indicating the success of the execution
	 * 
	 * @throws EPlatformException If something goes wrong
	 * 
	 */
	public boolean execute() throws EPlatformException
	{
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[CollectCountersJob.execute] Start executing the collection of the counters...");
		boolean bResult = true;
		try
		{
			/* initialize the variables */
			Hashtable htProps = new Hashtable();
			htProps.put(
				Context.INITIAL_CONTEXT_FACTORY,
				JNDIProperties.getProperty(
					JNDIProperties.CONTROLLER_CONTEXT_FACTORY));
			htProps.put(
				Context.PROVIDER_URL,
				JNDIProperties.getProperty(JNDIProperties.CONTROLLER_PROVIDER));
			/* get the initial context for the controller */
			InitialContext icContext = new InitialContext(htProps);
			/* find the controller */
			Object obj =
				icContext.lookup(
					JNDIProperties.getProperty(JNDIProperties.CONTROLLER_NAME));
			ControllerHome home =
				(ControllerHome) PortableRemoteObject.narrow(
					obj,
					ControllerHome.class);
			/* create the controller */
			Controller controller = home.create();
			/* collect the counters */
			controller.collectCounters(
				CounterKeys.INIT_SCHEDULER,
				CounterKeys.COUNTBEFORE);
		}
		catch (KOAException koae)
		{
			KOALogHelper.logError(
				"CollectCountersJob.execute",
				"Cannot find property",
				koae);
			bResult = false;
		}
		catch (CreateException ce)
		{
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"CollectCountersJob.execute",
				ErrorConstants.ERR_CREATE,
				params,
				ce);
			bResult = false;
		}
		catch (NamingException ne)
		{
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"CollectCountersJob.execute",
				ErrorConstants.ERR_NAMING,
				params,
				ne);
			bResult = false;
		}
		catch (RemoteException re)
		{
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"CollectCountersJob.execute",
				ErrorConstants.ERR_REMOTE,
				params,
				re);
			bResult = false;
		}
		return bResult;
	}
	/**
	 * Initialize the job
	 * 
	 */
	public boolean init() throws EPlatformException
	{
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[CollectCountersJob.init] initialize...");
		return true;
	}
}