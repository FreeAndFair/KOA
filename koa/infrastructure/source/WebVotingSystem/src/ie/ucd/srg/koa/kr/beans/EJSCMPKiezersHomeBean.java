package ie.ucd.srg.koa.kr.beans;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPKiezersHomeBean
 * @generated
 */
public class EJSCMPKiezersHomeBean extends EJSHome {
	/**
	 * EJSCMPKiezersHomeBean
	 * @generated
	 */
	public EJSCMPKiezersHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.Kiezers findByPrimaryKey(ie.ucd.srg.koa.kr.beans.KiezersKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.kr.beans.EJSJDBCPersisterCMPKiezersBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.Kiezers create(java.lang.String sStemcode, java.lang.String sKiezersidentificatie, java.lang.String sHashedWachtwoord, java.lang.String sDistrictnummer, java.lang.String sKieskringnummer, java.lang.String sTransactienummer) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.kr.beans.Kiezers _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.kr.beans.KiezersBean bean = (ie.ucd.srg.koa.kr.beans.KiezersBean) beanO.getEnterpriseBean();
			bean.ejbCreate(sStemcode, sKiezersidentificatie, sHashedWachtwoord, sDistrictnummer, sKieskringnummer, sTransactienummer);
			_EJS_result = (ie.ucd.srg.koa.kr.beans.Kiezers) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(sStemcode, sKiezersidentificatie, sHashedWachtwoord, sDistrictnummer, sKieskringnummer, sTransactienummer);
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
	 * findByKiezerId
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.Kiezers findByKiezerId(java.lang.String sKiezerId) throws javax.ejb.FinderException, java.rmi.RemoteException {
return ((ie.ucd.srg.koa.kr.beans.EJSJDBCPersisterCMPKiezersBean)persister).findByKiezerId(sKiezerId);	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.Kiezers create() throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.kr.beans.Kiezers _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.kr.beans.KiezersBean bean = (ie.ucd.srg.koa.kr.beans.KiezersBean) beanO.getEnterpriseBean();
			bean.ejbCreate();
			_EJS_result = (ie.ucd.srg.koa.kr.beans.Kiezers) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate();
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
		ie.ucd.srg.koa.kr.beans.KiezersBean tmpEJB = (ie.ucd.srg.koa.kr.beans.KiezersBean) generalEJB;
		ie.ucd.srg.koa.kr.beans.KiezersKey keyClass = new ie.ucd.srg.koa.kr.beans.KiezersKey();
		keyClass.stemcode = tmpEJB.stemcode;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.KiezersKey keyFromFields(java.lang.String f0) {
		ie.ucd.srg.koa.kr.beans.KiezersKey keyClass = new ie.ucd.srg.koa.kr.beans.KiezersKey();
		keyClass.stemcode = f0;
		return keyClass;
	}
}
