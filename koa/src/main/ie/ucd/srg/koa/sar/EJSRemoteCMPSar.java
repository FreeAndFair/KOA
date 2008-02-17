package ie.ucd.srg.koa.sar;
import ie.ucd.srg.ejs.container.*;

import java.rmi.RemoteException;
/**
 * EJSRemoteCMPSar
 * @generated
 */
public class EJSRemoteCMPSar extends EJSWrapper implements Sar {
	/**
	 * EJSRemoteCMPSar
	 * @generated
	 */
	public EJSRemoteCMPSar() throws java.rmi.RemoteException {
		super();	}
	/**
	 * getStemcode
	 * @generated
	 */
	public java.lang.String getStemcode() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		java.lang.String _EJS_result = null;
		try {
			ie.ucd.srg.koa.sar.SarBean beanRef = (ie.ucd.srg.koa.sar.SarBean)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = beanRef.getStemcode();
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
	 * setStemcode
	 * @generated
	 */
	public void setStemcode(java.lang.String newStemcode) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.sar.SarBean beanRef = (ie.ucd.srg.koa.sar.SarBean)container.preInvoke(this, 1, _EJS_s);
			beanRef.setStemcode(newStemcode);
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
