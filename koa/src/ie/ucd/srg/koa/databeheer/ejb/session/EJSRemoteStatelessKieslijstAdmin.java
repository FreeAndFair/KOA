package ie.ucd.srg.koa.databeheer.ejb.session;
import ie.ucd.srg.ejs.container.*;
import java.rmi.RemoteException;
/**
 * EJSRemoteStatelessKieslijstAdmin
 * @generated
 */
public class EJSRemoteStatelessKieslijstAdmin extends EJSWrapper implements KieslijstAdmin {
	/**
	 * EJSRemoteStatelessKieslijstAdmin
	 * @generated
	 */
	public EJSRemoteStatelessKieslijstAdmin() throws java.rmi.RemoteException {
		super();	}
	/**
	 * processImport
	 * @generated
	 */
	public void processImport(int iTempID) throws ie.ucd.srg.koa.exception.KOAException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.databeheer.ejb.session.KieslijstAdminBean beanRef = (ie.ucd.srg.koa.databeheer.ejb.session.KieslijstAdminBean)container.preInvoke(this, 0, _EJS_s);
			beanRef.processImport(iTempID);
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
		return ;
	}
	/**
	 * removeImport
	 * @generated
	 */
	public void removeImport(int iTempID) throws ie.ucd.srg.koa.exception.KOAException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = new EJSDeployedSupport();
		
		try {
			ie.ucd.srg.koa.databeheer.ejb.session.KieslijstAdminBean beanRef = (ie.ucd.srg.koa.databeheer.ejb.session.KieslijstAdminBean)container.preInvoke(this, 1, _EJS_s);
			beanRef.removeImport(iTempID);
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
