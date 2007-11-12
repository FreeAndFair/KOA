package ie.ucd.srg.koa.kieslijst.beans;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSStatelessKiesLijstHomeBean
 * @generated
 */
public class EJSStatelessKiesLijstHomeBean extends EJSHome {
	/**
	 * EJSStatelessKiesLijstHomeBean
	 * @generated
	 */
	public EJSStatelessKiesLijstHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.kieslijst.beans.KiesLijst create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
ie.ucd.srg.koa.kieslijst.beans.KiesLijst _EJS_result = null;
boolean createFailed = false;
try {
	_EJS_result = (ie.ucd.srg.koa.kieslijst.beans.KiesLijst) super.createWrapper(new BeanId(this, null));
}
catch (javax.ejb.CreateException ex) {
	createFailed = true;
	throw ex;
} catch (java.rmi.RemoteException ex) {
	createFailed = true;
	throw ex;
} catch (Throwable ex) {
	createFailed = true;
	throw new CreateFailureException(ex);
} finally {
	if (createFailed) {
		super.createFailure(beanO);
	}
}
return _EJS_result;	}
}
