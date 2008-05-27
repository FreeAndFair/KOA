package ie.ucd.srg.koa.koaschema;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPKieskringenHomeBean
 * @generated
 */
public class EJSCMPKieskringenHomeBean extends EJSHome {
	/**
	 * EJSCMPKieskringenHomeBean
	 * @generated
	 */
	public EJSCMPKieskringenHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.Kieskringen findByPrimaryKey(ie.ucd.srg.koa.koaschema.KieskringenKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.koaschema.EJSJDBCPersisterCMPKieskringenBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.Kieskringen create(java.lang.String kieskringnummer) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.koaschema.Kieskringen _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.koaschema.KieskringenBean bean = (ie.ucd.srg.koa.koaschema.KieskringenBean) beanO.getEnterpriseBean();
			bean.ejbCreate(kieskringnummer);
			_EJS_result = (ie.ucd.srg.koa.koaschema.Kieskringen) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(kieskringnummer);
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
		ie.ucd.srg.koa.koaschema.KieskringenBean tmpEJB = (ie.ucd.srg.koa.koaschema.KieskringenBean) generalEJB;
		ie.ucd.srg.koa.koaschema.KieskringenKey keyClass = new ie.ucd.srg.koa.koaschema.KieskringenKey();
		keyClass.kieskringnummer = tmpEJB.kieskringnummer;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.KieskringenKey keyFromFields(java.lang.String f0) {
		ie.ucd.srg.koa.koaschema.KieskringenKey keyClass = new ie.ucd.srg.koa.koaschema.KieskringenKey();
		keyClass.kieskringnummer = f0;
		return keyClass;
	}
}
