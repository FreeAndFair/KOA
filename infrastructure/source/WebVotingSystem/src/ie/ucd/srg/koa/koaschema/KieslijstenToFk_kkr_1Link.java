package ie.ucd.srg.koa.koaschema;
import javax.naming.NamingException;
import javax.ejb.*;
/**
 * KieslijstenToFk_kkr_1Link
 * @generated
 */
public class KieslijstenToFk_kkr_1Link
	extends ie.ucd.srg.ivj.ejb.associations.links.ManyToSingleLink
{
	/**
	 * @generated
	 */
	private static ie.ucd.srg.koa.koaschema.KieskringenHome targetHome;
	/**
	 * @generated
	 */
	private static final java.lang.String targetHomeName = "Kieskringen";
	/**
	 * Create a link instance with the passed source bean.
	 * @generated
	 */
	public KieslijstenToFk_kkr_1Link(javax.ejb.EntityBean anEntityBean)
	{
		super(anEntityBean);
	}
	/**
	 * Get the target home for the link.
	 * @generated
	 */
	protected synchronized ie.ucd.srg.koa.koaschema.KieskringenHome getTargetHome(
			ie.ucd.srg.ivj.ejb.associations.links.Link aLink)
		throws javax.naming.NamingException
	{
		if (targetHome == null)
			targetHome =
				(ie.ucd.srg.koa.koaschema.KieskringenHome) lookupTargetHome("java:comp/env/ejb/Kieskringen",
					ie.ucd.srg.koa.koaschema.KieskringenHome.class);
		return targetHome;
	}
	/**
	 * Fetch the target for this single link, return an instance of the target class.
	 * @generated
	 */
	protected javax.ejb.EJBObject fetchTarget()
		throws javax.ejb.FinderException, java.rmi.RemoteException
	{
		EJBObject target = null;
		ie.ucd.srg.koa.koaschema.KieskringenKey key =
			((ie.ucd.srg.koa.koaschema.KieslijstenBean) source)
				.getFk_kkr_1Key();
		try
		{
			target =
				(
					(ie.ucd.srg.koa.koaschema.KieskringenHome) getTargetHome(
						this)).findByPrimaryKey(
					key);
		}
		catch (NamingException e)
		{
			throw new FinderException(e.toString());
		}
		return target;
	}
	/**
	 * Get the entity context from the source bean.
	 * @generated
	 */
	public javax.ejb.EntityContext getEntityContext()
	{
		return ((ie.ucd.srg.koa.koaschema.KieslijstenBean) source)
			.getEntityContext();
	}
	/**
	 * Return whether or not the source key is valid for querying.
	 * @generated
	 */
	protected boolean isKeyValid()
	{
		return (
			((ie.ucd.srg.koa.koaschema.KieslijstenBean) source)
				.getFk_kkr_1Key()
				!= null);
	}
	/**
	 * Forward inverse maintenance through my target EJB.
	 * @generated
	 */
	public void secondaryRemoveElementCounterLinkOf(javax.ejb.EJBObject anEJB)
		throws java.rmi.RemoteException
	{
		if (anEJB != null)
			(
				(
					ie.ucd.srg.koa.koaschema.Kieskringen) anEJB)
						.secondaryRemoveKieslijsten(
				(ie.ucd.srg.koa.koaschema.Kieslijsten) getEntityContext()
					.getEJBObject());
	}
	/**
	 * Forward inverse maintenance through my target EJB.
	 * @generated
	 */
	public void secondaryAddElementCounterLinkOf(javax.ejb.EJBObject anEJB)
		throws java.rmi.RemoteException
	{
		if (anEJB != null)
			(
				(
						ie.ucd.srg.koa.koaschema.Kieskringen) anEJB)
						.secondaryAddKieslijsten(
				(ie.ucd.srg.koa.koaschema.Kieslijsten) getEntityContext()
					.getEJBObject());
	}
	/**
	 * Set the target for this single link, an instance of the target class.
	 * @generated
	 */
	public void set(javax.ejb.EJBObject targetEJB)
		throws java.rmi.RemoteException
	{
		super.set(targetEJB);
		if (targetEJB == null)
			(
				(
						ie.ucd.srg.koa.koaschema.KieslijstenBean) source)
						.privateSetFk_kkr_1Key(
				null);
		else
			(
				(
						ie.ucd.srg.koa.koaschema.KieslijstenBean) source)
						.privateSetFk_kkr_1Key(
				(ie.ucd.srg.koa.koaschema.KieskringenKey) targetEJB
					.getPrimaryKey());
	}
	/**
	 * Reset my target key.
	 * @generated
	 */
	protected void resetKey() throws java.rmi.RemoteException
	{
		(
			(
					ie.ucd.srg.koa.koaschema.KieslijstenBean) source)
					.privateSetFk_kkr_1Key(
			null);
	}
}
