package ie.ucd.srg.koa.scheduler.beans;
import java.util.Enumeration;
import javax.ejb.*;
import javax.naming.NamingException;
/**
 * JobtypeToScheduledjobLink
 * @generated
 */
public class JobtypeToScheduledjobLink
	extends ie.ucd.srg.ivj.ejb.associations.links.SingleToManyLink
{
	/**
	 * @generated
	 */
	private static ie.ucd.srg
		.koa
		.scheduler
		.beans
		.ScheduledjobHome targetHome;
	/**
	 * @generated
	 */
	private static final java.lang.String targetHomeName = "Scheduledjob";
	/**
	 * Create a link instance with the passed source bean.
	 * @generated
	 */
	public JobtypeToScheduledjobLink(javax.ejb.EntityBean anEntityBean)
	{
		super(anEntityBean);
	}
	/**
	 * Get the target home for the link.
	 * @generated
	 */
	protected synchronized ie.ucd.srg
		.koa
		.scheduler
		.beans
		.ScheduledjobHome getTargetHome(
			ie.ucd.srg.ivj.ejb.associations.links.Link aLink)
		throws javax.naming.NamingException
	{
		if (targetHome == null)
			targetHome =
				(ie.ucd.srg
					.koa
					.scheduler
					.beans
					.ScheduledjobHome) lookupTargetHome("java:comp/env/ejb/Scheduledjob",
					ie.ucd.srg.koa.scheduler.beans.ScheduledjobHome.class);
		return targetHome;
	}
	/**
	 * Fetch the target for this many link, return an enumeration of the members.
	 * @generated
	 */
	protected java.util.Enumeration fetchTargetEnumeration()
		throws javax.ejb.FinderException, java.rmi.RemoteException
	{
		Enumeration enumer = null;
		try
		{
			enumer =
				(
					(ie.ucd.srg
						.koa
						.scheduler
						.beans
						.ScheduledjobHome) getTargetHome(
						this)).findScheduledjobByJobtype(
					(ie.ucd.srg
						.koa
						.scheduler
						.beans
						.JobtypeKey) getEntityContext()
						.getPrimaryKey());
		}
		catch (NamingException e)
		{
			throw new FinderException(e.toString());
		}
		return enumer;
	}
	/**
	 * Get the entity context from the source bean.
	 * @generated
	 */
	public javax.ejb.EntityContext getEntityContext()
	{
		return ((ie.ucd.srg.koa.scheduler.beans.JobtypeBean) source)
			.getEntityContext();
	}
	/**
	 * Return whether or not the source key is valid for querying.
	 * @generated
	 */
	protected boolean isKeyValid()
	{
		return (getEntityContext().getPrimaryKey() != null);
	}
	/**
	 * Direct my counterLink to set my source as its target.
	 * @generated
	 */
	public void secondarySetCounterLinkOf(javax.ejb.EJBObject anEJB)
		throws java.rmi.RemoteException
	{
		if (anEJB != null)
			(
				(
						ie.ucd.srg
						.koa
						.scheduler
						.beans
						.Scheduledjob) anEJB)
						.secondarySetJobtype(
				(ie.ucd.srg.koa.scheduler.beans.Jobtype) getEntityContext()
					.getEJBObject());
	}
	/**
	 * Send my counterLink #secondaryDisconnect by routing through my target EJB.
	 * @generated
	 */
	public void setNullCounterLinkOf(javax.ejb.EJBObject anEJB)
		throws java.rmi.RemoteException
	{
		if (anEJB != null)
			(
				(
						ie.ucd.srg
						.koa
						.scheduler
						.beans
						.Scheduledjob) anEJB)
						.setJobtype(
				null);
	}
	/**
	 * Send my counterLink #secondaryDisconnect by routing through my target EJB.
	 * @generated
	 */
	public void secondarySetNullCounterLinkOf(javax.ejb.EJBObject anEJB)
		throws java.rmi.RemoteException
	{
		if (anEJB != null)
			(
				(
						ie.ucd.srg
						.koa
						.scheduler
						.beans
						.Scheduledjob) anEJB)
						.secondarySetJobtype(
				null);
	}
	/**
	 * Add an element to this many link.  Disallow null adds.
	 * @generated
	 */
	public void addElement(javax.ejb.EJBObject targetEJB)
		throws java.rmi.RemoteException
	{
		if (targetEJB != null)
		{
			super.addElement(targetEJB);
			(
				(
						ie.ucd.srg
						.koa
						.scheduler
						.beans
						.Scheduledjob) targetEJB)
						.privateSetJobtypeKey(
				(ie.ucd.srg
					.koa
					.scheduler
					.beans
					.JobtypeKey) getEntityContext()
					.getPrimaryKey());
		}
	}
	/**
	 * Narrow the remote object.
	 * @generated
	 */
	protected javax.ejb.EJBObject narrowElement(java.lang.Object element)
		throws java.rmi.RemoteException
	{
		return (EJBObject) javax.rmi.PortableRemoteObject.narrow(
			element,
			ie.ucd.srg.koa.scheduler.beans.Scheduledjob.class);
	}
	/**
	 * Check if I contain anEJB by comparing primary keys.
	 * @generated
	 */
	protected boolean currentlyContains(javax.ejb.EJBObject anEJB)
		throws java.rmi.RemoteException
	{
		return super.currentlyContains(anEJB)
			|| getEntityContext().getPrimaryKey().equals(
				((ie.ucd.srg.koa.scheduler.beans.Scheduledjob) anEJB)
					.getJobtypeKey());
	}
}
