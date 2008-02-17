/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.constants.JNDIProperties.java
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
  *  0.1		17-04-2003	MKu			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.constants;
import java.io.IOException;
import java.util.Properties;

import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.utils.KOALogHelper;
/**
 * The properties concearning the jndi locations of
 * different components.
 * 
 * @author KuijerM
 * 
 */
public class JNDIProperties
{
	/**
	 * The singleton instance of the JNDIProperties class
	 * 
	 */
	private static JNDIProperties g_instance = null;
	/**
	 * The location of the resource to load.
	 * The resource it is looking for is /koa_jndi.properties
	 * 
	 */
	private final static String RESOURCE = "/koa_jndi.properties";
	/**
	 * The properties object containing all the properties
	 * 
	 */
	private Properties g_pProps = null;
	/**
	 * The key used to store the controller jndi name
	 * The key value is ie.ucd.srg.koa.controller.jndi.ControllerName
	 * 
	 */
	public final static String CONTROLLER_NAME =
		"ie.ucd.srg.koa.controller.jndi.ControllerName";
	/**
	 * The key used to store the controller jndi url provider
	 * The key value is ie.ucd.srg.koa.controller.jndi.Provider
	 * 
	 */
	public final static String CONTROLLER_PROVIDER =
		"ie.ucd.srg.koa.controller.jndi.Provider";
	/**
	 * The key used to store the context factory used for the controller
	 * The key value is ie.ucd.srg.koa.controller.jndi.InitialContextFactory
	 * 
	 */
	public final static String CONTROLLER_CONTEXT_FACTORY =
		"ie.ucd.srg.koa.controller.jndi.InitialContextFactory";
	/**
	 * The key used to store the koa state jndi name
	 * The key value is ie.ucd.srg.koa.state.jndi.KoaStateName
	 * 
	 */
	public final static String KOA_STATE_NAME =
		"ie.ucd.srg.koa.state.jndi.KoaStateName";
	/**
	 * The key used to store the koa state jndi url provider
	 * The key value is ie.ucd.srg.koa.state.jndi.Provider
	 * 
	 */
	public final static String KOA_STATE_PROVIDER =
		"ie.ucd.srg.koa.state.jndi.Provider";
	/**
	 * The key used to store the context factory used for the koa state
	 * The key value is ie.ucd.srg.koa.state.jndi.InitialContextFactory
	 * 
	 */
	public final static String KOA_STATE_CONTEXT_FACTORY =
		"ie.ucd.srg.koa.state.jndi.InitialContextFactory";
	/**
	 * The key used to store the list of comma seperated registered ESB components
	 * The key value is ie.ucd.srg.koa.esb.registration.components
	 * 
	 */
	public final static String ESB_REGISTERED_COMPONENTS =
		"ie.ucd.srg.koa.esb.registration.components";
	/**
	 * The prefix before the ESB component ID used in the name of the key to store the context factory
	 * The value is ie.ucd.srg.koa.esb.registration.jndi.InitialContextFactory.
	 * After this prefix the component id is placed to get the value
	 * 
	 */
	public final static String ESB_REGISTERED_COMPONENT_CONTEXT_FACTORY_PREFIX =
		"ie.ucd.srg.koa.esb.registration.jndi.InitialContextFactory.";
	/**
	 * The prefix before the ESB component ID used in the name of the key to store the provider url
	 * After this prefix the component id is placed to get the value
	 * The value is ie.ucd.srg.koa.esb.registration.jndi.Provider.
	 * 
	 */
	public final static String ESB_REGISTERED_COMPONENT_PROVIDER_PREFIX =
		"ie.ucd.srg.koa.esb.registration.jndi.Provider.";
	/**
	 * The prefix before the ESB component ID used in the name of the key to store the jndi name
	 * After this prefix the component id is placed to get the value
	 * The value is ie.ucd.srg.koa.esb.registration.jndi.JNDIName.
	 * 
	 */
	public final static String ESB_REGISTERED_COMPONENT_JNDINAME_PREFIX =
		"ie.ucd.srg.koa.esb.registration.jndi.JNDIName.";
	/**
	 * The key used to store the list of comma seperated registered ESB components
	 * The key value is ie.ucd.srg.koa.kr.registration.components
	 * 
	 */
	public final static String KR_REGISTERED_COMPONENTS =
		"ie.ucd.srg.koa.kr.registration.components";
	/**
	 * The prefix before the KR component ID used in the name of the key to store the context factory
	 * After this prefix the component id is placed to get the value
	 * The value is ie.ucd.srg.koa.kr.registration.jndi.InitialContextFactory.
	 * 
	 */
	public final static String KR_REGISTERED_COMPONENT_CONTEXT_FACTORY_PREFIX =
		"ie.ucd.srg.koa.kr.registration.jndi.InitialContextFactory.";
	/**
	 * The prefix before the KR component ID used in the name of the key to store the provider url
	 * After this prefix the component id is placed to get the value
	 * The value is ie.ucd.srg.koa.kr.registration.jndi.Provider.
	 * 
	 */
	public final static String KR_REGISTERED_COMPONENT_PROVIDER_PREFIX =
		"ie.ucd.srg.koa.kr.registration.jndi.Provider.";
	/**
	 * The prefix before the KR component ID used in the name of the key to store the jndi name
	 * After this prefix the component id is placed to get the value
	 * The value is ie.ucd.srg.koa.kr.registration.jndi.JNDIName.
	 * 
	 */
	public final static String KR_REGISTERED_COMPONENT_JNDINAME_PREFIX =
		"ie.ucd.srg.koa.kr.registration.jndi.JNDIName.";
	/**
	 * The key used to store the context factory used for the databeheer
	 * The key value is ie.ucd.srg.koa.databeheer.jndi.InitialContextFactory
	 * 
	 */
	public final static String DATABEHEER_CONTEXT_FACTORY =
		"ie.ucd.srg.koa.databeheer.jndi.InitialContextFactory";
	/**
	 * The key used to store the context factory used for the databeheer kiesregister admin
	 * The key value is ie.ucd.srg.koa.databeheer.jndi.KiesRegisterAdmin
	 * 
	 */
	public final static String DATABEHEER_KIESREGISTER_ADMIN =
		"ie.ucd.srg.koa.databeheer.jndi.KiesRegisterAdmin";
	/**
	 * The key used to store the context factory used for the databeheer kieslijst admin
	 * The key value is ie.ucd.srg.koa.databeheer.jndi.KieslijstAdmin
	 * 
	 */
	public final static String DATABEHEER_KIESLIJST_ADMIN =
		"ie.ucd.srg.koa.databeheer.jndi.KieslijstAdmin";
	/**
	 * The key used to store the context factory used for the databeheer kieslijst admin helper class
	 * The key value is ie.ucd.srg.koa.databeheer.jndi.KieslijstAdminHelper
	 * 
	 */
	public final static String DATABEHEER_KIESLIJSTADMIN_HELPER =
		"ie.ucd.srg.koa.databeheer.jndi.KieslijstAdminHelper";
	/**
	 * The key used to store the context factory used for the databeheer kiesregister admin helper class
	 * The key value is ie.ucd.srg.koa.databeheer.jndi.KiesRegisterAdminHelperHome
	 * 
	 */
	public final static String DATABEHEER_KIESREGISTER_ADMIN_HELPER =
		"ie.ucd.srg.koa.databeheer.jndi.KiesRegisterAdminHelperHome";
	/**
	 * The key used to store the databeheer jndi name
	 * The key value is ie.ucd.srg.koa.databeheer.jndi.Provider
	 * 
	 */
	public final static String DATABEHEER_PROVIDER =
		"ie.ucd.srg.koa.databeheer.jndi.Provider";
	/**
	 * The key used to store the datasource database KOA
	 * The key value is ie.ucd.srg.koa.datasource.jndi.KOA
	 * 
	 */
	public final static String DATASOURCE_KOA =
		"ie.ucd.srg.koa.datasource.jndi.KOA";
	/**
	 * The key used to store the datasource database KR
	 */
	public final static String DATASOURCE_KR =
		"ie.ucd.srg.koa.kr.datasource.jndi.KR";
	/**
	 * The key used to store the datasource database ESB
	 */
	public final static String DATASOURCE_ESB =
		"ie.ucd.srg.koa.esb.datasource.jndi.ESB";
	/**
	 * The key used to store the datasource database KOA without transaction support
	 */
	public final static String DATASOURCE_KOA_NO_TRANS =
		"ie.ucd.srg.koa.datasource.jndi.KOA_NO_TRANS";
	/**
	 * The key used to store the datasource database KR without transaction support
	 */
	public final static String DATASOURCE_KR_NO_TRANS =
		"ie.ucd.srg.koa.datasource.jndi.KR_NO_TRANS";
	/**
	 * The key used to store the datasource database ESB without transaction support
	 */
	public final static String DATASOURCE_ESB_NO_TRANS =
		"ie.ucd.srg.koa.datasource.jndi.ESB_NO_TRANS";
	/**
	 * The key used to store the initial context factory for all datasources
	 * The key value is ie.ucd.srg.koa.datasource.jndi.InitialContextFactory
	 * 
	 */
	public final static String DATASOURCE_CONTEXT_FACTORY =
		"ie.ucd.srg.koa.datasource.jndi.InitialContextFactory";
	/**
	 * The key used to store the provider url for all datasources
	 * The key value is ie.ucd.srg.koa.datasource.jndi.Provider
	 * 
	 */
	public final static String DATASOURCE_PROVIDER =
		"ie.ucd.srg.koa.datasource.jndi.Provider";
	/**
	 * The key used to store the initial context factory for the SAR tables
	 * The key value is ie.ucd.srg.koa.sar.jndi.InitialContextFactory
	 * 
	 */
	public final static String SAR_CONTEXT_FACTORY =
		"ie.ucd.srg.koa.sar.jndi.InitialContextFactory";
	/**
	 * The key used to store the provider url for SAR tables
	 * The key value is ie.ucd.srg.koa.sar.jndi.Provider
	 * 
	 */
	public final static String SAR_PROVIDER =
		"ie.ucd.srg.koa.sar.jndi.Provider";
	/**
	 * The key used to store the context factory used for the sar
	 * The key value is ie.ucd.srg.koa.sar.jndi.SarHome
	 * 
	 */
	public final static String SAR_SAR = "ie.ucd.srg.koa.sar.jndi.SarHome";
	/**
	 * The key used to store the initial context factory for the kieslijst tables
	 * The key value is ie.ucd.srg.koa.kieslijst.jndi.InitialContextFactory
	 * 
	 */
	public final static String KIESLIJST_CONTEXT_FACTORY =
		"ie.ucd.srg.koa.kieslijst.jndi.InitialContextFactory";
	/**
	 * The key used to store the provider url for kieslijst tables
	 * The key value is ie.ucd.srg.koa.kieslijst.jndi.Provider
	 * 
	 */
	public final static String KIESLIJST_PROVIDER =
		"ie.ucd.srg.koa.kieslijst.jndi.Provider";
	/**
	 * The key used to store the context factory used for the kieslijsten
	 * The key value is ie.ucd.srg.koa.kieslijst.jndi.Kieslijsten
	 * 
	 */
	public final static String KIESLIJST_KIESLIJSTEN =
		"ie.ucd.srg.koa.kieslijst.jndi.Kieslijsten";
	/**
	 * The key used to store the context factory used for the kieskringen
	 * The key value is ie.ucd.srg.koa.kieslijst.jndi.Kieskringen
	 * 
	 */
	public final static String KIESLIJST_KIESKRINGEN =
		"ie.ucd.srg.koa.kieslijst.jndi.Kieskringen";
	/**
	 * The key used to store the context factory used for the kieskringen
	 * The key value is ie.ucd.srg.koa.kieslijst.jndi.Districten	
	 * 
	 */
	public final static String KIESLIJST_DISTRICTEN =
		"ie.ucd.srg.koa.kieslijst.jndi.Districten";
	/**
	 * The key used to store the context factory used for the kandidaatcodes
	 * The key value is ie.ucd.srg.koa.kieslijst.jndi.Kandidaatcodes
	 * 
	 */
	public final static String KIESLIJST_KANDIDAATCODES =
		"ie.ucd.srg.koa.kieslijst.jndi.Kandidaatcodes";
	/**
	 * The key used to store the context factory used for the lijstposisties
	 * The key value is ie.ucd.srg.koa.kieslijst.jndi.Lijstposities
	 * 
	 */
	public final static String KIESLIJST_LIJSTPOSITIES =
		"ie.ucd.srg.koa.kieslijst.jndi.Lijstposities";
	/**
	 * The key used to store the home of kieslijst SESSION BEAN
	 */
	public final static String KIESLIJST_SESSION_EJB =
		"ie.ucd.srg.koa.kieslijst.jndi.kieslijsthome";
	/**
	 * The key used to store the home interface used for the kr session ejb
	 * The key value is ie.ucd.srg.koa.kr.jndi.krhome
	 * 
	 */
	public final static String KR_SESSION_EJB =
		"ie.ucd.srg.koa.kr.jndi.krhome";
	/**
	 * The key used to store the provider url
	 * The key value is ie.ucd.srg.koa.kr.jndi.Provider
	 * 
	 */
	public final static String KR_PROVIDER =
		"ie.ucd.srg.koa.kr.jndi.Provider";
	/**
	 * The key used to store the initial context factory
	 * The key value is ie.ucd.srg.koa.kr.jndi.InitialContextFactory
	 * 
	 */
	public final static String KR_CONTEXT_FACTORY =
		"ie.ucd.srg.koa.kr.jndi.InitialContextFactory";
	/**
	 * The key used to store the home interface used for the kr fingerprint ejb
	 * 
	 */
	public final static String KR_FINGERPRINT_EJB =
		"ie.ucd.srg.koa.kr.jndi.krfingerprint";
	/**
	 * The key used to store the home interface used for the kr sequence ejb
	 * 
	 */
	public final static String KR_SEQUENCE_EJB =
		"ie.ucd.srg.koa.kr.jndi.krsequence";
	/**
	 * The key used to store the home interface used for the transactienummer ejb
	 * 
	 */
	public final static String KR_TXNUMMER_EJB =
		"ie.ucd.srg.koa.kr.jndi.TransactienummerHome";
	/**
	 * The key used to store the home interface used for the kiezers ejb
	 * 
	 */
	public final static String KIEZERS_EJB =
		"ie.ucd.srg.koa.kr.jndi.kiezershome";
	/**
	 * The key used to store the home interface used for the esb fingerprint ejb
	 * 
	 */
	public final static String ESB_FINGERPRINT_EJB =
		"ie.ucd.srg.koa.esb.jndi.esbfingerprint";
	/**
	 * The key used to store the home interface used for the esb sequence ejb
	 * 
	 */
	public final static String ESB_SEQUENCE_EJB =
		"ie.ucd.srg.koa.esb.jndi.esbsequence";
	/**
	 * The key used to store the home interface used for the encrypted esb ejb
	 * 
	 */
	public final static String ESB_ENCRYPTED_EJB =
		"ie.ucd.srg.koa.esb.jndi.encryptedesb";
	/**
	 * The key used to store the home interface used for the decrypted esb ejb
	 * 
	 */
	public final static String ESB_DECRYPTED_EJB =
		"ie.ucd.srg.koa.esb.jndi.decryptedesb";
	/**
	 * The key used to store the home interface used for the decrypted esb ejb
	 * 
	 */
	public final static String ESB_DECRYPT_HELPER_EJB =
		"ie.ucd.srg.koa.esb.jndi.esbdecrypthelperejb";
	/**
	 * The key used to store the intitial context factory used for the esb session ejb
	 * 
	 */
	public final static String ESB_SESSION_CONTEXT_FACTORY =
		"ie.ucd.srg.koa.esb.jndi.InitialContextFactory";
	/**
	 * The key used to store the provider url used for the esb session ejb
	 * 
	 */
	public final static String ESB_SESSION_PROVIDER =
		"ie.ucd.srg.koa.esb.jndi.Provider";
	/**
	 * The key used to store the home interface used for the esb session ejb
	 * 
	 */
	public final static String ESB_SESSION_EJB =
		"ie.ucd.srg.koa.esb.jndi.esbsessionbean";
	/**
	 * The key used to store the home interface used for the stemproces
	 * 
	 */
	public final static String STEMPROCES_SESSION_EJB =
		"ie.ucd.srg.koa.stemproces.jndi.stemproceshome";
	/**
	 * The key used to store the initial context factory fpr tje scheduler
	 * The key value is ie.ucd.srg.koa.scheduler.jndi.InitialContextFactory
	 * 
	 */
	public final static String SCHEDULER_CONTEXT_FACTORY =
		"ie.ucd.srg.koa.scheduler.jndi.InitialContextFactory";
	/**
	 * The key used to store the initial context factory fpr tje scheduler
	 * The key value is ie.ucd.srg.koa.scheduler.jndi.Provider
	 * 
	 */
	public final static String SCHEDULER_PROVIDER =
		"ie.ucd.srg.koa.scheduler.jndi.Provider";
	/**
	 * The key used to store the JNDI name for the scheduler
	 * The key value is ie.ucd.srg.koa.scheduler.jndi.JNDIName
	 * 
	 */
	public final static String SCHEDULER_JNDI_NAME =
		"ie.ucd.srg.koa.scheduler.jndi.JNDIName";
	/**
	 * The key used to store the JNDI name for a scheduled job
	 * The key value is ie.ucd.srg.koa.scheduler.job.jndi.JNDIName
	 * 
	 */
	public final static String SCHEDULED_JOB_JNDI_NAME =
		"ie.ucd.srg.koa.scheduler.job.jndi.JNDIName";
	/**
	 * The key used to store the JNDI name for the scheduler administrator
	 * The key value is ie.ucd.srg.koa.scheduler.admin.jndi.JNDIName
	 * 
	 */
	public final static String SCHEDULER_ADMIN_JNDI_NAME =
		"ie.ucd.srg.koa.scheduler.admin.jndi.JNDIName";
	/**
	 * The key used to store the JNDI name for a job type
	 * The key value is ie.ucd.srg.koa.scheduler.jobtype.jndi.JNDIName
	 * 
	 */
	public final static String JOB_TYPE_JNDI_NAME =
		"ie.ucd.srg.koa.scheduler.jobtype.jndi.JNDIName";
	/**
	 * The key used to store the context factory for a audit log
	 * The key value is ie.ucd.srg.koa.session.utility.jndi.ContextFactory
	 * 
	 */
	public final static String KOA_UTILITY_CONTEXT_FACTORY =
		"ie.ucd.srg.koa.session.utility.jndi.ContextFactory";
	/**
	 * The key used to store the provider url for a audit log
	 * The key value is ie.ucd.srg.koa.session.utility.jndi.Provider
	 * 
	 */
	public final static String KOA_UTILITY_PROVIDER =
		"ie.ucd.srg.koa.session.utility.jndi.Provider";
	/**
	 * The key used to store the jndi name for a audit log
	 * The key value is ie.ucd.srg.koa.session.utility.jndi.JNDIName
	 * 
	 */
	public final static String KOA_UTILITY_JNDI_NAME =
		"ie.ucd.srg.koa.session.utility.jndi.JNDIName";
	/**
	 * The key used to store the initial context factory name for the tsm command target
	 * The key value is ie.ucd.srg.koa.tsm.commandtarget.jndi.InitialContextFactory
	 * 
	 */
	public final static String TSM_COMMAND_TARGET_CONTEXT_FACTORY =
		"ie.ucd.srg.koa.tsm.commandtarget.jndi.InitialContextFactory";
	/**
	 * The key used to store the provider url for the tsm command target
	 * The key value is ie.ucd.srg.koa.tsm.commandtarget.jndi.Provider
	 * 
	 */
	public final static String TSM_COMMAND_TARGET_PROVIDER =
		"ie.ucd.srg.koa.tsm.commandtarget.jndi.Provider";
	/**
	 * The key used to store the jndi name for the tsm command target
	 * The key value is ie.ucd.srg.koa.tsm.commandtarget.jndi.JNDIName
	 * 
	 */
	public final static String TSM_COMMAND_TARGET_JNDINAME =
		"ie.ucd.srg.koa.tsm.commandtarget.jndi.JNDIName";
	/**
	 * The private constructor for the JNDI properties
	 * This method is private to ensure the singleton usage
	 * 
	 * @throws KOAException when the loading of the resource is failing
	 * 
	 */
	//@ signals (KOAException) true;
	private JNDIProperties() throws KOAException
	{
		/* load the properties file */
		this.loadResource();
	}
	/**
	 * Private method to get the instance, to make sure 
	 * there will be only one JNDIProperties instance
	 * 
	 * @return JNDIProperties the instance of the properties object
	 * 
	 * @throws KOAException when anything goes wrong during the get instance
	 * 
	 */
	//@ ensures \result != null;
	//@ signals (KOAException) true;
	private static JNDIProperties getInstance() throws KOAException
	{
		/* check if there is an instance */
		if (g_instance == null)
		{
			init();
		}
		/* return the instance */
		return g_instance;
	}
	/**
	 * Initialize the properties.
	 * 
	 * @throws KOAException when we fail to init the properties
	 */
	//@ signals (KOAException) true;
	private static synchronized void init() throws KOAException
	{
		/* create a new instance */
		g_instance = new JNDIProperties();
	}
	/**
	 * load the resource from the file system
	 * 
	 * @throws KOAException when anything goes wrong during the load of the resource
	 * 
	 */
	//@ signals (KOAException) true;
	private void loadResource() throws KOAException
	{
		/* create new properties instance */
		g_pProps = new Properties();
		try
		{
			/* load the resource */
			g_pProps.load(this.getClass().getResourceAsStream(RESOURCE));
		}
		/* catch any io exceptions */
		catch (IOException ioe)
		{
			/* log the error and throw new KOAException */
			String[] params = { "load file " + RESOURCE };
			KOALogHelper.logErrorCode(
				"JNDIProperties.loadResource",
				ErrorConstants.ERR_IO,
				params,
				ioe);
			throw new KOAException(ErrorConstants.JNDI_PROPS_LOAD, ioe);
		}
	}
	/**
	 * Get the property for the specified key
	 * 
	 * @param sKey The key to get the value for
	 * 
	 * @return String the value for the specified key
	 * 
	 * @throws KOAException when the property is not found
	 */
	//@ requires sKey != null;
	//@ ensures \result != null;
	//@ signals (KOAException) true;
	public static String getProperty(String sKey) throws KOAException
	{
		String sValue = null;
		/* try to get the value from the properties */
		sValue = getInstance().g_pProps.getProperty(sKey);
		if (sValue == null)
		{
			KOALogHelper.log(
				KOALogHelper.ERROR,
				"[JNDIProperties.getProperty] Could not find property " + sKey);
			throw new KOAException(ErrorConstants.JNDI_PROPS_NOT_FOUND);
		}
		return sValue;
	}
}