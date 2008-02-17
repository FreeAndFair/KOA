package ie.ucd.srg.koa.esb.beans;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPESBSequencesEJBHomeBean
 * @generated
 */
public class EJSCMPESBSequencesEJBHomeBean extends EJSHome {
	/**
	 * EJSCMPESBSequencesEJBHomeBean
	 * @generated
	 */
	public EJSCMPESBSequencesEJBHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.ESBSequencesEJB findByPrimaryKey(ie.ucd.srg.koa.esb.beans.ESBSequencesEJBKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.esb.beans.EJSJDBCPersisterCMPESBSequencesEJBBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.ESBSequencesEJB create() throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.esb.beans.ESBSequencesEJB _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.esb.beans.ESBSequencesEJBBean bean = (ie.ucd.srg.koa.esb.beans.ESBSequencesEJBBean) beanO.getEnterpriseBean();
			bean.ejbCreate();
			_EJS_result = (ie.ucd.srg.koa.esb.beans.ESBSequencesEJB) super.postCreate(beanO, keyFromBean(bean));
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
		ie.ucd.srg.koa.esb.beans.ESBSequencesEJBBean tmpEJB = (ie.ucd.srg.koa.esb.beans.ESBSequencesEJBBean) generalEJB;
		ie.ucd.srg.koa.esb.beans.ESBSequencesEJBKey keyClass = new ie.ucd.srg.koa.esb.beans.ESBSequencesEJBKey();
		keyClass.tablename = tmpEJB.tablename;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.ESBSequencesEJBKey keyFromFields(java.lang.String f0) {
		ie.ucd.srg.koa.esb.beans.ESBSequencesEJBKey keyClass = new ie.ucd.srg.koa.esb.beans.ESBSequencesEJBKey();
		keyClass.tablename = f0;
		return keyClass;
	}
}
