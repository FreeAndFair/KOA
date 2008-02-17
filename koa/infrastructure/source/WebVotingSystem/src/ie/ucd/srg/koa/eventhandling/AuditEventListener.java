/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.eventhandling.AuditEventListener.java
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
  *  0.1		23-04-2003	KuijerM		First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.eventhandling;
import java.rmi.RemoteException;
import java.util.Hashtable;
import javax.ejb.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import ie.ucd.srg.logica.eplatform.eventhandling.Event;
import ie.ucd.srg.logica.eplatform.eventhandling.EventListener;
import ie.ucd.srg.logica.eplatform.eventhandling.EventListenerContext;
import ie.ucd.srg.koa.constants.JNDIProperties;
import ie.ucd.srg.koa.eventhandling.KOAEvent;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.session.beans.UtilitySessionEJB;
import ie.ucd.srg.koa.session.beans.UtilitySessionEJBHome;
/**
 * This Implementation logs events to the LogService from the Services Framework.
 * 
 * @author: KuijerM
 * 
 */
public class AuditEventListener implements EventListener
{
	private ie.ucd.srg.logica.eplatform.services.logging.LogService logService;
	private static int DEFAULT_MINLEVEL = -1;
	private static int DEFAULT_MAXLEVEL = 6;
	private int minLevel = DEFAULT_MINLEVEL;
	private int maxLevel = DEFAULT_MAXLEVEL;
	private java.lang.String serviceName = "AuditService";
	public static String VOTER = "VOTER";
	public static String DATA_MANAGEMENT = "DATA_MANAGEMENT";
	public static String STATE_CHANGE = "STATE_CHANGE";
	public static String FINGERPRINT = "FINGERPRINT";
	/**
	 * constructor for the event listener
	 * 
	 */
	public AuditEventListener()
	{
		System.out.println("[AuditEventListener] Starting LogEventListener");
	}
	/**
	 * initialize the event listener with the parameters from the properties
	 * if no parameters are specified the default parameters are used.
	 * 
	 * @param elc The context of the eventlistener containing the parameters
	 */
	public void init(EventListenerContext elc)
	{
		try
		{
			/* try to get the minimum log level */
			minLevel = Integer.parseInt((String) elc.getProperty("minlevel"));
		}
		catch (NumberFormatException nfe)
		{
			/* if no proper minimum level can be found, use the default. */
			System.out.println(
				"[AuditEventListener] No proper minlevel specified, default used.");
		}
		try
		{
			/* try to get the maximum log level */
			maxLevel = Integer.parseInt((String) elc.getProperty("maxlevel"));
		}
		catch (NumberFormatException nfe)
		{
			/* if no proper maximum level can be found, use the default. */
			System.out.println(
				"[AuditEventListener] No proper maxlevel specified, default used.");
		}
		try
		{
			/* try to get the service name */
			serviceName = elc.getProperty("serviceName");
		}
		catch (Exception e)
		{
			/* if no proper service name can be found, use the default. */
			System.out.println(
				"[AuditEventListener] No serviceName set. default used");
		}
	}
	/**
	 * The on log event event handling.
	 * Logs the incoming event, if it is between the minimum and maximum severity
	 * If it is an error it is logged to the standard error, else it is 
	 * logged to the standard out.
	 * 
	 * @param event The event to log
	 */
	public void onEvent(Event event)
	{
		/* check if the event should be logged by this event listener */
		if ((event.getSeverity() >= minLevel)
			&& (event.getSeverity() <= maxLevel))
		{
			try
			{
				String sMessage = event.getMessage();
				String sComponent = null;
				String sActor = null;
				String sAction = null;
				int iSeverity = event.getSeverity();
				/* cast the event to a koa event */
				if (event instanceof KOAEvent)
				{
					KOAEvent koaEvent = (KOAEvent) event;
					sComponent = koaEvent.getComponent();
					sActor = koaEvent.getActor();
					sAction = koaEvent.getAction();
				}
				/* init context */
				Hashtable htProps = new Hashtable();
				htProps.put(
					Context.INITIAL_CONTEXT_FACTORY,
					JNDIProperties.getProperty(
						JNDIProperties.KOA_UTILITY_CONTEXT_FACTORY));
				htProps.put(
					Context.PROVIDER_URL,
					JNDIProperties.getProperty(
						JNDIProperties.KOA_UTILITY_PROVIDER));
				InitialContext icContext = new InitialContext(htProps);
				/* get the controller remote interface */
				Object obj =
					icContext.lookup(
						JNDIProperties.getProperty(
							JNDIProperties.KOA_UTILITY_JNDI_NAME));
				UtilitySessionEJBHome xUtilitySessionEJBHome =
					(UtilitySessionEJBHome) PortableRemoteObject.narrow(
						obj,
						UtilitySessionEJBHome.class);
				UtilitySessionEJB xUtilitySessionEJB =
					xUtilitySessionEJBHome.create();
				/* log the audit record */
				xUtilitySessionEJB.logAuditRecord(
					iSeverity,
					sAction,
					sComponent,
					sActor,
					event.getMessage());
			}
			/* if something goes wrong, log the error */
			catch (CreateException ce)
			{
				/* log the error */
				System.err.println(
					"[AuditEventListener] Could not log the audit log, Create Exception during logging. ");
				ce.printStackTrace();
				System.err.println(
					"[AuditEventListener] Integrity of the auditlog is not garanteed, because auditlog cannot be written.");
			}
			/* if something goes wrong, log the error */
			catch (NamingException ne)
			{
				/* log the error */
				System.err.println(
					"[AuditEventListener] Could not log the audit log, Naming Exception during logging. ");
				ne.printStackTrace();
				System.err.println(
					"[AuditEventListener] Integrity of the auditlog is not garanteed, because auditlog cannot be written.");
			}
			/* if something goes wrong, log the error */
			catch (RemoteException re)
			{
				/* log the error */
				System.err.println(
					"[AuditEventListener] Could not log the audit log, Remote Exception during logging. ");
				re.printStackTrace();
				System.err.println(
					"[AuditEventListener] Integrity of the auditlog is not garanteed, because auditlog cannot be written.");
			}
			/* if something goes wrong, log the error */
			catch (KOAException ex)
			{
				/* log the error */
				System.err.println(
					"[AuditEventListener] Could not log the audit log, KOAException during logging. ");
				ex.printStackTrace();
				System.err.println(
					"[AuditEventListener] Integrity of the auditlog is not garanteed, because auditlog cannot be written.");
			}
		}
	}
}