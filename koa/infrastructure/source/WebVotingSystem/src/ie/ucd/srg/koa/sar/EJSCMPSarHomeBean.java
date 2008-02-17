package ie.ucd.srg.koa.sar;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPSarHomeBean
 * @generated
 */
public class EJSCMPSarHomeBean extends EJSHome {
	/**
	 * EJSCMPSarHomeBean
	 * @generated
	 */
	public EJSCMPSarHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.sar.Sar findByPrimaryKey(ie.ucd.srg.koa.sar.SarKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.sar.EJSJDBCPersisterCMPSarBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * findByStemcode
	 * @generated
	 */
	public ie.ucd.srg.koa.sar.Sar findByStemcode(java.lang.String sStemcode) throws javax.ejb.FinderException, java.rmi.RemoteException {
return ((ie.ucd.srg.koa.sar.EJSJDBCPersisterCMPSarBean)persister).findByStemcode(sStemcode);	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.sar.Sar create(java.lang.String kiezeridentificatie, java.lang.String stemcode) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.sar.Sar _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.sar.SarBean bean = (ie.ucd.srg.koa.sar.SarBean) beanO.getEnterpriseBean();
			bean.ejbCreate(kiezeridentificatie, stemcode);
			_EJS_result = (ie.ucd.srg.koa.sar.Sar) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(kiezeridentificatie, stemcode);
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
		ie.ucd.srg.koa.sar.SarBean tmpEJB = (ie.ucd.srg.koa.sar.SarBean) generalEJB;
		ie.ucd.srg.koa.sar.SarKey keyClass = new ie.ucd.srg.koa.sar.SarKey();
		keyClass.kiezeridentificatie = tmpEJB.kiezeridentificatie;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.sar.SarKey keyFromFields(java.lang.String f0) {
		ie.ucd.srg.koa.sar.SarKey keyClass = new ie.ucd.srg.koa.sar.SarKey();
		keyClass.kiezeridentificatie = f0;
		return keyClass;
	}
}
