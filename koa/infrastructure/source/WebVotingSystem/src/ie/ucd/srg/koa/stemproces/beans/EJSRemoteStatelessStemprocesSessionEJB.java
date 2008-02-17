package ie.ucd.srg.koa.stemproces.beans;
import ie.ucd.srg.ejs.container.*;
import java.rmi.RemoteException;
/**
 * EJSRemoteStatelessStemprocesSessionEJB
 * @generated
 */
public class EJSRemoteStatelessStemprocesSessionEJB extends EJSWrapper implements StemprocesSessionEJB {
	/**
	 * EJSRemoteStatelessStemprocesSessionEJB
	 * @generated
	 */
	public EJSRemoteStatelessStemprocesSessionEJB() throws java.rmi.RemoteException {
		super();	}
	/**
	 * vote
	 * @generated
	 */
	public ie.ucd.srg.koa.dataobjects.StemTransactie vote(ie.ucd.srg.koa.dataobjects.Stem xStem, java.lang.String sStemcode, java.lang.String sWachtwoord) throws ie.ucd.srg.koa.exception.KOAException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		ie.ucd.srg.koa.dataobjects.StemTransactie _EJS_result = null;
		try {
			ie.ucd.srg.koa.stemproces.beans.StemprocesSessionEJBBean beanRef = (ie.ucd.srg.koa.stemproces.beans.StemprocesSessionEJBBean)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = beanRef.vote(xStem, sStemcode, sWachtwoord);
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
	 * getNextTransactionCode
	 * @generated
	 */
	public java.lang.String getNextTransactionCode() throws ie.ucd.srg.koa.exception.KOAException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		java.lang.String _EJS_result = null;
		try {
			ie.ucd.srg.koa.stemproces.beans.StemprocesSessionEJBBean beanRef = (ie.ucd.srg.koa.stemproces.beans.StemprocesSessionEJBBean)container.preInvoke(this, 1, _EJS_s);
			_EJS_result = beanRef.getNextTransactionCode();
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
}
