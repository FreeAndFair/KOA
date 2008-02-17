/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.reportserver.command.KOAReportCommand.java
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
  *  0.1		12-05-2003	MKu			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.reportserver.command;

/**@author Alan Morkan
 * The following import statement has been commented out as it didn't provide
 * the neccessary methods. The Ticket class of the eplatform is being used instead.
 * This may be an incorrect assumption.
 */
//import sun.security.krb5.internal.Ticket;

import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.exception.KOAException;
import ie.ucd.srg.koa.reportserver.report.Report;
import ie.ucd.srg.koa.reportserver.report.ReportFactory;
import ie.ucd.srg.koa.reportserver.reportdata.ReportData;
import ie.ucd.srg.koa.reportserver.reportdata.ReportDataFactory;
import ie.ucd.srg.logica.eplatform.command.CommandException;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.logica.eplatform.ticket.Ticket;
import ie.ucd.srg.logica.eplatform.ticket.TicketConstants;
import ie.ucd.srg.logica.eplatform.util.LogHelper;

import java.util.Enumeration;
import java.util.Properties;
/**
 * The KOAReportCommand provides a KOA implementation of a command that can generate reports.
 * All actions are performed out side of the container.
 * 
 * @author KuijerM
 */
public class KOAReportCommand
	extends ie.ucd.srg.logica.eplatform.command.http.AbstractHttpCommand
	implements ReportCommand
{
	public static String CALLER = "caller";
	private java.lang.String RESULT_JSP;
	protected Properties parameters;
	private java.lang.String reportName;
	private java.lang.String reportDataName;
	private Report report;
	private ReportData reportData;
	/**
	 * executes the command.
	 * 
	 * @throws CommandException
	 * @throws EPlatformException
	 */
	public void execute()
		throws ie.ucd.srg.logica.eplatform.command.CommandException, EPlatformException
	{
		LogHelper.trace(LogHelper.TRACE, "[KOAReportCommand] execute");
	}
	/**
	 * returns the ErrorJSP
	 * 
	 * @return String	representing the error jsp
	 */
	public String getErrorJSP()
	{
		return "error.jsp";
	}
	/**
	 * returns the report, which was retrieved in the postExecution() method
	 * 
	 * @return Report
	 */
	public Report getReport()
	{
		LogHelper.trace(LogHelper.TRACE, "[KOAReportCommand] getReport");
		return report;
	}
	/**
	 * returns the ReportData, which was retrieved in the postExecution() method
	 * 
	 * @return ReportData
	 */
	public ReportData getReportData()
	{
		LogHelper.trace(LogHelper.TRACE, "[KOAReportCommand] getReportData");
		return reportData;
	}
	/**
	 * returns the resultJSP
	 * 
	 * @return String representing the resulting jsp
	 */
	public String getResultJSP()
	{
		return RESULT_JSP;
	}
	/**
	 * initializes the command. It tries to read the report name which was
	 * requested by the user.
	 * 
	 * @param request	an object representing the request to the servlet
	 */
	public void init(javax.servlet.http.HttpServletRequest request)
	{
		reportData = null;
		report = null;
		LogHelper.trace(LogHelper.TRACE, "[KOAReportCommand] init");
		RESULT_JSP = "/ReportRenderServlet";
		/* get the name of the chairman */
		String caller = "Onbekend";
		Ticket t =
			(Ticket) request.getSession(true).getAttribute(
				TicketConstants.TICKET_IN_SESSION);
		if (t != null)
		{
			caller = t.getUserID();
		}
		// get the reportname
		reportName = request.getParameter("report");
		// save all the parameters
		parameters = new Properties();
		Enumeration params = request.getParameterNames();
		String key;
		while (params.hasMoreElements())
		{
			key = (String) params.nextElement();
			parameters.put(key, request.getParameter(key));
		}
		//set the caller
		parameters.put(CALLER, caller);
	}
	/**
	 * postExecutes the command. This method actually instantiates the report and
	 * its accompanying reportdata.
	 * 
	 * @throws EPlatformException when something goes wrong
	 */
	public void postExecution()
		throws ie.ucd.srg.logica.eplatform.error.EPlatformException
	{
		LogHelper.trace(LogHelper.TRACE, "[KOAReportCommand] postExecution");
		try
		{
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAReportCommand] getting report data");
			ReportDataFactory reportDataFactory =
				ReportDataFactory.getReportDataFactory();
			reportData =
				reportDataFactory.getReportData(reportDataName, parameters);
		}
		catch (KOAException koae)
		{
			throw (EPlatformException) koae;
		}
		catch (Exception e)
		{
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAReportCommand] Exception in execute");
			throw new EPlatformException(ErrorConstants.REPORT_DATA_COMMAND, e);
		}
		try
		{
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAReportCommand] getting report");
			ReportFactory reportFactory = ReportFactory.getReportFactory();
			report = reportFactory.getReport(reportName);
		}
		catch (KOAException koae)
		{
			throw (EPlatformException) koae;
		}
		catch (Exception e)
		{
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAReportCommand] Exception in execute");
			throw new EPlatformException(ErrorConstants.REPORT_COMMAND, e);
		}
	}
	/**
	 * preExecutes the command.
	 */
	public void preExecution()
		throws ie.ucd.srg.logica.eplatform.error.EPlatformException
	{
		LogHelper.trace(LogHelper.TRACE, "[KOAReportCommand] preExecution");
		try
		{
			// get the report to get the reportDataName of the report
			ReportFactory reportFactory = ReportFactory.getReportFactory();
			Report report = reportFactory.getReport(reportName);
			reportDataName = report.getReportDataName();
		}
		catch (KOAException nsre)
		{
			LogHelper.trace(
				LogHelper.TRACE,
				"[KOAReportCommand] Exception in execute");
			throw (EPlatformException) nsre;
		}
	}
}
