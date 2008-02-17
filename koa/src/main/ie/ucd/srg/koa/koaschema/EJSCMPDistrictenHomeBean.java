package ie.ucd.srg.koa.koaschema;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPDistrictenHomeBean
 * @generated
 */
public class EJSCMPDistrictenHomeBean extends EJSHome {
	/**
	 * EJSCMPDistrictenHomeBean
	 * @generated
	 */
	public EJSCMPDistrictenHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findDistrictenByFk_dist_kkr
	 * @generated
	 */
	public java.util.Enumeration findDistrictenByFk_dist_kkr(ie.ucd.srg.koa.koaschema.KieskringenKey inKey) throws java.rmi.RemoteException, javax.ejb.FinderException {
return super.getEnumeration(((ie.ucd.srg.koa.koaschema.EJSFinderDistrictenBean)persister).findDistrictenByFk_dist_kkr(inKey));	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.Districten findByPrimaryKey(ie.ucd.srg.koa.koaschema.DistrictenKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.koaschema.EJSJDBCPersisterCMPDistrictenBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.Districten create(java.lang.String districtnummer) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.koaschema.Districten _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.koaschema.DistrictenBean bean = (ie.ucd.srg.koa.koaschema.DistrictenBean) beanO.getEnterpriseBean();
			bean.ejbCreate(districtnummer);
			_EJS_result = (ie.ucd.srg.koa.koaschema.Districten) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(districtnummer);
		}
		catch (javax.ejb.CreateException ex) {
			createFailed = true;
			throw ex;
		} catch (java.rmi.RemoteException ex) {
			createFailed = true;
			throw ex;
		} catch (Throwable ex) {
			createFailed = true;
			throw new CreateFailureException(ex);
		} finally {
			if (createFailed) {
				super.createFailure(beanO);
			}
		}
		return _EJS_result;
	}
	/**
	 * keyFromBean
	 * @generated
	 */
	public Object keyFromBean(javax.ejb.EntityBean generalEJB) {
		ie.ucd.srg.koa.koaschema.DistrictenBean tmpEJB = (ie.ucd.srg.koa.koaschema.DistrictenBean) generalEJB;
		ie.ucd.srg.koa.koaschema.DistrictenKey keyClass = new ie.ucd.srg.koa.koaschema.DistrictenKey();
		keyClass.districtnummer = tmpEJB.districtnummer;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.DistrictenKey keyFromFields(java.lang.String f0) {
		ie.ucd.srg.koa.koaschema.DistrictenKey keyClass = new ie.ucd.srg.koa.koaschema.DistrictenKey();
		keyClass.districtnummer = f0;
		return keyClass;
	}
}
