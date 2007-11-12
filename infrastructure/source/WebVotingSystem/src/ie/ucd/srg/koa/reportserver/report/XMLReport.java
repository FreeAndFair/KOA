/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.reportserver.report.XMLReport.java
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
  *  0.1		12-05-2003	XUi			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.reportserver.report;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import javax.servlet.http.*;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.reportserver.report.AbstractReport;
import ie.ucd.srg.koa.reportserver.report.Report;
import ie.ucd.srg.koa.reportserver.reportdata.ReportData;
import ie.ucd.srg.koa.utils.KOALogHelper;
/**
 * XML report implementation to create reports in the XML format.
 * Implements the report interface.
 * 
 * @author uiterlix
 * 
 */
public class XMLReport extends AbstractReport implements Report
{
	/**
	 * Init the XML report
	 * 
	 * @throws EPlatformException when something goes wrong initializing
	 * 
	 * @see Report#init()
	 */
	public void init() throws EPlatformException
	{
		// set streamsource
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[XMLReport.init] Initializing streamsource: "
				+ getReportContext().getProperty("stylesheet"));
		/* Here we don't need a source, since we're dealing with
		   XML that won't be transformed */
		source = null;
		contentType = getReportContext().getProperty("content-type");
	}
	/**
	 * Generates the XML Report
	 * 
	 * @param reportData the reportdata to get the data from
	 * @param out The outputstream to write the data to
	 * 
	 * @throws EPlatformException exception when generating the report
	 * 
	 * @see Report#generate(ReportData, OutputStream)
	 */
	public void generate(ReportData reportData, OutputStream out)
		throws EPlatformException
	{
		KOALogHelper.log(
			KOALogHelper.TRACE,
			"[XMLReport.generate] Generating report.");
		BufferedReader myBReader = null;
		try
		{
			OutputStreamWriter outWriter = new OutputStreamWriter(out);
			myBReader =
				new BufferedReader(reportData.getStreamSource().getReader());
			BufferedWriter bWriter = new BufferedWriter(outWriter);
			String currentLine = null;
			while ((currentLine = myBReader.readLine()) != null)
			{
				bWriter.write(currentLine);
			}
			bWriter.flush();
		}
		catch (IOException ioe)
		{
			String[] params = { "Outputstream" };
			KOALogHelper.logErrorCode(
				"XMLReport.generate",
				ErrorConstants.ERR_IO,
				params,
				ioe);
			throw new EPlatformException(
				ErrorConstants.REPORT_XML_GENERATE,
				ioe);
		}
		/* Also catch the unexpected exceptions to make sure the reader gets closed */
		catch (Exception e)
		{
			KOALogHelper.logErrorCode(
				"XMLReport.generate",
				ErrorConstants.ERR_REPORT_GENERATE_DEFAULT,
				e);
			throw new EPlatformException(ErrorConstants.REPORT_XML_GENERATE, e);
		}
	}
	/**
	 * Generates the XML Report for the HTTP channel
	 * 
	 * @param reportData the reportdata to get the data from
	 * @param res The HTTP response to write the data to
	 * 
	 * @throws EPlatformException exception when generating the report
	 * 
	 * @see Report#generate(ReportData, HttpServletResponse)
	 */
	public void generate(ReportData reportData, HttpServletResponse res)
		throws EPlatformException
	{
		try
		{
			generate(reportData, res.getOutputStream());
		}
		catch (IOException ioe)
		{
			String[] params = { "Request outputstream" };
			KOALogHelper.logErrorCode(
				"XMLReport.generate",
				ErrorConstants.ERR_IO,
				params,
				ioe);
			throw new EPlatformException(
				ErrorConstants.REPORT_XML_GENERATE,
				ioe);
		}
	}
}