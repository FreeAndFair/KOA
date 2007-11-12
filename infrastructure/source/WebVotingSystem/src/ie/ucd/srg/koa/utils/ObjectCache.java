/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.kr.beans.KRSessionEJBBean
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
  *  0.1.7		16-07-2003	XUi			Performance: Use cached home interfaces 
  * 
  */
package ie.ucd.srg.koa.utils;
import java.rmi.RemoteException;
import java.util.Hashtable;
import javax.ejb.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.constants.JNDIProperties;
import ie.ucd.srg.koa.controller.beans.ControllerHome;
import ie.ucd.srg.koa.controller.beans.Koa_state;
import ie.ucd.srg.koa.controller.beans.Koa_stateHome;
import ie.ucd.srg.koa.controller.beans.Koa_stateKey;
import ie.ucd.srg.koa.databeheer.ejb.session.KiesRegisterAdminHelperHome;
import ie.ucd.srg.koa.databeheer.ejb.session.KieslijstAdminHelperHome;
import ie.ucd.srg.koa.esb.beans.DecryptedesbHome;
import ie.ucd.srg.koa.esb.beans.ESBDecryptHelperHome;
import ie.ucd.srg.koa.esb.beans.ESBSessionEJBHome;
import ie.ucd.srg.koa.esb.beans.EncryptedesbHome;
import ie.ucd.srg.koa.esb.beans.EsbfingerprintsHome;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.kieslijst.beans.KiesLijstHome;
import ie.ucd.srg.koa.koaschema.DistrictenHome;
import ie.ucd.srg.koa.koaschema.KandidaatcodesHome;
import ie.ucd.srg.koa.koaschema.KieskringenHome;
import ie.ucd.srg.koa.koaschema.KieslijstenHome;
import ie.ucd.srg.koa.koaschema.LijstpositiesHome;
import ie.ucd.srg.koa.kr.beans.KRFingerprintsHome;
import ie.ucd.srg.koa.kr.beans.KRSequenceEJBHome;
import ie.ucd.srg.koa.kr.beans.KRSessionEJBHome;
import ie.ucd.srg.koa.kr.beans.KiezersHome;
import ie.ucd.srg.koa.kr.beans.TransactioncodeHome;
import ie.ucd.srg.koa.sar.SarHome;
import ie.ucd.srg.koa.session.beans.UtilitySessionEJBHome;
import ie.ucd.srg.koa.stemproces.beans.StemprocesSessionEJBHome;
import ie.ucd.srg.koa.utils.KOALogHelper;
import ie.ucd.srg.opensource.commandUtil.*;
public class ObjectCache
{
	private static ObjectCache g_cache = null;
	private KRSessionEJBHome g_KRSessionHome = null;
	private KiesLijstHome g_KiesLijstHome = null;
	private StemprocesSessionEJBHome g_StemprocesSessionHome = null;
	private EJBCommandTargetHome g_TSMTargetHome = null;
	private ControllerHome g_ControllerHome = null;
	private ESBSessionEJBHome g_ESBSessionEJBHome = null;
	private Koa_stateHome g_KOAStateHome = null;
	private DecryptedesbHome g_DecryptedESBHome = null;
	private EsbfingerprintsHome g_ESBFingerprintsHome = null;
	private EncryptedesbHome g_EncryptedESBHome = null;
	private LijstpositiesHome g_LijstpositiesHome = null;
	private KieslijstenHome g_KieslijstenHome = null;
	private KiezersHome g_KiezersHome = null;
	private KandidaatcodesHome g_KandidaatcodesHome = null;
	private KRFingerprintsHome g_KRFingerprintsHome = null;
	private KRSequenceEJBHome g_KRSequenceEJBHome = null;
	private KieskringenHome g_KieskringenHome = null;
	private TransactioncodeHome g_TransactioncodeHome = null;
	private KiesRegisterAdminHelperHome g_KiesregisterAdminHelperHome = null;
	private SarHome g_SARHome = null;
	private DistrictenHome g_DistrictenHome = null;
	private KieslijstAdminHelperHome g_KieslijstAdminHelperHome = null;
	private ESBDecryptHelperHome g_ESBDecryptHelperHome = null;
	private UtilitySessionEJBHome g_UtilityHome = null;
	private Koa_state xState = null;
	private Hashtable datasources = new Hashtable();
	public static ObjectCache getInstance()
	{
		if (g_cache == null)
		{
			g_cache = new ObjectCache();
		}
		return g_cache;
	}
	// private constructor to prevent instatiation
	//@ signals (Exception) false;
	private ObjectCache(){
		try
		{
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: TSMCommandTarget");
			Hashtable htProps = new Hashtable();
			htProps.put(
				Context.INITIAL_CONTEXT_FACTORY,
				JNDIProperties.getProperty(
					JNDIProperties.TSM_COMMAND_TARGET_CONTEXT_FACTORY));
			htProps.put(
				Context.PROVIDER_URL,
				JNDIProperties.getProperty(
					JNDIProperties.TSM_COMMAND_TARGET_PROVIDER));
			InitialContext jndiContext = new InitialContext(htProps);
			Object xRef;
			/*Object xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.TSM_COMMAND_TARGET_JNDINAME));
			g_TSMTargetHome =
				(EJBCommandTargetHome) javax.rmi.PortableRemoteObject.narrow(
					xRef,
					EJBCommandTargetHome.class);*/
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: Controller");
			htProps.clear();
			htProps.put(
				Context.INITIAL_CONTEXT_FACTORY,
				JNDIProperties.getProperty(
					JNDIProperties.CONTROLLER_CONTEXT_FACTORY));
			
			htProps.put(
				Context.PROVIDER_URL,
				JNDIProperties.getProperty(JNDIProperties.CONTROLLER_PROVIDER));
			/* create the initial context */
			jndiContext = new InitialContext(htProps);
			/* look up the home interface */
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(JNDIProperties.CONTROLLER_NAME));
			g_ControllerHome =
				(ControllerHome) PortableRemoteObject.narrow(
					xRef,
					ControllerHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: KOA_State");
			/* set the properties for the initial context */
			htProps.clear();
			htProps.put(
				Context.INITIAL_CONTEXT_FACTORY,
				JNDIProperties.getProperty(
					JNDIProperties.KOA_STATE_CONTEXT_FACTORY));
			htProps.put(
				Context.PROVIDER_URL,
				JNDIProperties.getProperty(JNDIProperties.KOA_STATE_PROVIDER));
			/* create new initial context */
			jndiContext = new InitialContext(htProps);
			/* lookup the home interface of the state bean */
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(JNDIProperties.KOA_STATE_NAME));
			g_KOAStateHome =
				(Koa_stateHome) PortableRemoteObject.narrow(
					xRef,
					Koa_stateHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: DecryptedESBHome");
			htProps.clear();
			htProps.put(
				Context.INITIAL_CONTEXT_FACTORY,
				JNDIProperties.getProperty(
					JNDIProperties.ESB_SESSION_CONTEXT_FACTORY));
			htProps.put(
				Context.PROVIDER_URL,
				JNDIProperties.getProperty(
					JNDIProperties.ESB_SESSION_PROVIDER));
			jndiContext = new InitialContext(htProps);
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.ESB_DECRYPTED_EJB));
			g_DecryptedESBHome =
				(DecryptedesbHome) PortableRemoteObject.narrow(
					xRef,
					DecryptedesbHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: ESBFingerprintsHome");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.ESB_FINGERPRINT_EJB));
			g_ESBFingerprintsHome =
				(EsbfingerprintsHome) PortableRemoteObject.narrow(
					xRef,
					EsbfingerprintsHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: ESBFingerprintsHome");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.ESB_ENCRYPTED_EJB));
			g_EncryptedESBHome =
				(EncryptedesbHome) PortableRemoteObject.narrow(
					xRef,
					EncryptedesbHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: ESBSessionEJB");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(JNDIProperties.ESB_SESSION_EJB));
			g_ESBSessionEJBHome =
				(ESBSessionEJBHome) PortableRemoteObject.narrow(
					xRef,
					ESBSessionEJBHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: ESBDecryptHelperEJB");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.ESB_DECRYPT_HELPER_EJB));
			g_ESBDecryptHelperHome =
				(ESBDecryptHelperHome) PortableRemoteObject.narrow(
					xRef,
					ESBDecryptHelperHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: Lijstposities");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.KIESLIJST_LIJSTPOSITIES));
			g_LijstpositiesHome =
				(LijstpositiesHome) PortableRemoteObject.narrow(
					xRef,
					LijstpositiesHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: Kieslijsten");
			/* set the properties for the initial context */
			htProps.clear();
			htProps.put(
				Context.INITIAL_CONTEXT_FACTORY,
				JNDIProperties.getProperty(
					JNDIProperties.KIESLIJST_CONTEXT_FACTORY));
			htProps.put(
				Context.PROVIDER_URL,
				JNDIProperties.getProperty(JNDIProperties.KIESLIJST_PROVIDER));
			jndiContext = new InitialContext(htProps);
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.KIESLIJST_KIESLIJSTEN));
			g_KieslijstenHome =
				(KieslijstenHome) PortableRemoteObject.narrow(
					xRef,
					KieslijstenHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: KRSessionEJB");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(JNDIProperties.KR_SESSION_EJB));
			g_KRSessionHome =
				(KRSessionEJBHome) PortableRemoteObject.narrow(
					xRef,
					KRSessionEJBHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: Kieslijst");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.KIESLIJST_SESSION_EJB));
			g_KiesLijstHome =
				(KiesLijstHome) PortableRemoteObject.narrow(
					xRef,
					KiesLijstHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: StemprocesSessionEJB");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.STEMPROCES_SESSION_EJB));
			g_StemprocesSessionHome =
				(StemprocesSessionEJBHome) PortableRemoteObject.narrow(
					xRef,
					StemprocesSessionEJBHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: Districten");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.KIESLIJST_DISTRICTEN));
			g_DistrictenHome =
				(DistrictenHome) javax.rmi.PortableRemoteObject.narrow(
					xRef,
					DistrictenHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: KieslijstAdminHelper");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.DATABEHEER_KIESLIJSTADMIN_HELPER));
			g_KieslijstAdminHelperHome =
				(
					KieslijstAdminHelperHome) javax
						.rmi
						.PortableRemoteObject
						.narrow(
					xRef,
					KieslijstAdminHelperHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: KandidaatCodes");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.KIESLIJST_KANDIDAATCODES));
			g_KandidaatcodesHome =
				(KandidaatcodesHome) PortableRemoteObject.narrow(
					xRef,
					KandidaatcodesHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: Kieskringen");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.KIESLIJST_KIESKRINGEN));
			g_KieskringenHome =
				(KieskringenHome) javax.rmi.PortableRemoteObject.narrow(
					xRef,
					KieskringenHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: Kiezers");
			htProps.clear();
			htProps.put(
				Context.INITIAL_CONTEXT_FACTORY,
				JNDIProperties.getProperty(JNDIProperties.KR_CONTEXT_FACTORY));
			htProps.put(
				Context.PROVIDER_URL,
				JNDIProperties.getProperty(JNDIProperties.KR_PROVIDER));
			jndiContext = new InitialContext(htProps);
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(JNDIProperties.KIEZERS_EJB));
			g_KiezersHome =
				(KiezersHome) PortableRemoteObject.narrow(
					xRef,
					KiezersHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: Transactioncode");
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(JNDIProperties.KR_TXNUMMER_EJB));
			g_TransactioncodeHome =
				(TransactioncodeHome) PortableRemoteObject.narrow(
					xRef,
					TransactioncodeHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: KiesregisterAdminHelper");
			htProps.clear();
			htProps.put(
				Context.INITIAL_CONTEXT_FACTORY,
				JNDIProperties.getProperty(
					JNDIProperties.DATABEHEER_CONTEXT_FACTORY));
			htProps.put(
				Context.PROVIDER_URL,
				JNDIProperties.getProperty(JNDIProperties.DATABEHEER_PROVIDER));
			jndiContext = new InitialContext(htProps);
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.DATABEHEER_KIESREGISTER_ADMIN_HELPER));
			g_KiesregisterAdminHelperHome =
				(
					KiesRegisterAdminHelperHome) javax
						.rmi
						.PortableRemoteObject
						.narrow(
					xRef,
					KiesRegisterAdminHelperHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: KRFingerprintsHome");
			htProps.clear();
			htProps.put(
				Context.INITIAL_CONTEXT_FACTORY,
				JNDIProperties.getProperty(JNDIProperties.KR_CONTEXT_FACTORY));
			htProps.put(
				Context.PROVIDER_URL,
				JNDIProperties.getProperty(JNDIProperties.KR_PROVIDER));
			jndiContext = new InitialContext(htProps);
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.KR_FINGERPRINT_EJB));
			g_KRFingerprintsHome =
				(KRFingerprintsHome) PortableRemoteObject.narrow(
					xRef,
					KRFingerprintsHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: SARHome");
			htProps.clear();
			htProps.put(
				Context.INITIAL_CONTEXT_FACTORY,
				JNDIProperties.getProperty(JNDIProperties.SAR_CONTEXT_FACTORY));
			htProps.put(
				Context.PROVIDER_URL,
				JNDIProperties.getProperty(JNDIProperties.SAR_PROVIDER));
			jndiContext = new InitialContext(htProps);
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(JNDIProperties.SAR_SAR));
			g_SARHome =
				(SarHome) javax.rmi.PortableRemoteObject.narrow(
					xRef,
					SarHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: UtilitySessionEJB");
			htProps.clear();
			htProps.put(
				Context.INITIAL_CONTEXT_FACTORY,
				JNDIProperties.getProperty(
					JNDIProperties.KOA_UTILITY_CONTEXT_FACTORY));
			htProps.put(
				Context.PROVIDER_URL,
				JNDIProperties.getProperty(
					JNDIProperties.KOA_UTILITY_PROVIDER));
			jndiContext = new InitialContext(htProps);
			xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(
						JNDIProperties.KOA_UTILITY_JNDI_NAME));
			g_UtilityHome =
				(UtilitySessionEJBHome) PortableRemoteObject.narrow(
					xRef,
					UtilitySessionEJBHome.class);
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"Caching home interface: KRSequenceEJB");
		    xRef =
				jndiContext.lookup(
					JNDIProperties.getProperty(JNDIProperties.KR_SEQUENCE_EJB));
			g_KRSequenceEJBHome =
				(KRSequenceEJBHome) PortableRemoteObject.narrow(
					xRef,
					KRSequenceEJBHome.class);
			KOALogHelper.log(KOALogHelper.TRACE, "Retrieving KOA State object");
			Koa_stateHome xStateHome = getKOAStateHome();
			/* find the entity bean by primary key */
			Koa_stateKey key = new Koa_stateKey(new Integer(0));
			xState = xStateHome.findByPrimaryKey(key);
		}
		catch (NamingException ne)
		{
			String[] params = { "ObjectCache" };
			KOALogHelper.logErrorCode(
				"ObjectCache constructor",
				ErrorConstants.ERR_NAMING,
				params,
				ne);
		}
		catch (RemoteException re)
		{
			String[] params = { "ObjectCache" };
			KOALogHelper.logErrorCode(
				"ObjectCache constructor",
				ErrorConstants.ERR_REMOTE,
				params,
				re);
		}
		catch (FinderException fe)
		{
			Koa_stateHome xStateHome = getKOAStateHome();
			/* create the entity bean */
			try
			{
				xState = xStateHome.create(new Integer(0));
			}
			catch (CreateException ce)
			{
				String[] params = { "KOA state" };
				KOALogHelper.logErrorCode(
					"ControllerBean.createNewStateEntity",
					ErrorConstants.ERR_CREATE,
					params,
					ce);
			}
			catch (RemoteException re)
			{
				String[] params = { "KOA state" };
				KOALogHelper.logErrorCode(
					"ControllerBean.createNewStateEntity",
					ErrorConstants.ERR_REMOTE,
					params,
					re);
			}
		}
		catch (KOAException koae)
		{
			String[] params = { "ObjectCache" };
			KOALogHelper.logErrorCode(
				"ObjectCache constructor",
				ErrorConstants.ERR_NULL_POINTER,
				params,
				koae);
		}
	}
	/**
	 * Gets a datasource from the cache
	 * 
	 * @return DataSource the datasource which matches the sourcename
	 */
	//@ requires sourceName != null;
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ DataSource getDataSource(String sourceName)
	{
		return (DataSource) datasources.get(sourceName);
	}
	/**
	 * Adds a datasource to the cache
	 * 
	 * @param String the name of the source
	 * @param DataSource the datasource to cache
	 */
	//@ requires sourceName != null;
	//@ requires dataSource != null;
	//@ signals (Exception) false;
	public void putDataSource(String sourceName, DataSource dataSource)
	{
		datasources.put(sourceName, dataSource);
	}
	/**
	 * Gets the g_KRSessionHome
	 * @return KRSessionEJBHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ KRSessionEJBHome getKRSessionHome()
	{
		return g_KRSessionHome;
	}
	/**
	 * Gets the g_ESBSessionEJBHome
	 * @return ESBSessionEJBHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ ESBSessionEJBHome getESBSessionEJBHome()
	{
		return g_ESBSessionEJBHome;
	}
	/**
	 * Gets the g_KiesLijstHome
	 * @return KiesLijstHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ KiesLijstHome getKiesLijstHome()
	{
		return g_KiesLijstHome;
	}
	/**
	 * Gets the g_ControllerHome
	 * @return ControllerHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ ControllerHome getControllerHome()
	{
		return g_ControllerHome;
	}
	/**
	 * Gets the g_KOAStateHome
	 * @return Koa_stateHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ Koa_stateHome getKOAStateHome()
	{
		return g_KOAStateHome;
	}
	/**
	 * Gets the g_DecryptedESBHome
	 * @return DecryptedesbHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ DecryptedesbHome getDecryptedESBHome()
	{
		return g_DecryptedESBHome;
	}
	/**
	 * Gets the g_ESBFingerprintsHome
	 * @return EsbfingerprintsHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ EsbfingerprintsHome getESBFingerprintsHome()
	{
		return g_ESBFingerprintsHome;
	}
	/**
	 * Gets the g_EncryptedESBHome
	 * @return EncryptedesbHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ EncryptedesbHome getEncryptedESBHome()
	{
		return g_EncryptedESBHome;
	}
	/**
	 * Gets the g_LijstpositiesHome
	 * @return LijstpositiesHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ LijstpositiesHome getLijstpositiesHome()
	{
		return g_LijstpositiesHome;
	}
	/**
	 * Gets the g_KieslijstenHome
	 * @return KieslijstenHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ KieslijstenHome getKieslijstenHome()
	{
		return g_KieslijstenHome;
	}
	/**
	 * Gets the g_KiezersHome
	 * @return KiezersHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ KiezersHome getKiezersHome()
	{
		return g_KiezersHome;
	}
	/**
	 * Gets the g_TransactioncodeHome
	 * @return TransactioncodeHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ TransactioncodeHome getTransactioncodeHome()
	{
		return g_TransactioncodeHome;
	}
	/**
	 * Gets the g_KandidaatcodesHome
	 * @return KandidaatcodesHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ KandidaatcodesHome getKandidaatcodesHome()
	{
		return g_KandidaatcodesHome;
	}
	/**
	 * Gets the state
	 * @return Koa_state
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ Koa_state getState()
	{
		return xState;
	}
	/**
	 * Gets the g_StemprocesSessionHome
	 * @return StemprocesSessionEJBHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ StemprocesSessionEJBHome getStemprocesSessionHome()
	{
		return g_StemprocesSessionHome;
	}
	/**
	 * Gets the g_KRFingerprintsHome
	 * @return KRFingerprintsHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ KRFingerprintsHome getKRFingerprintsHome()
	{
		return g_KRFingerprintsHome;
	}
	/**
	 * Gets the g_KRSequenceEJBHome
	 * @return KRSequenceEJBHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ KRSequenceEJBHome getKRSequenceEJBHome()
	{
		return g_KRSequenceEJBHome;
	}
	/**
	 * Gets the g_TSMTargetHome
	 * @return EJBCommandTargetHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ EJBCommandTargetHome getTSMTargetHome()
	{
		return g_TSMTargetHome;
	}
	/**
	 * Gets the g_KieskringenHome
	 * @return KieskringenHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ KieskringenHome getKieskringenHome()
	{
		return g_KieskringenHome;
	}
	/**
	 * Gets the g_KiesregisterAdminHelperHome
	 * @return KiesRegisterAdminHelperHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ KiesRegisterAdminHelperHome getKiesregisterAdminHelperHome()
	{
		return g_KiesregisterAdminHelperHome;
	}
	/**
	 * Gets the g_SARHome
	 * @return SarHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ SarHome getSARHome()
	{
		return g_SARHome;
	}
	/**
	 * Gets the g_DistrictenHome
	 * @return DistrictenHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ DistrictenHome getDistrictenHome()
	{
		return g_DistrictenHome;
	}
	/**
	 * Gets the g_KieslijstAdminHelperHome
	 * @return KieslijstAdminHelperHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ KieslijstAdminHelperHome getKieslijstAdminHelperHome()
	{
		return g_KieslijstAdminHelperHome;
	}
	/**
	 * Gets the g_ESBDecryptHelperHome
	 * @return ESBDecryptHelperHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ ESBDecryptHelperHome getESBDecryptHelperHome()
	{
		return g_ESBDecryptHelperHome;
	}
	/**
	 * Gets the g_UtilityHome
	 * @return UtilitySessionEJBHome
	 */
	//@ ensures \result != null;
	//@ signals (Exception) false;
	public /*@ pure @*/ UtilitySessionEJBHome getUtilityHome()
	{
		return g_UtilityHome;
	}
}