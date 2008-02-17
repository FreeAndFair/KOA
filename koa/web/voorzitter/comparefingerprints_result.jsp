<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>

<%@page import="ie.ucd.srg.koa.voorzitter.command.*,ie.ucd.srg.koa.constants.*"%>


<%
	CompareKieslijstFingerprints xCommand = (CompareKieslijstFingerprints) request.getAttribute(CommandConstants.COMMAND_IN_REQUEST_KEY);

	ie.ucd.srg.koa.dataobjects.KiesLijstFingerprintCompareResult xResult = xCommand.getCompareResult();
%>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Pragma" content="no-cache"/>
<META http-equiv="Expires" content="-1"/>
<TITLE>KOA Remote Voting - Voorzitter - Hoofdmenu</TITLE>
<LINK href="VoorzitterWeb.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
        <table width="725" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
            	<td colspan="3" background="images/banner_filler.gif"><div align="left">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="76" height="57">
							<div align="left"><IMG src="images/leeuw.gif" width="76" height="57" border="0"></div>
						</td>
						<td width="640" height="57">
							<div align="center"><font color="#FFFFFF" size="4" face="Arial, Helvetica, sans-serif">
								<strong>European Parliament Elections 2004</strong></font>
							</div>
						</td>
					</tr>
					</table>
				</div></td>  
            </tr>
            <tr valign="top">
                <td width="3%" align="left" height="380"></td>
                <td width="94%" valign="top">
                <br>                
                <table width="100%">
                	<tr><td><strong>Result of fingerprint comparison</strong></td></tr>
                	<tr><td>&nbsp;</td></tr>                	
                	<tr><td>The candidate data and the associated candidate codes have been checked and currently still correspond with the imported values.</td></tr>
                	<tr><td>The results can be seen in the table below:</td></tr>
                	<tr><td valign="top">
                		<ul>
                			<li>
                			<%
                				if (xResult.getKieslijstFPEqual())
                				{
							%>
							The data for political parties is identical to the values read in.
         					<%       				
                				}
                				else
                				{
							%>
							<H3>There is a deviation observed in the data for political parties compared with the values read in.</H3>
         					<%       				
                				}
                				
                			%>
                			</li>
                			<li>
                			<%
                				if (xResult.getLijstpositiesFPEqual())
                				{
							%>
							The list positions are identical to the values read in.
         					<%       				
                				}
                				else
                				{
							%>
							<H3>There are deviations observed in the list positions compared with the values read in.</H3>
         					<%       				
                				}
                				
                			%>
                			</li>
                			<li>
                			<%
                				if (xResult.getKandidaatCodeFPEqual())
                				{
							%>
							The candidate codes are identical to those generated during the import.
         					<%       				
                				}
                				else
                				{
							%>
							<H3>here are deviations observed in the candidate codes compared with the generated values.</H3>
         					<%       				
                				}
                				
                			%>
                			</li>
                		</ul>
                		</td>
                		</tr>
				</table>
				<br>
                <table width="100%">
                	<tr><td colspan="5"><strong>Fingerprints</strong></td></tr>
                	<tr><td colspan="5">&nbsp;</td></tr>                	
                	<tr><td colspan="5">The following fingerprints have been made:</td></tr>
                	<tr><td colspan="5">&nbsp;</td></tr>
                	<tr>
                		<td valign="top" width="20%"><strong>Table</strong></td>
                		<td valign="top" width="5%"></td>
                		<td valign="top" width="35%"><strong>Value at to read in</strong></td>
						<td valign="top" width="5%"></td>
						<td valign="top" width="35%"><strong>Current value</strong></td>                		
                	</tr>
                	<tr>
                		<td valign="top" width="20%">Groupings</td>
                		<td valign="top" width="5%"></td>
                		<td valign="top" width="35%"><%= xResult.getKieslijstDatabaseFP() %></td>
						<td valign="top" width="5%"></td>
						<td valign="top" width="35%"><%= xResult.getKieslijstCurrentFP() %></td>                		
                	</tr>
                	<tr>
                		<td valign="top" width="20%">list positions</td>
                		<td valign="top" width="5%"></td>
                		<td valign="top" width="35%"><%= xResult.getLijstpositiesDatabaseFP() %></td>
						<td valign="top" width="5%"></td>
						<td valign="top" width="35%"><%= xResult.getLijstpositiesCurrentFP() %></td>                		
                	</tr>
                	<tr>
                		<td valign="top" width="20%">candidate codes</td>
                		<td valign="top" width="5%"></td>
                		<td valign="top" width="35%"><%= xResult.getKandidaatcodesDatabaseFP() %></td>
						<td valign="top" width="5%"></td>
						<td valign="top" width="35%"><%= xResult.getKandidaatcodesCurrentFP() %></td>                		
                	</tr>
                	<tr><td colspan="5">&nbsp;</td></tr>
			        <tr>
			          <td colspan="5" align="left"><BUTTON STYLE="width:200" ONCLICK="window.location='Index'">Terug naar statusoverzicht</BUTTON>
			          </td>
			        </tr>
				</table>
				</td>
				<td width="3%" align="right" height="380"></td>
			</tr>
			<tr valign="top">
                <td colspan="3" height="10"><img src="images/horizontalline_2.gif" width="726" height="1"></td>
            </tr>
            <tr valign="top">
                <td colspan="3">
                <div align="center">
                <H2>The responsibility for this site rests at the Ministry of the Interior and Kingdom Relations</H2>
                </div>
                </td>
            </tr>
		</table>

	<%@ include file="refreshFooter.jsp" %>
</BODY>
<HEAD>
<META http-equiv="Pragma" content="no-cache" />
<META http-equiv="Expires" content="-1" />
</HEAD>
</HTML>
