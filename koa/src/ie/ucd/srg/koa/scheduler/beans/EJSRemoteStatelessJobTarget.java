package ie.ucd.srg.koa.scheduler.beans;
import ie.ucd.srg.ejs.container.*;

import java.rmi.RemoteException;
/**
 * EJSRemoteStatelessJobTarget
 * @generated
 */
public class EJSRemoteStatelessJobTarget extends EJSWrapper implements JobTarget {
	/**
	 * EJSRemoteStatelessJobTarget
	 * @generated
	 */
	public EJSRemoteStatelessJobTarget() throws java.rmi.RemoteException {
		super();	}
	/**
	 * performExecute
	 * @generated
	 */
	public void performExecute(java.math.BigDecimal jobID) throws ie.ucd.srg.logica.eplatform.error.EPlatformException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.scheduler.beans.JobTargetBean beanRef = (ie.ucd.srg.koa.scheduler.beans.JobTargetBean)container.preInvoke(this, 0, _EJS_s);
			beanRef.performExecute(jobID);
		}
		catch (ie.ucd.srg.logica.eplatform.error.EPlatformException ex) {
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
		return ;
	}
}
