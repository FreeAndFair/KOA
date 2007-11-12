package ie.ucd.srg.koa.esb.beans;
/**
 * Home interface for Enterprise Bean: ESBSequencesEJB
 */
public interface ESBSequencesEJBHome extends javax.ejb.EJBHome
{
	/**
	 * Creates an instance from a key for Entity Bean: ESBSequencesEJB
	 */
	public ie.ucd.srg.koa.esb.beans.ESBSequencesEJB create()
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * Finds an instance using a key for Entity Bean: ESBSequencesEJB
	 */
	public ie.ucd.srg.koa.esb.beans.ESBSequencesEJB findByPrimaryKey(
		ie.ucd.srg.koa.esb.beans.ESBSequencesEJBKey primaryKey)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
}
