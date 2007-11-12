<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>

<%@page import="ie.ucd.srg.koa.voorzitter.command.*,ie.ucd.srg.koa.constants.*"%>

<%
	ie.ucd.srg.koa.voorzitter.command.SelectProcesVerbaalCommand xCommand = (SelectProcesVerbaalCommand) request.getAttribute(CommandConstants.COMMAND_IN_REQUEST_KEY);
%>

<HEAD>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Pragma" content="no-cache"/>
<META http-equiv="Expires" content="-1"/>
<TITLE>KOA Remote Voting - Voorzitter - Select logbestand</TITLE>
<LINK href="VoorzitterWeb.css" rel="stylesheet" type="text/css">
</HEAD>



<BODY>
        <table width="725" border="0" align="center" cellpadding="0" cellspacing="0" valign="middle">
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
                <td width="3%" align="left" height="380"><img src="images/blueline_3.gif" width="1" height="380"></td>
                 <td width="94%" valign="top" height="380">
                 	<form action="Report" method="POST">
                 	<input type="hidden" name="report" value="proces_verbaal">
                 	<input type="hidden" name="selection" value="<%=xCommand.getSelection()%>">
                	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			            <TR>
			            	<TD colspan="4"><H1>Select logbestand</H1></td>
			            </tr>
			            <TR>
			            	<TD colspan="4">Select the period for which the reports from the logbestand must be shown:</td>
			            </tr>
			            <tr>
				            <td width="158">From date</td>   
			                <td width="12">&nbsp;</td>
			                <td width="330"><input type="text" name="periode_start" size="10" maxlength="10"> format: (dd-mm-jjjj)</td>
			                <td width="330"><input type="text" name="periode_start_time" size="5" maxlength="5" value="00:00"> format: (uu:mm)</td>
			            </TR>
			            <tr>
				            <td>Up to and including date</td>   
			                <td>&nbsp;</td>
			                <td><input type="text" name="periode_end" size="10" maxlength="10"> format: (dd-mm-jjjj)</td>
			                <td><input type="text" name="periode_end_time" size="5" maxlength="5" value="23:59"> formaat: (uu:mm)</td>
			            </TR>
			            <tr>
			                <td>&nbsp;</td>
			                <td>&nbsp;</td>
			                <td>&nbsp;</td>			                
			                <td align="right">
			                  <input type="submit" name="Toon rapport" value="Toon rapport">
			                </td>
			            </TR>
			        </table>
			        </form>
		       </td>
		       <td width="3%" align="right" height="380"><img src="images/blueline_3.gif" width="1" height="380"></td>
		    </tr>
		    <tr valign="top">
                <td colspan="3" height="20"><img src="images/horizontalline_2.gif" width="726" height="1"></td>
            </tr>
            <tr valign="top">
                <td colspan="3">
                <div align="center">
                <H2>The responsibility for this site rests at the Ministry of the Interior and Kingdom Relations</H2>
                </div>
                </td>
            </tr>
        </table>
</BODY>
<HEAD>
<META http-equiv="Pragma" content="no-cache"/>
<META http-equiv="Expires" content="-1"/>
</HEAD>
</HTML>
