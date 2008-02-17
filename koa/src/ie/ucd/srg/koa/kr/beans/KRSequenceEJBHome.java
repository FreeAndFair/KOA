package ie.ucd.srg.koa.kr.beans;
/**
 * Home interface for Enterprise Bean: KRSequenceEJB
 */
public interface KRSequenceEJBHome extends javax.ejb.EJBHome
{
	/**
	 * Creates an instance from a key for Entity Bean: KRSequenceEJB
	 */
	public ie.ucd.srg.koa.kr.beans.KRSequenceEJB create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * Finds an instance using a key for Entity Bean: KRSequenceEJB
	 */
	public ie.ucd.srg.koa.kr.beans.KRSequenceEJB findByPrimaryKey(
		ie.ucd.srg.koa.kr.beans.KRSequenceEJBKey primaryKey)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
}
