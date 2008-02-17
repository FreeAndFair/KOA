package ie.ucd.srg.koa.koaschema;
import java.util.Enumeration;
import javax.ejb.*;
import javax.naming.NamingException;
/**
 * KieskringenToDistrictenLink
 * @generated
 */
public class KieskringenToDistrictenLink
	extends ie.ucd.srg.ivj.ejb.associations.links.SingleToManyLink
{
	/**
	 * @generated
	 */
	private static ie.ucd.srg.koa.koaschema.DistrictenHome targetHome;
	/**
	 * @generated
	 */
	private static final java.lang.String targetHomeName = "Districten";
	/**
	 * Create a link instance with the passed source bean.
	 * @generated
	 */
	public KieskringenToDistrictenLink(javax.ejb.EntityBean anEntityBean)
	{
		super(anEntityBean);
	}
	/**
	 * Get the target home for the link.
	 * @generated
	 */
	protected synchronized ie.ucd.srg
		.koa
		.koaschema
		.DistrictenHome getTargetHome(
		ie.ucd.srg.ivj.ejb.associations.links.Link aLink)
		throws javax.naming.NamingException
	{
		if (targetHome == null)
			targetHome =
				(ie.ucd.srg
					.koa
					.koaschema
					.DistrictenHome) lookupTargetHome("java:comp/env/ejb/Districten",
					ie.ucd.srg.koa.koaschema.DistrictenHome.class);
		return targetHome;
	}
	/**
	 * Fetch the target for this many link, return an enumeration of the members.
	 * @generated
	 */
	protected java.util.Enumeration fetchTargetEnumeration()
		throws javax.ejb.FinderException, java.rmi.RemoteException
	{
		Enumeration enumer = null;
		try
		{
			enumer =
				(
					(ie.ucd.srg.koa.koaschema.DistrictenHome) getTargetHome(
						this)).findDistrictenByFk_dist_kkr(
					(ie.ucd.srg
						.koa
						.koaschema
						.KieskringenKey) getEntityContext()
						.getPrimaryKey());
		}
		catch (NamingException e)
		{
			throw new FinderException(e.toString());
		}
		return enumer;
	}
	/**
	 * Get the entity context from the source bean.
	 * @generated
	 */
	public javax.ejb.EntityContext getEntityContext()
	{
		return ((ie.ucd.srg.koa.koaschema.KieskringenBean) source)
			.getEntityContext();
	}
	/**
	 * Return whether or not the source key is valid for querying.
	 * @generated
	 */
	protected boolean isKeyValid()
	{
		return (getEntityContext().getPrimaryKey() != null);
	}
	/**
	 * Direct my counterLink to set my source as its target.
	 * @generated
	 */
	public void secondarySetCounterLinkOf(javax.ejb.EJBObject anEJB)
		throws java.rmi.RemoteException
	{
		if (anEJB != null)
			(
				(
						ie.ucd.srg
						.koa
						.koaschema
						.Districten) anEJB)
						.secondarySetFk_dist_kkr(
				(ie.ucd.srg.koa.koaschema.Kieskringen) getEntityContext()
					.getEJBObject());
	}
	/**
	 * Send my counterLink #secondaryDisconnect by routing through my target EJB.
	 * @generated
	 */
	public void setNullCounterLinkOf(javax.ejb.EJBObject anEJB)
		throws java.rmi.RemoteException
	{
		if (anEJB != null)
			((ie.ucd.srg.koa.koaschema.Districten) anEJB).setFk_dist_kkr(
				null);
	}
	/**
	 * Send my counterLink #secondaryDisconnect by routing through my target EJB.
	 * @generated
	 */
	public void secondarySetNullCounterLinkOf(javax.ejb.EJBObject anEJB)
		throws java.rmi.RemoteException
	{
		if (anEJB != null)
			(
				(
						ie.ucd.srg
						.koa
						.koaschema
						.Districten) anEJB)
						.secondarySetFk_dist_kkr(
				null);
	}
	/**
	 * Add an element to this many link.  Disallow null adds.
	 * @generated
	 */
	public void addElement(javax.ejb.EJBObject targetEJB)
		throws java.rmi.RemoteException
	{
		if (targetEJB != null)
		{
			super.addElement(targetEJB);
			(
				(
						ie.ucd.srg
						.koa
						.koaschema
						.Districten) targetEJB)
						.privateSetFk_dist_kkrKey(
				(ie.ucd.srg.koa.koaschema.KieskringenKey) getEntityContext()
					.getPrimaryKey());
		}
	}
	/**
	 * Narrow the remote object.
	 * @generated
	 */
	protected javax.ejb.EJBObject narrowElement(java.lang.Object element)
		throws java.rmi.RemoteException
	{
		return (EJBObject) javax.rmi.PortableRemoteObject.narrow(
			element,
			ie.ucd.srg.koa.koaschema.Districten.class);
	}
	/**
	 * Check if I contain anEJB by comparing primary keys.
	 * @generated
	 */
	protected boolean currentlyContains(javax.ejb.EJBObject anEJB)
		throws java.rmi.RemoteException
	{
		return super.currentlyContains(anEJB)
			|| getEntityContext().getPrimaryKey().equals(
				((ie.ucd.srg.koa.koaschema.Districten) anEJB)
					.getFk_dist_kkrKey());
	}
}
