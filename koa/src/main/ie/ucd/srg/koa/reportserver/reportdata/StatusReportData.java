/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.reportserver.reportdata.StatusReportData.java
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
  *  0.1		11-05-2003	MKu			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.reportserver.reportdata;
import javax.xml.transform.stream.StreamSource;

import ie.ucd.srg.koa.reportserver.KOAStatusReportXMLDBReader;
import ie.ucd.srg.koa.reportserver.command.KOAReportCommand;
import ie.ucd.srg.koa.reportserver.reportdata.AbstractReportData;
import ie.ucd.srg.koa.reportserver.reportdata.ReportData;
import ie.ucd.srg.koa.utils.KOALogHelper;
/**
 * The report data class file for the Status report
 * in the KOA Application
 * 
 * @author KuijerM
 */
public class StatusReportData extends AbstractReportData implements ReportData
{
	KOAStatusReportXMLDBReader reader = null;
	StreamSource streamSource = null;
	String sCaller = null;
	/**
	 * initialize the report data
	 * 
	 */
	public void init() throws ie.ucd.srg.logica.eplatform.error.EPlatformException
	{
		/* set stream source */
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[StatusReportData.init] setting stream source");
		sCaller = reportDataContext.getParameter(KOAReportCommand.CALLER);
		reader = new KOAStatusReportXMLDBReader(sCaller);
		streamSource = new StreamSource(reader);
		setStreamSource(streamSource);
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
				KOALogHelper.log(
					KOALogHelper.WARNING,
					"[StatusReportData.close] Error closing db reader");
			}
		}
	}
}