package ie.ucd.srg.koa.koaschema;
/**
 * Home interface for Enterprise Bean: Kieslijsten
 */
public interface KieslijstenHome extends javax.ejb.EJBHome
{
	/**
	 * Creates an instance from a key for Entity Bean: Kieslijsten
	 */
	public ie.ucd.srg.koa.koaschema.Kieslijsten create(
		java.lang.String kieslijstnummer,
		ie.ucd.srg.koa.koaschema.Kieskringen argFk_kkr_1)
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * Creates an instance from a key for Entity Bean: Kieslijsten
	 */
	public ie.ucd.srg.koa.koaschema.Kieslijsten create(
		java.lang.String kieslijstnummer,
		ie.ucd.srg.koa.koaschema.Kieskringen argFk_kkr_1,
		String lijstnaam)
		throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * Finds an instance using a key for Entity Bean: Kieslijsten
	 */
	public ie.ucd.srg.koa.koaschema.Kieslijsten findByPrimaryKey(
		ie.ucd.srg.koa.koaschema.KieslijstenKey primaryKey)
		throws javax.ejb.FinderException, java.rmi.RemoteException;
	/**
	 * This method was generated for supporting the association named fk_kkr_1.
	 * It will be deleted/edited when the association is deleted/edited.
	 */
	public java.util.Enumeration findKieslijstenByFk_kkr_1(
		ie.ucd.srg.koa.koaschema.KieskringenKey inKey)
		throws java.rmi.RemoteException, javax.ejb.FinderException;
}
