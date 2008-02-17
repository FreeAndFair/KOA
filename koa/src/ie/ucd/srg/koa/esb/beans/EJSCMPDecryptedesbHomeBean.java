package ie.ucd.srg.koa.esb.beans;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSCMPDecryptedesbHomeBean
 * @generated
 */
public class EJSCMPDecryptedesbHomeBean extends EJSHome {
	/**
	 * EJSCMPDecryptedesbHomeBean
	 * @generated
	 */
	public EJSCMPDecryptedesbHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Decryptedesb findByPrimaryKey(ie.ucd.srg.koa.esb.beans.DecryptedesbKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		return ((ie.ucd.srg.koa.esb.beans.EJSJDBCPersisterCMPDecryptedesbBean) persister).findByPrimaryKey(primaryKey);
	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Decryptedesb create(java.math.BigDecimal stemnummer, java.lang.String sKandidaatCode, java.lang.String sKieskringnummer, java.lang.String sDistrictnummer, java.lang.String sKieslijstnummer, java.lang.String sPositienummer, java.lang.String sLijstnaam, java.lang.String sAchternaam, java.lang.String sVoorletters) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.esb.beans.Decryptedesb _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.esb.beans.DecryptedesbBean bean = (ie.ucd.srg.koa.esb.beans.DecryptedesbBean) beanO.getEnterpriseBean();
			bean.ejbCreate(stemnummer, sKandidaatCode, sKieskringnummer, sDistrictnummer, sKieslijstnummer, sPositienummer, sLijstnaam, sAchternaam, sVoorletters);
			_EJS_result = (ie.ucd.srg.koa.esb.beans.Decryptedesb) super.postCreate(beanO, keyFromBean(bean));
			bean.ejbPostCreate(stemnummer, sKandidaatCode, sKieskringnummer, sDistrictnummer, sKieslijstnummer, sPositienummer, sLijstnaam, sAchternaam, sVoorletters);
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
	public ie.ucd.srg.koa.esb.beans.Decryptedesb create(java.math.BigDecimal stemnummer) throws javax.ejb.CreateException, java.rmi.RemoteException {
		BeanO beanO = null;
		ie.ucd.srg.koa.esb.beans.Decryptedesb _EJS_result = null;
		boolean createFailed = false;
		try {
			beanO = super.createBeanO();
			ie.ucd.srg.koa.esb.beans.DecryptedesbBean bean = (ie.ucd.srg.koa.esb.beans.DecryptedesbBean) beanO.getEnterpriseBean();
			bean.ejbCreate(stemnummer);
			_EJS_result = (ie.ucd.srg.koa.esb.beans.Decryptedesb) super.postCreate(beanO, keyFromBean(bean));
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
	 * findByLijstpositie
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Decryptedesb findByLijstpositie(java.lang.String sKieskringnummer, java.lang.String sKieslijstnummer, java.lang.String sPositienummer) throws javax.ejb.FinderException, java.rmi.RemoteException {
return ((ie.ucd.srg.koa.esb.beans.EJSJDBCPersisterCMPDecryptedesbBean)persister).findByLijstpositie(sKieskringnummer, sKieslijstnummer, sPositienummer);	}
	/**
	 * keyFromBean
	 * @generated
	 */
	public Object keyFromBean(javax.ejb.EntityBean generalEJB) {
		ie.ucd.srg.koa.esb.beans.DecryptedesbBean tmpEJB = (ie.ucd.srg.koa.esb.beans.DecryptedesbBean) generalEJB;
		ie.ucd.srg.koa.esb.beans.DecryptedesbKey keyClass = new ie.ucd.srg.koa.esb.beans.DecryptedesbKey();
		keyClass.stemnummer = tmpEJB.stemnummer;
		return keyClass;
	}
	/**
	 * keyFromFields
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.DecryptedesbKey keyFromFields(java.math.BigDecimal f0) {
		ie.ucd.srg.koa.esb.beans.DecryptedesbKey keyClass = new ie.ucd.srg.koa.esb.beans.DecryptedesbKey();
		keyClass.stemnummer = f0;
		return keyClass;
	}
}
