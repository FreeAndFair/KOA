package ie.ucd.srg.koa.esb.beans;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPEncryptedesbHomeBean
 * @generated
 */
public class EJSCMPEncryptedesbHomeBean extends EJSHome {
	/**
	 * EJSCMPEncryptedesbHomeBean
	 * @generated
	 */
	public EJSCMPEncryptedesbHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Encryptedesb create(java.math.BigDecimal stemnummer) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.esb.beans.Encryptedesb _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.esb.beans.EncryptedesbBean bean = (ie.ucd.srg.koa.esb.beans.EncryptedesbBean) beanO.getEnterpriseBean();
			bean.ejbCreate(stemnummer);
			_EJS_result = (ie.ucd.srg.koa.esb.beans.Encryptedesb) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(stemnummer);
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
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Encryptedesb create(java.math.BigDecimal stemnummer, byte[] encryptedvote) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.esb.beans.Encryptedesb _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.esb.beans.EncryptedesbBean bean = (ie.ucd.srg.koa.esb.beans.EncryptedesbBean) beanO.getEnterpriseBean();
			bean.ejbCreate(stemnummer, encryptedvote);
			_EJS_result = (ie.ucd.srg.koa.esb.beans.Encryptedesb) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(stemnummer, encryptedvote);
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
	public ie.ucd.srg.koa.esb.beans.Encryptedesb findByPrimaryKey(ie.ucd.srg.koa.esb.beans.EncryptedesbKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.esb.beans.EJSJDBCPersisterCMPEncryptedesbBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * keyFromBean
	 * @generated
	 */
	public Object keyFromBean(javax.ejb.EntityBean generalEJB) {
		ie.ucd.srg.koa.esb.beans.EncryptedesbBean tmpEJB = (ie.ucd.srg.koa.esb.beans.EncryptedesbBean) generalEJB;
		ie.ucd.srg.koa.esb.beans.EncryptedesbKey keyClass = new ie.ucd.srg.koa.esb.beans.EncryptedesbKey();
		keyClass.stemnummer = tmpEJB.stemnummer;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.EncryptedesbKey keyFromFields(java.math.BigDecimal f0) {
		ie.ucd.srg.koa.esb.beans.EncryptedesbKey keyClass = new ie.ucd.srg.koa.esb.beans.EncryptedesbKey();
		keyClass.stemnummer = f0;
		return keyClass;
	}
}
