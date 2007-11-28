/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.command.InitializeCommand.java
  *
  * -----------------------------------------------------------------------
  * 
  *  (c) 2003  Ministerie van Binnenlandse Zaken en Koninkrijkrelaties
  *
  *  Project		: Kiezen Op Afstand (KOA)
  *  Project Number	: ECF-2651
  *  
  *  History:
  *  Version	Date		Name		Reason
  * ---------------------------------------------------------
  *  1.0		01-05-2003	MKu			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.voorzitter.command.statechange;
import java.rmi.RemoteException;
import javax.ejb.*;
import java.util.Hashtable;
import javax.servlet.http.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.logica.eplatform.util.LogHelper;
import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.constants.JNDIProperties;
import ie.ucd.srg.koa.controller.beans.Controller;
import ie.ucd.srg.koa.controller.beans.ControllerHome;
import ie.ucd.srg.koa.dataobjects.CallResult;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.utils.KOALogHelper;
import ie.ucd.srg.koa.voorzitter.command.statechange.AbstractInitializeCommand;
/**
 * InitialzeCommand. Initializes the KOA system.
 * Public key and password are expected to initialize the system.
 * 
 * @author: KuijerM
 * 
 */
public class InitializeCommand extends AbstractInitializeCommand{
	/**
	 * The execute method on the Initialize command.
	 * This method is executed in the ejb command target.
	 * 
	 * @throws CommandException		necessary to fullfill abstract method signature
	 * @throws EPlatformException	thrown when the remote instance of the Controller can not be created.
	 */
	public void execute()throws ie.ucd.srg.logica.eplatform.command.CommandException, EPlatformException{
		LogHelper.log(
			LogHelper.INFO,
			"[InitializeCommand] execute with public key " + g_pkPublicKey);
		try{
			/* init the variabeles */
			Hashtable htProps = new Hashtable();
			htProps.put(
				Context.INITIAL_CONTEXT_FACTORY,
				JNDIProperties.getProperty(
					JNDIProperties.CONTROLLER_CONTEXT_FACTORY));
			htProps.put(
				Context.PROVIDER_URL,
				JNDIProperties.getProperty(JNDIProperties.CONTROLLER_PROVIDER));
			/* init the context */
			InitialContext icContext = new InitialContext(htProps);
			/* lookup the controller */
			Object obj =
				icContext.lookup(
					JNDIProperties.getProperty(JNDIProperties.CONTROLLER_NAME));
			/* create the controller */
			ControllerHome xControllerHome =
				(ControllerHome) PortableRemoteObject.narrow(
					obj,
					ControllerHome.class);
			Controller xController = xControllerHome.create();
			// Verify pincodes. If validated then continue with change of state 
			g_crCallResult = xController.checkPinCode(sPincode1, sPincode2);
			if (g_crCallResult.getResult() == CallResult.RESULT_OK){
				/* change state to initialize */
				g_crCallResult = xController.initialize(g_pkPublicKey);
			}
		}catch (NamingException ne){
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"InitializeCommand.execute",
				ErrorConstants.ERR_NAMING,
				params,
				ne);
			throw new KOAException(ErrorConstants.COMMAND_INITIALIZE_EXEC, ne);
		}catch (RemoteException re){
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"InitializeCommand.execute",
				ErrorConstants.ERR_REMOTE,
				params,
				re);
			throw new KOAException(ErrorConstants.COMMAND_INITIALIZE_EXEC, re);
		}catch (CreateException ce){
			String[] params = { "Controller" };
			KOALogHelper.logErrorCode(
				"InitializeCommand.execute",
				ErrorConstants.ERR_CREATE,
				params,
				ce);
			throw new KOAException(ErrorConstants.COMMAND_INITIALIZE_EXEC, ce);
		}catch (KOAException koae){
			KOALogHelper.logError(
				"InitializeCommand.execute",
				"KOAException while saving the public key",
				koae);
			throw koae;
		}
	}
	public void init(HttpServletRequest request){}
}
	
	
