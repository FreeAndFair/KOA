package ie.ucd.srg.koa.koaschema;
/**
 * Home interface for Enterprise Bean: Districten
 */
public interface DistrictenHome extends javax.ejb.EJBHome
{
	/**
	 * Creates an instance from a key for Entity Bean: Districten
	 */
	public ie.ucd.srg.koa.koaschema.Districten create(
		java.lang.String districtnummer)
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * Finds an instance using a key for Entity Bean: Districten
	 */
	public ie.ucd.srg.koa.koaschema.Districten findByPrimaryKey(
		ie.ucd.srg.koa.koaschema.DistrictenKey primaryKey)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
	/**
	 * This method was generated for supporting the association named fk_dist_kkr.
	 * It will be deleted/edited when the association is deleted/edited.
	 */
	public java.util.Enumeration findDistrictenByFk_dist_kkr(
		ie.ucd.srg.koa.koaschema.KieskringenKey inKey)
		throws java.rmi.RemoteException, javax.ejb.FinderException;
}
