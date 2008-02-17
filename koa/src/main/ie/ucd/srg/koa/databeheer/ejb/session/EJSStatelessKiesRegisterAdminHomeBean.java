package ie.ucd.srg.koa.databeheer.ejb.session;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSStatelessKiesRegisterAdminHomeBean
 * @generated
 */
public class EJSStatelessKiesRegisterAdminHomeBean extends EJSHome {
	/**
	 * EJSStatelessKiesRegisterAdminHomeBean
	 * @generated
	 */
	public EJSStatelessKiesRegisterAdminHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.databeheer.ejb.session.KiesRegisterAdmin create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
ie.ucd.srg.koa.databeheer.ejb.session.KiesRegisterAdmin _EJS_result = null;
boolean createFailed = false;
try {
	_EJS_result = (ie.ucd.srg.koa.databeheer.ejb.session.KiesRegisterAdmin) super.createWrapper(new BeanId(this, null));
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
