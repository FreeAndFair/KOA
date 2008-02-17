package ie.ucd.srg.koa.koaschema;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPKieslijstenHomeBean
 * @generated
 */
public class EJSCMPKieslijstenHomeBean extends EJSHome {
	/**
	 * EJSCMPKieslijstenHomeBean
	 * @generated
	 */
	public EJSCMPKieslijstenHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findKieslijstenByFk_kkr_1
	 * @generated
	 */
	public java.util.Enumeration findKieslijstenByFk_kkr_1(ie.ucd.srg.koa.koaschema.KieskringenKey inKey) throws java.rmi.RemoteException, javax.ejb.FinderException {
return super.getEnumeration(((ie.ucd.srg.koa.koaschema.EJSFinderKieslijstenBean)persister).findKieslijstenByFk_kkr_1(inKey));	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.Kieslijsten create(java.lang.String kieslijstnummer, ie.ucd.srg.koa.koaschema.Kieskringen argFk_kkr_1) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.koaschema.Kieslijsten _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.koaschema.KieslijstenBean bean = (ie.ucd.srg.koa.koaschema.KieslijstenBean) beanO.getEnterpriseBean();
			bean.ejbCreate(kieslijstnummer, argFk_kkr_1);
			_EJS_result = (ie.ucd.srg.koa.koaschema.Kieslijsten) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(kieslijstnummer, argFk_kkr_1);
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
	public ie.ucd.srg.koa.koaschema.Kieslijsten create(java.lang.String kieslijstnummer, ie.ucd.srg.koa.koaschema.Kieskringen argFk_kkr_1, java.lang.String lijstnaam) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.koaschema.Kieslijsten _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.koaschema.KieslijstenBean bean = (ie.ucd.srg.koa.koaschema.KieslijstenBean) beanO.getEnterpriseBean();
			bean.ejbCreate(kieslijstnummer, argFk_kkr_1, lijstnaam);
			_EJS_result = (ie.ucd.srg.koa.koaschema.Kieslijsten) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(kieslijstnummer, argFk_kkr_1, lijstnaam);
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
	public ie.ucd.srg.koa.koaschema.Kieslijsten findByPrimaryKey(ie.ucd.srg.koa.koaschema.KieslijstenKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.koaschema.EJSJDBCPersisterCMPKieslijstenBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * keyFromBean
	 * @generated
	 */
	public Object keyFromBean(javax.ejb.EntityBean generalEJB) {
		ie.ucd.srg.koa.koaschema.KieslijstenBean tmpEJB = (ie.ucd.srg.koa.koaschema.KieslijstenBean) generalEJB;
		ie.ucd.srg.koa.koaschema.KieslijstenKey keyClass = new ie.ucd.srg.koa.koaschema.KieslijstenKey();
		keyClass.kieslijstnummer = tmpEJB.kieslijstnummer;
		keyClass.fk_kkr_1_kieskringnummer = tmpEJB.fk_kkr_1_kieskringnummer;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.KieslijstenKey keyFromFields(java.lang.String f0, java.lang.String f1) {
		ie.ucd.srg.koa.koaschema.KieslijstenKey keyClass = new ie.ucd.srg.koa.koaschema.KieslijstenKey();
		keyClass.kieslijstnummer = f0;
		keyClass.fk_kkr_1_kieskringnummer = f1;
		return keyClass;
	}
}
