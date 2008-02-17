package ie.ucd.srg.koa.controller.beans;
/**
 * Home interface for Enterprise Bean: Koa_state
 */
public interface Koa_stateHome extends javax.ejb.EJBHome
{
	/**
	 * Creates an instance from a key for Entity Bean: Koa_state
	 */
	public ie.ucd.srg.koa.controller.beans.Koa_state create(
		java.lang.Integer id)
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * Finds an instance using a key for Entity Bean: Koa_state
	 */
	public ie.ucd.srg.koa.controller.beans.Koa_state findByPrimaryKey(
		ie.ucd.srg.koa.controller.beans.Koa_stateKey primaryKey)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
}
