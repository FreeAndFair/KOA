/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.constants.TSMSerializerFactories.java
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
  *  0.1		09-05-2003	XUi			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.adapter.soap;
import ie.ucd.srg.koa.constants.SOAPInterfaceProperties;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.soap.response.BlockResponse;
import ie.ucd.srg.koa.soap.response.CloseResponse;
import ie.ucd.srg.koa.soap.response.Counter;
import ie.ucd.srg.koa.soap.response.OpenResponse;
import ie.ucd.srg.koa.soap.response.PrepareForOpeningResponse;
import ie.ucd.srg.koa.soap.response.PrepareForReOpeningResponse;
import ie.ucd.srg.koa.soap.response.ReOpenResponse;
import ie.ucd.srg.koa.soap.response.Statistics;
import ie.ucd.srg.koa.soap.response.SuspendResponse;
import ie.ucd.srg.koa.utils.KOALogHelper;

import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.xml.utils.QName;
/**
 * Factories needed to serialize and deserialize objects
 * that are retrieved over the SOAP interface from the TSM
 * 
 * @author KuijerM
 * 
 */
public class TSMSerializerFactories
{
	// QNames
	private QName qPrepareForOpeningResponse = null;
	private QName qPrepareForReOpeningResponse = null;
	private QName qOpenResponse = null;
	private QName qSuspendResponse = null;
	private QName qBlockResponse = null;
	private QName qReOpenResponse = null;
	private QName qCloseResponse = null;
	private QName qCounter = null;
	private QName qStatistics = null;
	// Serializer and deserializer factories
	// prepare for opening
	BeanSerializerFactory serPrepareForOpening = null;
	BeanDeserializerFactory deserPrepareForOpening = null;
	// prepare for reopening
	BeanSerializerFactory serPrepareForReOpening = null;
	BeanDeserializerFactory deserPrepareForReOpening = null;
	// open
	BeanSerializerFactory serOpen = null;
	BeanDeserializerFactory deserOpen = null;
	// suspend
	BeanSerializerFactory serSuspend = null;
	BeanDeserializerFactory deserSuspend = null;
	// block
	BeanSerializerFactory serBlock = null;
	BeanDeserializerFactory deserBlock = null;
	// reOpen
	BeanSerializerFactory serReOpen = null;
	BeanDeserializerFactory deserReOpen = null;
	// close
	BeanSerializerFactory serClose = null;
	BeanDeserializerFactory deserClose = null;
	// counter
	BeanSerializerFactory serCounter = null;
	BeanDeserializerFactory deserCounter = null;
	// statistics
	BeanSerializerFactory serStatistics = null;
	BeanDeserializerFactory deserStatistics = null;
	/**
	 * private static singleton instance
	 */
	private static TSMSerializerFactories g_Instance = null;
	/**
	 * Constructor which initializes the serializers and deserializers
	 */
	private TSMSerializerFactories()
	{
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[TSMSerializerFactories.constructor] Initializing serializer factories.");
		// set QNames
		String sNamespace = null;
		try
		{
			sNamespace =
				SOAPInterfaceProperties.getProperty(
					SOAPInterfaceProperties.TSM_NAMESPACE);
			// Set QNames
			qPrepareForOpeningResponse =
				new QName(sNamespace, "PrepareForOpeningResponse");
			qPrepareForReOpeningResponse =
				new QName(sNamespace, "PrepareForReOpeningResponse");
			qOpenResponse = new QName(sNamespace, "OpenResponse");
			qSuspendResponse = new QName(sNamespace, "SuspendResponse");
			qBlockResponse = new QName(sNamespace, "BlockResponse");
			qReOpenResponse = new QName(sNamespace, "ReOpenResponse");
			qCloseResponse = new QName(sNamespace, "CloseResponse");
			qCounter = new QName(sNamespace, "Counter");
			qStatistics = new QName(sNamespace, "Statistics");
			// Create factories
			// prepare for open
			serPrepareForOpening =
				new BeanSerializerFactory(PrepareForOpeningResponse.class, qPrepareForOpeningResponse);
			deserPrepareForOpening =
				new BeanDeserializerFactory(
					PrepareForOpeningResponse.class,
					qPrepareForOpeningResponse);
			// prepare for reopening
			serPrepareForReOpening =
				new BeanSerializerFactory(
					PrepareForReOpeningResponse.class,
					qPrepareForReOpeningResponse);
			deserPrepareForReOpening =
				new BeanDeserializerFactory(
					PrepareForReOpeningResponse.class,
					qPrepareForReOpeningResponse);
			// open
			serOpen =
				new BeanSerializerFactory(OpenResponse.class, qOpenResponse);
			;
			deserOpen =
				new BeanDeserializerFactory(OpenResponse.class, qOpenResponse);
			// suspend
			serSuspend =
				new BeanSerializerFactory(
					SuspendResponse.class,
					qSuspendResponse);
			deserSuspend =
				new BeanDeserializerFactory(
					SuspendResponse.class,
					qSuspendResponse);
			// block
			serBlock =
				new BeanSerializerFactory(BlockResponse.class, qBlockResponse);
			deserBlock =
				new BeanDeserializerFactory(
					BlockResponse.class,
					qBlockResponse);
			// reOpen
			serReOpen =
				new BeanSerializerFactory(
					ReOpenResponse.class,
					qReOpenResponse);
			deserReOpen =
				new BeanDeserializerFactory(
					ReOpenResponse.class,
					qReOpenResponse);
			// close
			serClose =
				new BeanSerializerFactory(CloseResponse.class, qCloseResponse);
			deserClose =
				new BeanDeserializerFactory(
					CloseResponse.class,
					qCloseResponse);
			// counter
			serCounter = new BeanSerializerFactory(Counter.class, qCounter);
			deserCounter = new BeanDeserializerFactory(Counter.class, qCounter);
			// statistics
			serStatistics =
				new BeanSerializerFactory(Statistics.class, qStatistics);
			deserStatistics =
				new BeanDeserializerFactory(Statistics.class, qStatistics);
		}
		catch (KOAException koae)
		{
			KOALogHelper.logError(
				"TSMSoapAdapter.constructor",
				"Cannot obtain TSM properties",
				koae);
		}
	}
	/**
	 * Get an instance of the TSMSerializerFactories (Singleton implementation)
	 */
	//@ ensures \result != null;
	public static TSMSerializerFactories getInstance()
	{
		if (g_Instance == null)
		{
			g_Instance = new TSMSerializerFactories();
		}
		return g_Instance;
	}
	/**
	 * Gets the prepareForOpeningResponse
	 * @return Returns a QName
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ QName getPrepareForOpeningResponse()
	{
		return qPrepareForOpeningResponse;
	}
	/**
	 * Gets the prepareForReOpeningResponse
	 * @return Returns a QName
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ QName getPrepareForReOpeningResponse()
	{
		return qPrepareForReOpeningResponse;
	}
	/**
	 * Gets the openResponse
	 * @return Returns a QName
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ QName getOpenResponse()
	{
		return qOpenResponse;
	}
	/**
	 * Gets the suspendResponse
	 * @return Returns a QName
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ QName getSuspendResponse()
	{
		return qSuspendResponse;
	}
	/**
	 * Gets the blockResponse
	 * @return Returns a QName
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ QName getBlockResponse()
	{
		return qBlockResponse;
	}
	/**
	 * Gets the reOpenResponse
	 * @return Returns a QName
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ QName getReOpenResponse()
	{
		return qReOpenResponse;
	}
	/**
	 * Gets the closeResponse
	 * @return Returns a QName
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ QName getCloseResponse()
	{
		return qCloseResponse;
	}
	/**
	 * Gets the counter
	 * @return Returns a QName
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ QName getCounter()
	{
		return qCounter;
	}
	/**
	 * Gets the statistics
	 * @return Returns a QName
	 */
	//@ ensures \result != null;	
	public /*@ pure @*/ QName getStatistics()
	{
		return qStatistics;
	}
	/**
	 * Gets the serPrepareForOpening
	 * @return Returns a BeanSerializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanSerializerFactory getSerPrepareForOpening()
	{
		return serPrepareForOpening;
	}
	/**
	 * Gets the deserPrepareForOpening
	 * @return Returns a BeanDeserializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanDeserializerFactory getDeserPrepareForOpening()
	{
		return deserPrepareForOpening;
	}
	/**
	 * Gets the serPrepareForReOpening
	 * @return Returns a BeanSerializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanSerializerFactory getSerPrepareForReOpening()
	{
		return serPrepareForReOpening;
	}
	/**
	 * Gets the deserPrepareForReOpening
	 * @return Returns a BeanDeserializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanDeserializerFactory getDeserPrepareForReOpening()
	{
		return deserPrepareForReOpening;
	}
	/**
	 * Gets the serOpen
	 * @return Returns a BeanSerializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanSerializerFactory getSerOpen()
	{
		return serOpen;
	}
	/**
	 * Gets the deserOpen
	 * @return Returns a BeanDeserializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanDeserializerFactory getDeserOpen()
	{
		return deserOpen;
	}
	/**
	 * Gets the serSuspend
	 * @return Returns a BeanSerializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanSerializerFactory getSerSuspend()
	{
		return serSuspend;
	}
	/**
	 * Gets the deserSuspend
	 * @return Returns a BeanDeserializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanDeserializerFactory getDeserSuspend()
	{
		return deserSuspend;
	}
	/**
	 * Gets the serBlock
	 * @return Returns a BeanSerializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanSerializerFactory getSerBlock()
	{
		return serBlock;
	}
	/**
	 * Gets the deserBlock
	 * @return Returns a BeanDeserializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanDeserializerFactory getDeserBlock()
	{
		return deserBlock;
	}
	/**
	 * Gets the serReOpen
	 * @return Returns a BeanSerializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanSerializerFactory getSerReOpen()
	{
		return serReOpen;
	}
	/**
	 * Gets the deserReOpen
	 * @return Returns a BeanDeserializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanDeserializerFactory getDeserReOpen()
	{
		return deserReOpen;
	}
	/**
	 * Gets the serClose
	 * @return Returns a BeanSerializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanSerializerFactory getSerClose()
	{
		return serClose;
	}
	/**
	 * Gets the deserClose
	 * @return Returns a BeanDeserializerFactory
	 */
	//@ ensures \result != nulll;
	public /*@ pure @*/ BeanDeserializerFactory getDeserClose()
	{
		return deserClose;
	}
	/**
	 * Gets the serCounter
	 * @return Returns a BeanSerializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanSerializerFactory getSerCounter()
	{
		return serCounter;
	}
	/**
	 * Gets the deserCounter
	 * @return Returns a BeanDeserializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanDeserializerFactory getDeserCounter()
	{
		return deserCounter;
	}
	/**
	 * Gets the serStatistics
	 * @return Returns a BeanSerializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanSerializerFactory getSerStatistics()
	{
		return serStatistics;
	}
	/**
	 * Gets the deserStatistics
	 * @return Returns a BeanDeserializerFactory
	 */
	//@ ensures \result != null;
	public /*@ pure @*/ BeanDeserializerFactory getDeserStatistics()
	{
		return deserStatistics;
	}
}