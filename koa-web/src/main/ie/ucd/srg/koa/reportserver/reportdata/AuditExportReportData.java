/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.reportserver.reportdata.AuditExportReportData.java
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
  *  0.1.7		09-07-2003	KvdP		First implementation, copy of ProcesVerbaalReportData
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.reportserver.reportdata;
import javax.xml.transform.stream.StreamSource;

import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.constants.JNDIProperties;
import ie.ucd.srg.koa.db.ReportsDB;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.reportserver.KOAXMLDbReader;
import ie.ucd.srg.koa.reportserver.reportdata.AbstractReportData;
import ie.ucd.srg.koa.reportserver.reportdata.ReportData;
import ie.ucd.srg.koa.utils.KOALogHelper;
/**
 * The report data class file for the Audit Export 
 * in the KOA Application
 * 
 * @author KvdP
 */
public class AuditExportReportData
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
			"[AuditExportReportData.init] init audit export report data...");
		/* set stream source */
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[AuditExportReportData.init] composing the global information header...");
		String sHeaderXML = null;
		try
		{
			ReportsDB xReportsDB = new ReportsDB();
			sHeaderXML = xReportsDB.getGlobalInformationHeader();
		}
		catch (KOAException koae)
		{
			KOALogHelper.logError(
				"AuditExportReportData.init",
				"Error getting the global information header for the audit export report",
				koae);
			throw koae;
		}
		catch (Exception ex)
		{
			KOALogHelper.logErrorCode(
				"AuditExportReportData.init",
				ErrorConstants.ERR_REPORT_DATA_INIT,
				ex);
			throw new KOAException(
				ErrorConstants.REPORT_PROCESVERBAAL_DEFAULT,
				ex);
		}
		/* set stream source */
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[AuditExportReportData.init] setting stream source");
		try
		{
			//String whereClause = " ACTION != '" + AuditEventListener.VOTER + "'";
			String whereClause = null;
			reader =
				new KOAXMLDbReader(
					JNDIProperties.getProperty(
						JNDIProperties.DATASOURCE_KOA_NO_TRANS),
					"KOA01",
					"AUDIT_LOG",
					"ROW_ID",
					whereClause,
					sHeaderXML,
					null,
					"UTF-8");
			streamSource = new StreamSource(reader);
			setStreamSource(streamSource);
		}
		catch (KOAException koae)
		{
			KOALogHelper.logError(
				"AuditExportReportData.init",
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
				String[] params = { "reader" };
				KOALogHelper.logErrorCode(
					"AuditExportReportData.close",
					ErrorConstants.ERR_COUNTERREADER_CLOSE,
					ioe);
			}
		}
	}
}