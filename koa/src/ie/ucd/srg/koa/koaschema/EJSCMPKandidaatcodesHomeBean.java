package ie.ucd.srg.koa.koaschema;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPKandidaatcodesHomeBean
 * @generated
 */
public class EJSCMPKandidaatcodesHomeBean extends EJSHome {
	/**
	 * EJSCMPKandidaatcodesHomeBean
	 * @generated
	 */
	public EJSCMPKandidaatcodesHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.Kandidaatcodes findByPrimaryKey(ie.ucd.srg.koa.koaschema.KandidaatcodesKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.koaschema.EJSJDBCPersisterCMPKandidaatcodesBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.Kandidaatcodes create(java.lang.String kandidaatcode, java.lang.String positienummer, java.lang.String kieslijstnummer, java.lang.String kieskringnummer) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.koaschema.Kandidaatcodes _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.koaschema.KandidaatcodesBean bean = (ie.ucd.srg.koa.koaschema.KandidaatcodesBean) beanO.getEnterpriseBean();
			bean.ejbCreate(kandidaatcode, positienummer, kieslijstnummer, kieskringnummer);
			_EJS_result = (ie.ucd.srg.koa.koaschema.Kandidaatcodes) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(kandidaatcode, positienummer, kieslijstnummer, kieskringnummer);
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
	public ie.ucd.srg.koa.koaschema.Kandidaatcodes create(java.lang.String kandidaatcode) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.koaschema.Kandidaatcodes _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.koaschema.KandidaatcodesBean bean = (ie.ucd.srg.koa.koaschema.KandidaatcodesBean) beanO.getEnterpriseBean();
			bean.ejbCreate(kandidaatcode);
			_EJS_result = (ie.ucd.srg.koa.koaschema.Kandidaatcodes) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(kandidaatcode);
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
	 * findKandidaatcodesByFk_kkrklpn_1
	 * @generated
	 */
	public java.util.Enumeration findKandidaatcodesByFk_kkrklpn_1(ie.ucd.srg.koa.koaschema.LijstpositiesKey inKey) throws java.rmi.RemoteException, javax.ejb.FinderException {
return super.getEnumeration(((ie.ucd.srg.koa.koaschema.EJSFinderKandidaatcodesBean)persister).findKandidaatcodesByFk_kkrklpn_1(inKey));	}
	/**
	 * keyFromBean
	 * @generated
	 */
	public Object keyFromBean(javax.ejb.EntityBean generalEJB) {
		ie.ucd.srg.koa.koaschema.KandidaatcodesBean tmpEJB = (ie.ucd.srg.koa.koaschema.KandidaatcodesBean) generalEJB;
		ie.ucd.srg.koa.koaschema.KandidaatcodesKey keyClass = new ie.ucd.srg.koa.koaschema.KandidaatcodesKey();
		keyClass.kandidaatcode = tmpEJB.kandidaatcode;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.KandidaatcodesKey keyFromFields(java.lang.String f0) {
		ie.ucd.srg.koa.koaschema.KandidaatcodesKey keyClass = new ie.ucd.srg.koa.koaschema.KandidaatcodesKey();
		keyClass.kandidaatcode = f0;
		return keyClass;
	}
}
