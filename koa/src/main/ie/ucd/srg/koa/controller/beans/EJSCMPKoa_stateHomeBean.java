package ie.ucd.srg.koa.controller.beans;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPKoa_stateHomeBean
 * @generated
 */
public class EJSCMPKoa_stateHomeBean extends EJSHome {
	/**
	 * EJSCMPKoa_stateHomeBean
	 * @generated
	 */
	//@ signals (Exception) false;
	public EJSCMPKoa_stateHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 * @generated
	 */
	//@ requires id != null;
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public ie.ucd.srg.koa.controller.beans.Koa_state create(java.lang.Integer id) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.controller.beans.Koa_state _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.controller.beans.Koa_stateBean bean = (ie.ucd.srg.koa.controller.beans.Koa_stateBean) beanO.getEnterpriseBean();
			bean.ejbCreate(id);
			_EJS_result = (ie.ucd.srg.koa.controller.beans.Koa_state) super.postCreate(beanO, keyFromBean(bean));
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
	 * findByPrimaryKey
	 * @generated
	 */
	//@ requires primaryKey != null;
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public ie.ucd.srg.koa.controller.beans.Koa_state findByPrimaryKey(ie.ucd.srg.koa.controller.beans.Koa_stateKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.controller.beans.EJSJDBCPersisterCMPKoa_stateBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * keyFromBean
	 * @generated
	 */
	//@ requires generalEJB != null;
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public Object keyFromBean(javax.ejb.EntityBean generalEJB) {
		ie.ucd.srg.koa.controller.beans.Koa_stateBean tmpEJB = (ie.ucd.srg.koa.controller.beans.Koa_stateBean) generalEJB;
		ie.ucd.srg.koa.controller.beans.Koa_stateKey keyClass = new ie.ucd.srg.koa.controller.beans.Koa_stateKey();
		keyClass.id = tmpEJB.id;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	//@ requires f0 != null;
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public ie.ucd.srg.koa.controller.beans.Koa_stateKey keyFromFields(java.lang.Integer f0) {
		ie.ucd.srg.koa.controller.beans.Koa_stateKey keyClass = new ie.ucd.srg.koa.controller.beans.Koa_stateKey();
		keyClass.id = f0;
		return keyClass;
	}
}
