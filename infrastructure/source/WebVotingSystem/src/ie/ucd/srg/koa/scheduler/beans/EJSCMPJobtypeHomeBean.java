package ie.ucd.srg.koa.scheduler.beans;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPJobtypeHomeBean
 * @generated
 */
public class EJSCMPJobtypeHomeBean extends EJSHome {
	/**
	 * EJSCMPJobtypeHomeBean
	 * @generated
	 */
	public EJSCMPJobtypeHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.scheduler.beans.Jobtype create(java.math.BigDecimal typeid) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.scheduler.beans.Jobtype _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.scheduler.beans.JobtypeBean bean = (ie.ucd.srg.koa.scheduler.beans.JobtypeBean) beanO.getEnterpriseBean();
			bean.ejbCreate(typeid);
			_EJS_result = (ie.ucd.srg.koa.scheduler.beans.Jobtype) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(typeid);
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
	public ie.ucd.srg.koa.scheduler.beans.Jobtype findByPrimaryKey(ie.ucd.srg.koa.scheduler.beans.JobtypeKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.scheduler.beans.EJSJDBCPersisterCMPJobtypeBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * keyFromBean
	 * @generated
	 */
	public Object keyFromBean(javax.ejb.EntityBean generalEJB) {
		ie.ucd.srg.koa.scheduler.beans.JobtypeBean tmpEJB = (ie.ucd.srg.koa.scheduler.beans.JobtypeBean) generalEJB;
		ie.ucd.srg.koa.scheduler.beans.JobtypeKey keyClass = new ie.ucd.srg.koa.scheduler.beans.JobtypeKey();
		keyClass.typeid = tmpEJB.typeid;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.scheduler.beans.JobtypeKey keyFromFields(java.math.BigDecimal f0) {
		ie.ucd.srg.koa.scheduler.beans.JobtypeKey keyClass = new ie.ucd.srg.koa.scheduler.beans.JobtypeKey();
		keyClass.typeid = f0;
		return keyClass;
	}
}
