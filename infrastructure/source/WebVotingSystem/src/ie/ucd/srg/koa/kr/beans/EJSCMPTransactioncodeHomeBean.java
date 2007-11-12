package ie.ucd.srg.koa.kr.beans;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPTransactioncodeHomeBean
 * @generated
 */
public class EJSCMPTransactioncodeHomeBean extends EJSHome {
	/**
	 * EJSCMPTransactioncodeHomeBean
	 * @generated
	 */
	public EJSCMPTransactioncodeHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.Transactioncode findByPrimaryKey(ie.ucd.srg.koa.kr.beans.TransactioncodeKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.kr.beans.EJSJDBCPersisterCMPTransactioncodeBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.Transactioncode create(java.lang.String transactienummer) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.kr.beans.Transactioncode _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.kr.beans.TransactioncodeBean bean = (ie.ucd.srg.koa.kr.beans.TransactioncodeBean) beanO.getEnterpriseBean();
			bean.ejbCreate(transactienummer);
			_EJS_result = (ie.ucd.srg.koa.kr.beans.Transactioncode) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(transactienummer);
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
		ie.ucd.srg.koa.kr.beans.TransactioncodeBean tmpEJB = (ie.ucd.srg.koa.kr.beans.TransactioncodeBean) generalEJB;
		ie.ucd.srg.koa.kr.beans.TransactioncodeKey keyClass = new ie.ucd.srg.koa.kr.beans.TransactioncodeKey();
		keyClass.transactienummer = tmpEJB.transactienummer;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.kr.beans.TransactioncodeKey keyFromFields(java.lang.String f0) {
		ie.ucd.srg.koa.kr.beans.TransactioncodeKey keyClass = new ie.ucd.srg.koa.kr.beans.TransactioncodeKey();
		keyClass.transactienummer = f0;
		return keyClass;
	}
}
