package ie.ucd.srg.koa.scheduler.beans;
import ie.ucd.srg.ejs.container.*;

import java.rmi.RemoteException;
/**
 * EJSRemoteStatelessSchedulerAdmin
 * @generated
 */
public class EJSRemoteStatelessSchedulerAdmin extends EJSWrapper implements SchedulerAdmin {
	/**
	 * EJSRemoteStatelessSchedulerAdmin
	 * @generated
	 */
	public EJSRemoteStatelessSchedulerAdmin() throws java.rmi.RemoteException {
		super();	}
	/**
	 * pollForScheduledJobs
	 * @generated
	 */
	public java.util.Vector pollForScheduledJobs() throws ie.ucd.srg.logica.eplatform.error.EPlatformException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		java.util.Vector _EJS_result = null;
		try {
			ie.ucd.srg.koa.scheduler.beans.SchedulerAdminBean beanRef = (ie.ucd.srg.koa.scheduler.beans.SchedulerAdminBean)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = beanRef.pollForScheduledJobs();
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
		return _EJS_result;
	}
	/**
	 * configureJobType
	 * @generated
	 */
	public void configureJobType(java.math.BigDecimal type, java.util.Date firstTime, java.math.BigDecimal interval) throws ie.ucd.srg.logica.eplatform.error.EPlatformException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.scheduler.beans.SchedulerAdminBean beanRef = (ie.ucd.srg.koa.scheduler.beans.SchedulerAdminBean)container.preInvoke(this, 1, _EJS_s);
			beanRef.configureJobType(type, firstTime, interval);
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
			container.postInvoke(this, 1, _EJS_s);
		}
		return ;
	}
	/**
	 * removeScheduledJob
	 * @generated
	 */
	public void removeScheduledJob(java.math.BigDecimal jobID) throws ie.ucd.srg.logica.eplatform.error.EPlatformException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.scheduler.beans.SchedulerAdminBean beanRef = (ie.ucd.srg.koa.scheduler.beans.SchedulerAdminBean)container.preInvoke(this, 2, _EJS_s);
			beanRef.removeScheduledJob(jobID);
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
			container.postInvoke(this, 2, _EJS_s);
		}
		return ;
	}
	/**
	 * rescheduleForJobType
	 * @generated
	 */
	public void rescheduleForJobType(java.math.BigDecimal successorType) throws ie.ucd.srg.logica.eplatform.error.EPlatformException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.scheduler.beans.SchedulerAdminBean beanRef = (ie.ucd.srg.koa.scheduler.beans.SchedulerAdminBean)container.preInvoke(this, 3, _EJS_s);
			beanRef.rescheduleForJobType(successorType);
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
			container.postInvoke(this, 3, _EJS_s);
		}
		return ;
	}
	/**
	 * rescheduleForJobType
	 * @generated
	 */
	public void rescheduleForJobType(java.math.BigDecimal successorType, java.lang.String sCustomContext) throws ie.ucd.srg.logica.eplatform.error.EPlatformException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.scheduler.beans.SchedulerAdminBean beanRef = (ie.ucd.srg.koa.scheduler.beans.SchedulerAdminBean)container.preInvoke(this, 4, _EJS_s);
			beanRef.rescheduleForJobType(successorType, sCustomContext);
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
			container.postInvoke(this, 4, _EJS_s);
		}
		return ;
	}
}
