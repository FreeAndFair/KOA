<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>

<%@page import="ie.ucd.srg.koa.voorzitter.command.*,ie.ucd.srg.koa.constants.*"%>



<%
	ie.ucd.srg.koa.voorzitter.command.FetchDataCommand xCommand = (FetchDataCommand) request.getAttribute(CommandConstants.COMMAND_IN_REQUEST_KEY);
	
	String sDutchCurrentState = "Niet bekend";
	if (xCommand != null)
	{
		sDutchCurrentState = SystemState.getDutchTranslationForState(xCommand.getCurrentState());	
	}
%>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Pragma" content="no-cache"/>
<META http-equiv="Expires" content="-1"/>
<META http-equiv="Refresh" content="60;URL=Index" />
<TITLE>KOA Remote Voting - Voorzitter - Fetch Data</TITLE>
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
							<strong>European Parliament Election 2004</strong></font>
						</div>
					</td>
				</tr>
				</table>
			</div></td>  
			</tr>
            <tr valign="top">
                <td width="3%" align="left" height="380"><img src="images/blueline_3.gif" width="1" height="380"></td>
                <td width="94%" valign="top" height="360">
                <table width="100%" height="100%">
                	<tr><td>Welcome to the 'fetch data' page of the Internet and telephone voting offices.</td></tr>
                	<tr><td>The current system status is: <b><%= sDutchCurrentState %></b></td></tr>
                	<%
        				if( (xCommand == null) ||
        				    (xCommand.getCurrentState().equals(SystemState.CLOSED) 	                          == false	&& 
        					 xCommand.getCurrentState().equals(SystemState.READY_TO_COUNT)                    == false	&&
        					 xCommand != null && xCommand.getCurrentState().equals(SystemState.VOTES_COUNTED) == false) ) 
        				{
        			%>
                           <tr><td>In the current state there is no data available to be imported.</td></tr>
        			<%
        			    } else {
        			%>
	                	<tr><td>Make a choice from the options below:</td></tr>
	                	<tr>
	                	  <td valign="top">
	                		<ul>
	                			<% 
	                				if(xCommand != null && xCommand.getCurrentState().equals(SystemState.VOTES_COUNTED))
	                				{
	                			%>
	                			    <!-- Or 22.3.632 save xml document under a specific name, not standard Report -->
	                				<li><a href="Report?report=uitslag_export">export the result of the poll to file</A></li>
	                				<li><a href="Report?report=transactiecodes">export the operation codes to file</A></li>
	                			<%  } %>
	                			<%
		                			//OR22.3.604 Exporteren versleutelde stembus
	                				if( xCommand != null && xCommand.getCurrentState().equals(SystemState.CLOSED) 			|| 
	                					xCommand != null && xCommand.getCurrentState().equals(SystemState.READY_TO_COUNT) 	||
	                					xCommand != null && xCommand.getCurrentState().equals(SystemState.VOTES_COUNTED) 		) 
	                				{
	                			%>
	                				<!-- Or 22.3.632 save xml document under a specific name, not standard Report -->
	                 				<li><a href="Report?report=versleutelde_stembus_export">export file versleutelde poll</A></li>
	                			<%	} %>
	                		</ul>
	                      </td>
	                    </tr>
	                <%  } %>
                    <tr>
                      <td align="left">
                        <BUTTON STYLE="width:200" ONCLICK="window.location='Index'">Terug naar statusoverzicht</BUTTON>
                      </td>
                    </tr>
                </table>
		      </td>
		      <td width="3%" align="right" height="380"><img src="images/blueline_3.gif" width="1" height="380"></td>
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
</BODY>
<HEAD>
<META http-equiv="Pragma" content="no-cache" />
<META http-equiv="Expires" content="-1" />
</HEAD>
</HTML>
