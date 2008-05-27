package ie.ucd.srg.koa.session.beans;
import ie.ucd.srg.ejs.container.*;
import java.rmi.RemoteException;
/**
 * EJSRemoteStatelessUtilitySessionEJB
 * @generated
 */
public class EJSRemoteStatelessUtilitySessionEJB extends EJSWrapper implements UtilitySessionEJB {
	/**
	 * EJSRemoteStatelessUtilitySessionEJB
	 * @generated
	 */
	public EJSRemoteStatelessUtilitySessionEJB() throws java.rmi.RemoteException {
		super();	}
	/**
	 * logAuditRecord
	 * @generated
	 */
	public void logAuditRecord(int iSeverity, java.lang.String sAction, java.lang.String sComponent, java.lang.String sActor, java.lang.String sMessage) throws java.rmi.RemoteException, ie.ucd.srg.koa.exception.KOAException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.session.beans.UtilitySessionEJBBean beanRef = (ie.ucd.srg.koa.session.beans.UtilitySessionEJBBean)container.preInvoke(this, 0, _EJS_s);
			beanRef.logAuditRecord(iSeverity, sAction, sComponent, sActor, sMessage);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (ie.ucd.srg.koa.exception.KOAException ex) {
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
		return ;
	}
	/**
	 * logTXAuditRecord
	 * @generated
	 */
	public void logTXAuditRecord(int iSeverity, java.lang.String sAction, java.lang.String sComponent, java.lang.String sActor, java.lang.String sMessage) throws ie.ucd.srg.koa.exception.KOAException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.session.beans.UtilitySessionEJBBean beanRef = (ie.ucd.srg.koa.session.beans.UtilitySessionEJBBean)container.preInvoke(this, 1, _EJS_s);
			beanRef.logTXAuditRecord(iSeverity, sAction, sComponent, sActor, sMessage);
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
		return ;
	}
}
