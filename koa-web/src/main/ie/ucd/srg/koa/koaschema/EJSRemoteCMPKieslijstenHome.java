package ie.ucd.srg.koa.koaschema;
import ie.ucd.srg.ejs.container.*;

import java.rmi.RemoteException;
/**
 * EJSRemoteCMPKieslijstenHome
 * @generated
 */
public class EJSRemoteCMPKieslijstenHome extends EJSWrapper implements ie.ucd.srg.koa.koaschema.KieslijstenHome {
	/**
	 * EJSRemoteCMPKieslijstenHome
	 * @generated
	 */
	public EJSRemoteCMPKieslijstenHome() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.Kieslijsten create(java.lang.String kieslijstnummer, ie.ucd.srg.koa.koaschema.Kieskringen argFk_kkr_1) throws javax.ejb.CreateException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		ie.ucd.srg.koa.koaschema.Kieslijsten _EJS_result = null;
		try {
			ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean _EJS_beanRef = (ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = _EJS_beanRef.create(kieslijstnummer, argFk_kkr_1);
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
	public ie.ucd.srg.koa.koaschema.Kieslijsten create(java.lang.String kieslijstnummer, ie.ucd.srg.koa.koaschema.Kieskringen argFk_kkr_1, java.lang.String lijstnaam) throws javax.ejb.CreateException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		ie.ucd.srg.koa.koaschema.Kieslijsten _EJS_result = null;
		try {
			ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean _EJS_beanRef = (ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean)container.preInvoke(this, 1, _EJS_s);
			_EJS_result = _EJS_beanRef.create(kieslijstnummer, argFk_kkr_1, lijstnaam);
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
	 * findByPrimaryKey
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.Kieslijsten findByPrimaryKey(ie.ucd.srg.koa.koaschema.KieslijstenKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		ie.ucd.srg.koa.koaschema.Kieslijsten _EJS_result = null;
		try {
			ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean _EJS_beanRef = (ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean)container.preInvoke(this, 2, _EJS_s);
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
			container.postInvoke(this, 2, _EJS_s);
		}
		return _EJS_result;
	}
	/**
	 * findKieslijstenByFk_kkr_1
	 * @generated
	 */
	public java.util.Enumeration findKieslijstenByFk_kkr_1(ie.ucd.srg.koa.koaschema.KieskringenKey inKey) throws java.rmi.RemoteException, javax.ejb.FinderException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		java.util.Enumeration _EJS_result = null;
		try {
			ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean _EJS_beanRef = (ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean)container.preInvoke(this, 3, _EJS_s);
			_EJS_result = _EJS_beanRef.findKieslijstenByFk_kkr_1(inKey);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (javax.ejb.FinderException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
			ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean _EJS_beanRef = (ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean)container.preInvoke(this, 4, _EJS_s);
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
			ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean _EJS_beanRef = (ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean)container.preInvoke(this, 5, _EJS_s);
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
			ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean _EJS_beanRef = (ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean)container.preInvoke(this, 6, _EJS_s);
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
			ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean _EJS_beanRef = (ie.ucd.srg.koa.koaschema.EJSCMPKieslijstenHomeBean)container.preInvoke(this, 7, _EJS_s);
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
