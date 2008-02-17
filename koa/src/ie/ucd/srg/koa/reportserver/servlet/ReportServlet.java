/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.report.ReportServlet.java
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
package ie.ucd.srg.koa.reportserver.servlet;
import java.io.IOException;
import ie.ucd.srg.koa.exception.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Enumeration;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.logica.eplatform.servlet.UtilServlet;
import ie.ucd.srg.logica.eplatform.ticket.Ticket;
import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.reportserver.command.ReportCommand;
import ie.ucd.srg.koa.reportserver.report.Report;
import ie.ucd.srg.koa.reportserver.reportdata.ReportData;
import ie.ucd.srg.koa.utils.KOALogHelper;
import ie.ucd.srg.koa.votecommands.CommandConstants;
public class ReportServlet extends UtilServlet
{
	/**
	 * doGet method of the Servlet
	 * Gets the Report from the Command that's saved in the Session and generates it's report.
	 * The report will be send to the screen.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, java.io.IOException
	{
		ReportData reportData = null;
		try
		{
			// Get report  
			ReportCommand command =
				(ReportCommand) req.getAttribute(
					CommandConstants.COMMAND_IN_REQUEST_KEY);
			Report report = command.getReport();
			reportData = command.getReportData();
			/* Get the ticket */
			Ticket ticket = getTicket(req);
			/* Verify the roles against the reportdata */
			KOALogHelper.log(
				KOALogHelper.TRACE,
				"[ReportServlet.doGet] Verifying user roles");
			boolean bAuthorized = false;
			String role = null;
			Enumeration enumer = reportData.getAuthorizedRoles().elements();
			while (enumer.hasMoreElements())
			{
				role = (String) enumer.nextElement();
				KOALogHelper.log(
					KOALogHelper.TRACE,
					"[ReportServlet.doGet] Checking role: " + role);
				if (ticket.hasRole(role))
				{
						KOALogHelper.log(
						KOALogHelper.TRACE,
						"[ReportServlet.doGet] Valid role found!");
					bAuthorized = true;
					break;
				}
			}
			if (bAuthorized)
			{
				/* init the reportdata and the report */
				KOALogHelper.log(
					KOALogHelper.TRACE,
					"[ReportServlet.doGet] initializing the report and reportdata");
				reportData.init();
				report.init();
				KOALogHelper.log(
					KOALogHelper.TRACE,
					"[ReportServlet.doGet] Set content-type "
						+ report.getContentType());
				res.setContentType(report.getContentType());
				if (report.getContentType().equals("text/xml"))
				{
					res.setHeader(
						"Content-Disposition",
						"inline; filename=\""
							+ report.getReportDataName()
							+ ".xml\"");
				}
				// Save the xml report with a specific name
				if (report.getContentType().equals("text/koaexport"))
				{
					res.setHeader(
						"Content-Disposition",
						"attachment; filename=\""
							+ report.getReportDataName()
							+ ".xml\"");
				}
				/* generate the report */
				KOALogHelper.log(
					KOALogHelper.TRACE,
					"[ReportServlet.doGet] Generate");
				report.generate(reportData, res);
				/* flush the buffer of the response to make sure everything is sent to the client */
				KOALogHelper.log(
					KOALogHelper.TRACE,
					"[ReportServlet.doGet] Flush outputstream");
				res.flushBuffer();
				/* make sure the report data is closed properly */
				reportData.close();
			}
			else
			{
				reportData.close();
				KOALogHelper.log(
					KOALogHelper.WARNING,
					"[ReportServlet.doGet] No valid roles found!, redirecting.");
				req.setAttribute(
					"ERROR",
					"U bent niet geautoriseerd om het rapport te bekijken.");
				redirect(
					req,
					res,
					props.getProperty(
						"ie.ucd.srg.logica.eplatform.error.DefaultErrorPage"));
			}
		}
		catch (EPlatformException ep)
		{
			/* close the report data if something goes wrong and log the error */
			if (reportData != null)
			{
				reportData.close();
			}
			KOALogHelper.logErrorCode(
				"ReportServlet",
				ErrorConstants.ERR_GENERATE_REPORT,
				ep);
			/* set the error message and redirect to the error jsp */
			try
			{
				req.setAttribute(
					"ERROR",
					ie.ucd.srg
						.logica
						.eplatform
						.error
						.ErrorMessageFactory
						.getErrorMessageFactory()
						.getErrorMessage(ep.getErrorCode(), ep.getParameters()));
			}
			/**@author Alan Morkan
			 *TODO The body of the following catch block should be completed properly
			 */
			catch(KOAException k){
			    System.out.println(k);
			}
			catch (IOException ioe)
			{
				req.setAttribute(
					"ERROR",
					"Er heeft zich een probleem voorgedaan bij het ophalen van het rapport: "
						+ ep.getMessage());
			}
			redirect(
				req,
				res,
				props.getProperty(
					"ie.ucd.srg.logica.eplatform.error.DefaultErrorPage"));
		}
		catch (Exception e)
		{
			/* close the report data if something goes wrong and log the error */
			reportData.close();
			KOALogHelper.logErrorCode(
				"ReportServlet",
				ErrorConstants.ERR_GENERATE_REPORT,
				e);
			/* set the error message and redirect to the error jsp */
			req.setAttribute(
				"ERROR",
				"Er heeft zich een probleem voorgedaan bij het ophalen van het rapport: "
					+ e.getMessage());
			redirect(
				req,
				res,
				props.getProperty(
					"ie.ucd.srg.logica.eplatform.error.DefaultErrorPage"));
		}
	}
	/**
	 * doPost method of the Servlet
	 * Gets the Report from the Command that's saved in the Session and generateds it's report.
	 * The report will be send to the screen.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, java.io.IOException
	{
		doGet(req, res);
	}
}
