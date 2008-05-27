package ie.ucd.srg.koa.esb.beans;
/**
 * Home interface for Enterprise Bean: Encryptedesb
 */
public interface EncryptedesbHome extends javax.ejb.EJBHome
{
	/**
	 * Creates an instance from a key for Entity Bean: Encryptedesb
	 */
	public ie.ucd.srg.koa.esb.beans.Encryptedesb create(
		java.math.BigDecimal stemnummer)
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * Creates an instance from a key for Entity Bean: Encryptedesb
	 */
	public ie.ucd.srg.koa.esb.beans.Encryptedesb create(
		java.math.BigDecimal stemnummer,
		byte[] encryptedvote)
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * Finds an instance using a key for Entity Bean: Encryptedesb
	 */
	public ie.ucd.srg.koa.esb.beans.Encryptedesb findByPrimaryKey(
		ie.ucd.srg.koa.esb.beans.EncryptedesbKey primaryKey)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
}
