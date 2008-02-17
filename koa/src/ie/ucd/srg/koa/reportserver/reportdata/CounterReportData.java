/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.reportserver.reportdata.CounterReportData.java
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
import java.io.IOException;

import javax.xml.transform.stream.StreamSource;

import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.reportserver.KOACounterXMLReader;
import ie.ucd.srg.koa.reportserver.reportdata.AbstractReportData;
import ie.ucd.srg.koa.reportserver.reportdata.ReportData;
import ie.ucd.srg.koa.utils.KOALogHelper;
/**
 * The report data class file for the Status report
 * in the KOA Application
 * 
 * @author KuijerM
 */
public class CounterReportData extends AbstractReportData implements ReportData
{
	KOACounterXMLReader reader = null;
	/**
	 * initialize the report data
	 */
	public void init() throws ie.ucd.srg.logica.eplatform.error.EPlatformException
	{
		/* set stream source */
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[CounterReportData.init] setting stream source");
		/* get the variables */
		String start = reportDataContext.getParameter("periode_start");
		String end = reportDataContext.getParameter("periode_end");
		try
		{
			reader = new KOACounterXMLReader(start, end);
			setStreamSource(new StreamSource(reader));
		}
		catch (KOAException koae)
		{
			throw koae;
		}
		catch (Exception e)
		{
			KOALogHelper.logErrorCode(
				"CounterReportData.init",
				ErrorConstants.ERR_REPORT_DATA_INIT,
				e);
			throw new KOAException(
				ErrorConstants.REPORT_COUNTER_REPORT_INIT,
				e);
		}
	}
	/**
	 * @see ReportData#close()
	 */
	public void close()
	{
		try
		{
			reader.close();
		}
		catch (IOException ioe)
		{
			String[] params = { "reader" };
			KOALogHelper.logErrorCode(
				"CounterReportData.close",
				ErrorConstants.ERR_COUNTERREADER_CLOSE,
				ioe);
		}
		catch (Exception e)
		{
			//do nothing
		}
	}
}