package ie.ucd.srg.koa.esb.beans;
import ie.ucd.srg.ejs.container.*;

import java.rmi.RemoteException;
/**
 * EJSRemoteCMPDecryptedesbHome
 * @generated
 */
public class EJSRemoteCMPDecryptedesbHome extends EJSWrapper implements ie.ucd.srg.koa.esb.beans.DecryptedesbHome {
	/**
	 * EJSRemoteCMPDecryptedesbHome
	 * @generated
	 */
	public EJSRemoteCMPDecryptedesbHome() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Decryptedesb create(java.math.BigDecimal stemnummer) throws javax.ejb.CreateException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		ie.ucd.srg.koa.esb.beans.Decryptedesb _EJS_result = null;
		try {
			ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean _EJS_beanRef = (ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = _EJS_beanRef.create(stemnummer);
		}
		catch (javax.ejb.CreateException ex) {
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
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Decryptedesb create(java.math.BigDecimal stemnummer, java.lang.String sKandidaatCode, java.lang.String sKieskringnummer, java.lang.String sDistrictnummer, java.lang.String sKieslijstnummer, java.lang.String sPositienummer, java.lang.String sLijstnaam, java.lang.String sAchternaam, java.lang.String sVoorletters) throws javax.ejb.CreateException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		ie.ucd.srg.koa.esb.beans.Decryptedesb _EJS_result = null;
		try {
			ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean _EJS_beanRef = (ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean)container.preInvoke(this, 1, _EJS_s);
			_EJS_result = _EJS_beanRef.create(stemnummer, sKandidaatCode, sKieskringnummer, sDistrictnummer, sKieslijstnummer, sPositienummer, sLijstnaam, sAchternaam, sVoorletters);
		}
		catch (javax.ejb.CreateException ex) {
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
	 * findByLijstpositie
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Decryptedesb findByLijstpositie(java.lang.String sKieskringnummer, java.lang.String sKieslijstnummer, java.lang.String sPositienummer) throws javax.ejb.FinderException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		ie.ucd.srg.koa.esb.beans.Decryptedesb _EJS_result = null;
		try {
			ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean _EJS_beanRef = (ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean)container.preInvoke(this, 2, _EJS_s);
			_EJS_result = _EJS_beanRef.findByLijstpositie(sKieskringnummer, sKieslijstnummer, sPositienummer);
		}
		catch (javax.ejb.FinderException ex) {
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
	/**
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.esb.beans.Decryptedesb findByPrimaryKey(ie.ucd.srg.koa.esb.beans.DecryptedesbKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		ie.ucd.srg.koa.esb.beans.Decryptedesb _EJS_result = null;
		try {
			ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean _EJS_beanRef = (ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean)container.preInvoke(this, 3, _EJS_s);
			_EJS_result = _EJS_beanRef.findByPrimaryKey(primaryKey);
		}
		catch (javax.ejb.FinderException ex) {
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
			container.postInvoke(this, 3, _EJS_s);
		}
		return _EJS_result;
	}
	/**
	 * getEJBMetaData
	 * @generated
	 */
	public javax.ejb.EJBMetaData getEJBMetaData() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		javax.ejb.EJBMetaData _EJS_result = null;
		try {
			ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean _EJS_beanRef = (ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean)container.preInvoke(this, 4, _EJS_s);
			_EJS_result = _EJS_beanRef.getEJBMetaData();
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			container.postInvoke(this, 4, _EJS_s);
		}
		return _EJS_result;
	}
	/**
	 * getHomeHandle
	 * @generated
	 */
	public javax.ejb.HomeHandle getHomeHandle() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		javax.ejb.HomeHandle _EJS_result = null;
		try {
			ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean _EJS_beanRef = (ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean)container.preInvoke(this, 5, _EJS_s);
			_EJS_result = _EJS_beanRef.getHomeHandle();
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			container.postInvoke(this, 5, _EJS_s);
		}
		return _EJS_result;
	}
	/**
	 * remove
	 * @generated
	 */
	public void remove(java.lang.Object arg0) throws java.rmi.RemoteException, javax.ejb.RemoveException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean _EJS_beanRef = (ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean)container.preInvoke(this, 6, _EJS_s);
			_EJS_beanRef.remove(arg0);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (javax.ejb.RemoveException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			container.postInvoke(this, 6, _EJS_s);
		}
		return ;
	}
	/**
	 * remove
	 * @generated
	 */
	public void remove(javax.ejb.Handle arg0) throws java.rmi.RemoteException, javax.ejb.RemoveException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean _EJS_beanRef = (ie.ucd.srg.koa.esb.beans.EJSCMPDecryptedesbHomeBean)container.preInvoke(this, 7, _EJS_s);
			_EJS_beanRef.remove(arg0);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (javax.ejb.RemoveException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			container.postInvoke(this, 7, _EJS_s);
		}
		return ;
	}
}
