package ie.ucd.srg.koa.esb.beans;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPEsbfingerprintsHomeBean
 * @generated
 */
public class EJSCMPEsbfingerprintsHomeBean extends EJSHome {
	/**
	 * EJSCMPEsbfingerprintsHomeBean
	 * @generated
	 */
	public EJSCMPEsbfingerprintsHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Esbfingerprints findByPrimaryKey(ie.ucd.srg.koa.esb.beans.EsbfingerprintsKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.esb.beans.EJSJDBCPersisterCMPEsbfingerprintsBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Esbfingerprints create(java.math.BigDecimal id) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.esb.beans.Esbfingerprints _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.esb.beans.EsbfingerprintsBean bean = (ie.ucd.srg.koa.esb.beans.EsbfingerprintsBean) beanO.getEnterpriseBean();
			bean.ejbCreate(id);
			_EJS_result = (ie.ucd.srg.koa.esb.beans.Esbfingerprints) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(id);
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
	 * findLatestFingerprint
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Esbfingerprints findLatestFingerprint() throws javax.ejb.FinderException, java.rmi.RemoteException {
return ((ie.ucd.srg.koa.esb.beans.EJSJDBCPersisterCMPEsbfingerprintsBean)persister).findLatestFingerprint();	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Esbfingerprints create(java.math.BigDecimal id, byte[] xFingerprint, java.sql.Timestamp xTimestamp, java.lang.String sSystemState) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.esb.beans.Esbfingerprints _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.esb.beans.EsbfingerprintsBean bean = (ie.ucd.srg.koa.esb.beans.EsbfingerprintsBean) beanO.getEnterpriseBean();
			bean.ejbCreate(id, xFingerprint, xTimestamp, sSystemState);
			_EJS_result = (ie.ucd.srg.koa.esb.beans.Esbfingerprints) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(id, xFingerprint, xTimestamp, sSystemState);
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
		ie.ucd.srg.koa.esb.beans.EsbfingerprintsBean tmpEJB = (ie.ucd.srg.koa.esb.beans.EsbfingerprintsBean) generalEJB;
		ie.ucd.srg.koa.esb.beans.EsbfingerprintsKey keyClass = new ie.ucd.srg.koa.esb.beans.EsbfingerprintsKey();
		keyClass.id = tmpEJB.id;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.EsbfingerprintsKey keyFromFields(java.math.BigDecimal f0) {
		ie.ucd.srg.koa.esb.beans.EsbfingerprintsKey keyClass = new ie.ucd.srg.koa.esb.beans.EsbfingerprintsKey();
		keyClass.id = f0;
		return keyClass;
	}
}
