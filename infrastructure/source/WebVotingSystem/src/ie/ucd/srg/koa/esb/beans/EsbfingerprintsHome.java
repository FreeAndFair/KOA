package ie.ucd.srg.koa.esb.beans;
/**
 * Home interface for Enterprise Bean: Esbfingerprints
 */
public interface EsbfingerprintsHome extends javax.ejb.EJBHome
{
	/**
	 * Creates an instance from a key for Entity Bean: Esbfingerprints
	 */
	public ie.ucd.srg.koa.esb.beans.Esbfingerprints create(
		java.math.BigDecimal id)
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * Creates an instance from a key for Entity Bean: Esbfingerprints
	 */
	public ie.ucd.srg.koa.esb.beans.Esbfingerprints create(
		java.math.BigDecimal id,
		byte[] xFingerprint,
		java.sql.Timestamp xTimestamp,
		java.lang.String sSystemState)
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * Finds an instance using a key for Entity Bean: Esbfingerprints
	 */
	public ie.ucd.srg.koa.esb.beans.Esbfingerprints findByPrimaryKey(
		ie.ucd.srg.koa.esb.beans.EsbfingerprintsKey primaryKey)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
	/**
	 * searches for the fingerprint with the most recent timestamp
	 */
	public ie.ucd.srg.koa.esb.beans.Esbfingerprints findLatestFingerprint()
		throws javax.ejb.FinderException, java.rmi.RemoteException;
}
