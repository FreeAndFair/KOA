package ie.ucd.srg.koa.scheduler.beans;
import java.rmi.RemoteException;

import ie.ucd.srg.koa.scheduler.beans.Jobtype;
/**
 * Home interface for Enterprise Bean: Scheduledjob
 */
public interface ScheduledjobHome extends javax.ejb.EJBHome
{
	/**
	 * Creates an instance from a key for Entity Bean: Scheduledjob
	 */
	public ie.ucd.srg.koa.scheduler.beans.Scheduledjob create(
		java.math.BigDecimal jobid)
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	public ie.ucd.srg.koa.scheduler.beans.Scheduledjob create(
		java.math.BigDecimal jobid,
		Jobtype aJobtype,
		java.sql.Timestamp startTime,
		String status,
		String context,
		Integer priority)
		throws javax.ejb.CreateException, RemoteException;
	/**
	 * Finds an instance using a key for Entity Bean: Scheduledjob
	 */
	public ie.ucd.srg.koa.scheduler.beans.Scheduledjob findByPrimaryKey(
		ie.ucd.srg.koa.scheduler.beans.ScheduledjobKey primaryKey)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
	/**
	 * This method was generated for supporting the association named jobtype.
	 * It will be deleted/edited when the association is deleted/edited.
	 */
	public java.util.Enumeration findScheduledjobByJobtype(
		ie.ucd.srg.koa.scheduler.beans.JobtypeKey inKey)
		throws java.rmi.RemoteException, javax.ejb.FinderException;
}
