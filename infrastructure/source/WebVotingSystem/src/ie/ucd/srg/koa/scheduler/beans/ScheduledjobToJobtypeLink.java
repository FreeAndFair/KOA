package ie.ucd.srg.koa.scheduler.beans;
import javax.naming.NamingException;
import javax.ejb.*;
/**
 * ScheduledjobToJobtypeLink
 * @generated
 */
public class ScheduledjobToJobtypeLink
	extends ie.ucd.srg.ivj.ejb.associations.links.ManyToSingleLink
{
	/**
	 * @generated
	 */
	private static ie.ucd.srg.koa.scheduler.beans.JobtypeHome targetHome;
	/**
	 * @generated
	 */
	private static final java.lang.String targetHomeName = "Jobtype";
	/**
	 * Create a link instance with the passed source bean.
	 * @generated
	 */
	public ScheduledjobToJobtypeLink(javax.ejb.EntityBean anEntityBean)
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
		.JobtypeHome getTargetHome(
			ie.ucd.srg.ivj.ejb.associations.links.Link aLink)
		throws javax.naming.NamingException
	{
		if (targetHome == null)
			targetHome =
				(ie.ucd.srg
					.koa
					.scheduler
					.beans
					.JobtypeHome) lookupTargetHome("java:comp/env/ejb/Jobtype",
					ie.ucd.srg.koa.scheduler.beans.JobtypeHome.class);
		return targetHome;
	}
	/**
	 * Fetch the target for this single link, return an instance of the target class.
	 * @generated
	 */
	protected javax.ejb.EJBObject fetchTarget()
		throws javax.ejb.FinderException, java.rmi.RemoteException
	{
		EJBObject target = null;
		ie.ucd.srg.koa.scheduler.beans.JobtypeKey key =
			((ie.ucd.srg.koa.scheduler.beans.ScheduledjobBean) source)
				.getJobtypeKey();
		try
		{
			target =
				(
					(ie.ucd.srg
						.koa
						.scheduler
						.beans
						.JobtypeHome) getTargetHome(
						this)).findByPrimaryKey(
					key);
		}
		catch (NamingException e)
		{
			throw new FinderException(e.toString());
		}
		return target;
	}
	/**
	 * Get the entity context from the source bean.
	 * @generated
	 */
	public javax.ejb.EntityContext getEntityContext()
	{
		return ((ie.ucd.srg.koa.scheduler.beans.ScheduledjobBean) source)
			.getEntityContext();
	}
	/**
	 * Return whether or not the source key is valid for querying.
	 * @generated
	 */
	protected boolean isKeyValid()
	{
		return (
			((ie.ucd.srg.koa.scheduler.beans.ScheduledjobBean) source)
				.getJobtypeKey()
				!= null);
	}
	/**
	 * Forward inverse maintenance through my target EJB.
	 * @generated
	 */
	public void secondaryRemoveElementCounterLinkOf(javax.ejb.EJBObject anEJB)
		throws java.rmi.RemoteException
	{
		if (anEJB != null)
			(
				(
						ie.ucd.srg
						.koa
						.scheduler
						.beans
						.Jobtype) anEJB)
						.secondaryRemoveScheduledjob(
				(ie.ucd.srg
					.koa
					.scheduler
					.beans
					.Scheduledjob) getEntityContext()
					.getEJBObject());
	}
	/**
	 * Forward inverse maintenance through my target EJB.
	 * @generated
	 */
	public void secondaryAddElementCounterLinkOf(javax.ejb.EJBObject anEJB)
		throws java.rmi.RemoteException
	{
		if (anEJB != null)
			(
				(
						ie.ucd.srg
						.koa
						.scheduler
						.beans
						.Jobtype) anEJB)
						.secondaryAddScheduledjob(
				(ie.ucd.srg
					.koa
					.scheduler
					.beans
					.Scheduledjob) getEntityContext()
					.getEJBObject());
	}
	/**
	 * Set the target for this single link, an instance of the target class.
	 * @generated
	 */
	public void set(javax.ejb.EJBObject targetEJB)
		throws java.rmi.RemoteException
	{
		super.set(targetEJB);
		if (targetEJB == null)
			(
				(
						ie.ucd.srg
						.koa
						.scheduler
						.beans
						.ScheduledjobBean) source)
						.privateSetJobtypeKey(
				null);
		else
			(
				(
						ie.ucd.srg
						.koa
						.scheduler
						.beans
						.ScheduledjobBean) source)
						.privateSetJobtypeKey(
				(ie.ucd.srg.koa.scheduler.beans.JobtypeKey) targetEJB
					.getPrimaryKey());
	}
	/**
	 * Reset my target key.
	 * @generated
	 */
	protected void resetKey() throws java.rmi.RemoteException
	{
		(
			(
					ie.ucd.srg
					.koa
					.scheduler
					.beans
					.ScheduledjobBean) source)
					.privateSetJobtypeKey(
			null);
	}
}
