/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.reportserver.reportdata.VerkiezingsUitslagExportReportData.java
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
  *  0.1.7		11-05-2003	MKu			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.reportserver.reportdata;
import javax.xml.transform.stream.StreamSource;

import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.constants.JNDIProperties;
import ie.ucd.srg.koa.constants.TechnicalProps;
import ie.ucd.srg.koa.db.ReportsDB;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.reportserver.KOAXMLDbReader;
import ie.ucd.srg.koa.reportserver.reportdata.AbstractReportData;
import ie.ucd.srg.koa.reportserver.reportdata.ReportData;
import ie.ucd.srg.koa.utils.KOALogHelper;
/**
 * Report data for the election result in XML format
 * 
 * @author KuijerM
 */
public class VerkiezingsUitslagExportReportData
	extends AbstractReportData
	implements ReportData
{
	StreamSource streamSource = null;
	KOAXMLDbReader reader = null;
	/**
	 * @see ReportData#init()
	 */
	public void init() throws ie.ucd.srg.logica.eplatform.error.EPlatformException
	{
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[VerkiezingsUitslagExportReportData.init] init election result export report data...");
		/* set stream source */
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[VerkiezingsUitslagExportReportData.init] composing the global information header...");
		String sHeaderXML = null;
		try
		{
			ReportsDB xReportsDB = new ReportsDB();
			sHeaderXML = xReportsDB.getGlobalInformationHeader();
		}
		catch (KOAException koae)
		{
			KOALogHelper.logError(
				"VerkiezingsUitslagExportReportData.init",
				"Error getting the global information header for election result export report data",
				koae);
			throw koae;
		}
		catch (Exception ex)
		{
			KOALogHelper.logErrorCode(
				"VerkiezingsUitslagExportReportData.init",
				ErrorConstants.ERR_REPORT_DATA_INIT,
				ex);
			throw new KOAException(
				ErrorConstants.REPORT_PROCESVERBAAL_DEFAULT,
				ex);
		}
		/* set stream source */
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[VerkiezingsUitslagExportReportData.init] setting stream source");
		try
		{
			String encoding =
				TechnicalProps.getProperty(
					TechnicalProps.KL_EXPORT_XML_ENCODING);
			reader =
				new KOAXMLDbReader(
					JNDIProperties.getProperty(
						JNDIProperties.DATASOURCE_ESB_NO_TRANS),
					"KOA01",
					"DECRYPTEDESB",
					"STEMNUMMER",
					sHeaderXML,
					null,
					encoding);
			streamSource = new StreamSource(reader);
			setStreamSource(streamSource);
		}
		catch (KOAException koae)
		{
			KOALogHelper.logError(
				"VerkiezingsUitslagExportReportData.init",
				"could not find property in jndi properties, stream source not set",
				koae);
			throw koae;
		}
	}
	/**
	 * @see ReportData#close()
	 */
	public void close()
	{
		if (reader != null)
		{
			try
			{
				reader.close();
			}
			catch (Exception ioe)
			{
				String[] params = { "xml db reader" };
				KOALogHelper.logErrorCode(
					"VerkiezingsUitslagExportReportData.close",
					ErrorConstants.ERR_COUNTERREADER_CLOSE,
					ioe);
			}
		}
	}
}