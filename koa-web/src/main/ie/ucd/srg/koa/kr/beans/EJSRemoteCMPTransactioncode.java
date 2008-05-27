package ie.ucd.srg.koa.kr.beans;
import ie.ucd.srg.ejs.container.*;

import java.rmi.RemoteException;
/**
 * EJSRemoteCMPTransactioncode
 * @generated
 */
public class EJSRemoteCMPTransactioncode extends EJSWrapper implements Transactioncode {
	/**
	 * EJSRemoteCMPTransactioncode
	 * @generated
	 */
	public EJSRemoteCMPTransactioncode() throws java.rmi.RemoteException {
		super();	}
	/**
	 * isAlreadyused
	 * @generated
	 */
	public boolean isAlreadyused() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		boolean _EJS_result = false;
		try {
			ie.ucd.srg.koa.kr.beans.TransactioncodeBean beanRef = (ie.ucd.srg.koa.kr.beans.TransactioncodeBean)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = beanRef.isAlreadyused();
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
	 * setAlreadyused
	 * @generated
	 */
	public void setAlreadyused(boolean newAlreadyused) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.kr.beans.TransactioncodeBean beanRef = (ie.ucd.srg.koa.kr.beans.TransactioncodeBean)container.preInvoke(this, 1, _EJS_s);
			beanRef.setAlreadyused(newAlreadyused);
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
		return ;
	}
}
