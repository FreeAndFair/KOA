package ie.ucd.srg.koa.controller.beans;
import ie.ucd.srg.ejs.container.*;

/**
 * EJSStatelessControllerHomeBean
 * @generated
 */
public class EJSStatelessControllerHomeBean extends EJSHome {
	/**
	 * EJSStatelessControllerHomeBean
	 * @generated
	 */
	public EJSStatelessControllerHomeBean() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 * @generated
	 */
	public ie.ucd.srg.koa.controller.beans.Controller create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
ie.ucd.srg.koa.controller.beans.Controller _EJS_result = null;
boolean createFailed = false;
try {
	_EJS_result = (ie.ucd.srg.koa.controller.beans.Controller) super.createWrapper(new BeanId(this, null));
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
