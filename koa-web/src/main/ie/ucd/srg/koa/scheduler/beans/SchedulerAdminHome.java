package ie.ucd.srg.koa.scheduler.beans;
/**
 * Home interface for Enterprise Bean: SchedulerAdmin
 */
public interface SchedulerAdminHome extends javax.ejb.EJBHome
{
	/**
	 * Creates a default instance of Session Bean: SchedulerAdmin
	 */
	public ie.ucd.srg.koa.scheduler.beans.SchedulerAdmin create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
}
