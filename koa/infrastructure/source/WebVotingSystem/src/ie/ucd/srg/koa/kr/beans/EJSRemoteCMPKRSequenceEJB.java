package ie.ucd.srg.koa.kr.beans;
import ie.ucd.srg.ejs.container.*;

import java.rmi.RemoteException;
/**
 * EJSRemoteCMPKRSequenceEJB
 * @generated
 */
public class EJSRemoteCMPKRSequenceEJB extends EJSWrapper implements KRSequenceEJB {
	/**
	 * EJSRemoteCMPKRSequenceEJB
	 * @generated
	 */
	public EJSRemoteCMPKRSequenceEJB() throws java.rmi.RemoteException {
		super();	}
	/**
	 * getNextID
	 * @generated
	 */
	public java.math.BigDecimal getNextID() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		java.math.BigDecimal _EJS_result = null;
		try {
			ie.ucd.srg.koa.kr.beans.KRSequenceEJBBean beanRef = (ie.ucd.srg.koa.kr.beans.KRSequenceEJBBean)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = beanRef.getNextID();
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
	 * setNextID
	 * @generated
	 */
	public void setNextID(java.math.BigDecimal newNextID) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.kr.beans.KRSequenceEJBBean beanRef = (ie.ucd.srg.koa.kr.beans.KRSequenceEJBBean)container.preInvoke(this, 1, _EJS_s);
			beanRef.setNextID(newNextID);
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
