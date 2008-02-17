package ie.ucd.srg.koa.kr.beans;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPKRSequenceEJBHomeBean
 * @generated
 */
public class EJSCMPKRSequenceEJBHomeBean extends EJSHome {
	/**
	 * EJSCMPKRSequenceEJBHomeBean
	 * @generated
	 */
	public EJSCMPKRSequenceEJBHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.KRSequenceEJB findByPrimaryKey(ie.ucd.srg.koa.kr.beans.KRSequenceEJBKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.kr.beans.EJSJDBCPersisterCMPKRSequenceEJBBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.KRSequenceEJB create() throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.kr.beans.KRSequenceEJB _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.kr.beans.KRSequenceEJBBean bean = (ie.ucd.srg.koa.kr.beans.KRSequenceEJBBean) beanO.getEnterpriseBean();
			bean.ejbCreate();
			_EJS_result = (ie.ucd.srg.koa.kr.beans.KRSequenceEJB) super.postCreate(beanO, keyFromBean(bean));
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
		ie.ucd.srg.koa.kr.beans.KRSequenceEJBBean tmpEJB = (ie.ucd.srg.koa.kr.beans.KRSequenceEJBBean) generalEJB;
		ie.ucd.srg.koa.kr.beans.KRSequenceEJBKey keyClass = new ie.ucd.srg.koa.kr.beans.KRSequenceEJBKey();
		keyClass.tablename = tmpEJB.tablename;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.KRSequenceEJBKey keyFromFields(java.lang.String f0) {
		ie.ucd.srg.koa.kr.beans.KRSequenceEJBKey keyClass = new ie.ucd.srg.koa.kr.beans.KRSequenceEJBKey();
		keyClass.tablename = f0;
		return keyClass;
	}
}
