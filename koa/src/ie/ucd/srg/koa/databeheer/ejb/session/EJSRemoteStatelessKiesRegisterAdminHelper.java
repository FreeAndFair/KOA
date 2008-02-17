package ie.ucd.srg.koa.databeheer.ejb.session;
import ie.ucd.srg.ejs.container.*;

import java.rmi.RemoteException;
/**
 * EJSRemoteStatelessKiesRegisterAdminHelper
 * @generated
 */
public class EJSRemoteStatelessKiesRegisterAdminHelper extends EJSWrapper implements KiesRegisterAdminHelper {
	/**
	 * EJSRemoteStatelessKiesRegisterAdminHelper
	 * @generated
	 */
	public EJSRemoteStatelessKiesRegisterAdminHelper() throws java.rmi.RemoteException {
		super();	}
	/**
	 * removeKiezer
	 * @generated
	 */
	public java.lang.String removeKiezer(java.lang.String sKiezerId) throws ie.ucd.srg.koa.exception.KOAException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		java.lang.String _EJS_result = null;
		try {
			ie.ucd.srg.koa.databeheer.ejb.session.KiesRegisterAdminHelperBean beanRef = (ie.ucd.srg.koa.databeheer.ejb.session.KiesRegisterAdminHelperBean)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = beanRef.removeKiezer(sKiezerId);
		}
		catch (ie.ucd.srg.koa.exception.KOAException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			container.postInvoke(this, 0, _EJS_s);
		}
		return _EJS_result;
	}
	/**
	 * insertKiezers
	 * @generated
	 */
	public java.lang.String[] insertKiezers(ie.ucd.srg.koa.databeheer.data.KiezerData[] kiezers, ie.ucd.srg.koa.kr.beans.KiezersHome xKiezersHome, ie.ucd.srg.koa.sar.SarHome xSarHome) throws ie.ucd.srg.koa.exception.KOAException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		java.lang.String[] _EJS_result = null;
		try {
			ie.ucd.srg.koa.databeheer.ejb.session.KiesRegisterAdminHelperBean beanRef = (ie.ucd.srg.koa.databeheer.ejb.session.KiesRegisterAdminHelperBean)container.preInvoke(this, 1, _EJS_s);
			_EJS_result = beanRef.insertKiezers(kiezers, xKiezersHome, xSarHome);
		}
		catch (ie.ucd.srg.koa.exception.KOAException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			container.postInvoke(this, 1, _EJS_s);
		}
		return _EJS_result;
	}
	/**
	 * updateKiezers
	 * @generated
	 */
	public java.lang.String[] updateKiezers(ie.ucd.srg.koa.databeheer.data.KiezerData[] xKiezers, ie.ucd.srg.koa.kr.beans.KiezersHome xKiezersHome, ie.ucd.srg.koa.sar.SarHome xSarHome) throws ie.ucd.srg.koa.exception.KOAException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		java.lang.String[] _EJS_result = null;
		try {
			ie.ucd.srg.koa.databeheer.ejb.session.KiesRegisterAdminHelperBean beanRef = (ie.ucd.srg.koa.databeheer.ejb.session.KiesRegisterAdminHelperBean)container.preInvoke(this, 2, _EJS_s);
			_EJS_result = beanRef.updateKiezers(xKiezers, xKiezersHome, xSarHome);
		}
		catch (ie.ucd.srg.koa.exception.KOAException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			container.postInvoke(this, 2, _EJS_s);
		}
		return _EJS_result;
	}
}
