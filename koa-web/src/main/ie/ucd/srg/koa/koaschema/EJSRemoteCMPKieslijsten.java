package ie.ucd.srg.koa.koaschema;
import ie.ucd.srg.ejs.container.*;

import java.rmi.RemoteException;
/**
 * EJSRemoteCMPKieslijsten
 * @generated
 */
public class EJSRemoteCMPKieslijsten extends EJSWrapper implements Kieslijsten {
	/**
	 * EJSRemoteCMPKieslijsten
	 * @generated
	 */
	public EJSRemoteCMPKieslijsten() throws java.rmi.RemoteException {
		super();	}
	/**
	 * getFk_kkr_1
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.Kieskringen getFk_kkr_1() throws java.rmi.RemoteException, javax.ejb.FinderException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		ie.ucd.srg.koa.koaschema.Kieskringen _EJS_result = null;
		try {
			ie.ucd.srg.koa.koaschema.KieslijstenBean beanRef = (ie.ucd.srg.koa.koaschema.KieslijstenBean)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = beanRef.getFk_kkr_1();
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
			container.postInvoke(this, 0, _EJS_s);
		}
		return _EJS_result;
	}
	/**
	 * getFk_kkr_1Key
	 * @generated
	 */
	public ie.ucd.srg.koa.koaschema.KieskringenKey getFk_kkr_1Key() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		ie.ucd.srg.koa.koaschema.KieskringenKey _EJS_result = null;
		try {
			ie.ucd.srg.koa.koaschema.KieslijstenBean beanRef = (ie.ucd.srg.koa.koaschema.KieslijstenBean)container.preInvoke(this, 1, _EJS_s);
			_EJS_result = beanRef.getFk_kkr_1Key();
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
	 * getLijstnaam
	 * @generated
	 */
	public java.lang.String getLijstnaam() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		java.lang.String _EJS_result = null;
		try {
			ie.ucd.srg.koa.koaschema.KieslijstenBean beanRef = (ie.ucd.srg.koa.koaschema.KieslijstenBean)container.preInvoke(this, 2, _EJS_s);
			_EJS_result = beanRef.getLijstnaam();
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
	 * getLijstposities
	 * @generated
	 */
	public java.util.Enumeration getLijstposities() throws java.rmi.RemoteException, javax.ejb.FinderException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		java.util.Enumeration _EJS_result = null;
		try {
			ie.ucd.srg.koa.koaschema.KieslijstenBean beanRef = (ie.ucd.srg.koa.koaschema.KieslijstenBean)container.preInvoke(this, 3, _EJS_s);
			_EJS_result = beanRef.getLijstposities();
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
	 * secondaryAddLijstposities
	 * @generated
	 */
	public void secondaryAddLijstposities(ie.ucd.srg.koa.koaschema.Lijstposities aLijstposities) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.koaschema.KieslijstenBean beanRef = (ie.ucd.srg.koa.koaschema.KieslijstenBean)container.preInvoke(this, 4, _EJS_s);
			beanRef.secondaryAddLijstposities(aLijstposities);
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
		return ;
	}
	/**
	 * secondaryRemoveLijstposities
	 * @generated
	 */
	public void secondaryRemoveLijstposities(ie.ucd.srg.koa.koaschema.Lijstposities aLijstposities) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.koaschema.KieslijstenBean beanRef = (ie.ucd.srg.koa.koaschema.KieslijstenBean)container.preInvoke(this, 5, _EJS_s);
			beanRef.secondaryRemoveLijstposities(aLijstposities);
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
		return ;
	}
	/**
	 * setLijstnaam
	 * @generated
	 */
	public void setLijstnaam(java.lang.String newLijstnaam) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.koaschema.KieslijstenBean beanRef = (ie.ucd.srg.koa.koaschema.KieslijstenBean)container.preInvoke(this, 6, _EJS_s);
			beanRef.setLijstnaam(newLijstnaam);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
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
}
