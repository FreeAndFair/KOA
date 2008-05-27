package ie.ucd.srg.koa.koaschema;
/**
 * Remote interface for Enterprise Bean: Kieslijsten
 */
public interface Kieslijsten extends javax.ejb.EJBObject
{
	/**
	 * Get accessor for persistent attribute: lijstnaam
	 */
	public java.lang.String getLijstnaam() throws java.rmi.RemoteException;
	/**
	 * Set accessor for persistent attribute: lijstnaam
	 */
	public void setLijstnaam(java.lang.String newLijstnaam)
		throws java.rmi.RemoteException;
	/**
	 * This method was generated for supporting the association named fk_kkr_1.
	 * It will be deleted/edited when the association is deleted/edited.
	 */
	public ie.ucd.srg.koa.koaschema.KieskringenKey getFk_kkr_1Key()
		throws java.rmi.RemoteException;
	/**
	 * This method was generated for supporting the association named fk_kkr_1.
	 * It will be deleted/edited when the association is deleted/edited.
	 */
	public ie.ucd.srg.koa.koaschema.Kieskringen getFk_kkr_1()
		throws java.rmi.RemoteException, javax.ejb.FinderException;
	/**
	 * This method was generated for supporting the association named lijstposities.
	 * It will be deleted/edited when the association is deleted/edited.
	 */
	public void secondaryAddLijstposities(
		ie.ucd.srg.koa.koaschema.Lijstposities aLijstposities)
		throws java.rmi.RemoteException;
	/**
	 * This method was generated for supporting the association named lijstposities.
	 * It will be deleted/edited when the association is deleted/edited.
	 */
	public void secondaryRemoveLijstposities(
		ie.ucd.srg.koa.koaschema.Lijstposities aLijstposities)
		throws java.rmi.RemoteException;
	/**
	 * This method was generated for supporting the association named lijstposities.
	 * It will be deleted/edited when the association is deleted/edited.
	 */
	public java.util.Enumeration getLijstposities()
		throws java.rmi.RemoteException, javax.ejb.FinderException;
}
