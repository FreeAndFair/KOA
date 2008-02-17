<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Pragma" content="no-cache"/>
<META http-equiv="Expires" content="-1"/>
<TITLE>Kiezen op afstand - Data Managment Functionality</TITLE>
<LINK href="DatabeheerWeb.css" rel="stylesheet" type="text/css">
</HEAD>

<%@page import="ie.ucd.srg.koa.databeheer.command.*,java.util.*" %>


<%
	Upload command = 
		(Upload) request.getAttribute(CommandConstants.COMMAND_IN_REQUEST_KEY);

	Map xMap = command.getResult();	
%>


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
                <td width="94%" valign="top" height="360">
                	<TABLE border="0" width="100%" height="100%">
                	  <TR>
                			<% String sContentType = (String)xMap.get(CommandConstants.UPLOAD_CONTENTTYPE); %>
                			<TD>Type bestand:</TD>
                			<TD><%=sContentType%></TD>
                		</TR>

                		<TR>
                			<TD>Action:</TD>
                			<TD><%=(String)xMap.get(CommandConstants.UPLOAD_ACTION)%></TD>
                		</TR>
                		
                		<TR>
                			<TD>Reference Number:</TD>
                			<TD><%=(String)xMap.get(CommandConstants.UPLOAD_REQUEST_REFERENCE)%></TD>
                		</TR>

                		<TR>
                			<TD>
                			Date and time of processing:<%=(String)xMap.get(CommandConstants.UPLOAD_CREATIONTIME)%>
                			</TD>
                		</TR>
                		
                		<TR>
                		<% if(sContentType != null)
                			{
	                			if(sContentType.equalsIgnoreCase(CommandConstants.UPLOAP_CONTENTTYPE_VALUE_KIESLIJST))
	                			{%>
		                			<TD>
		                			Number of kieskringen:<%=(String)xMap.get(CommandConstants.UPLOAD_KIESKRING_COUNT)%><BR>
		                			Number of districten:<%=(String)xMap.get(CommandConstants.UPLOAD_DISTRICT_COUNT)%><BR>
		                			Number of kieslijsten:<%=(String)xMap.get(CommandConstants.UPLOAD_KIESLIJST_COUNT)%><BR>
		                			Number of Candidates:<%=(String)xMap.get(CommandConstants.UPLOAD_POSITIE_COUNT)%>
		                			</TD>
							   <%}
							     else
								 {%>	
	                			    <TD>
	                					Number of voters:<%=(String)xMap.get(CommandConstants.UPLOAD_KIEZER_COUNT)%>
	                				</TD>
	                		   <%}
	                		 }%>
	                		  
                		</TR>
                		
                		<TR>
                			<TD>Do you want this file to be processed?</TD>
                		</TR>
                		<TR>
                			<TD>
                				<FORM action="/KOADatabeheerWeb/ProcessUpload" method="POST">
	     	    					<INPUT type="hidden" name="process" value="JA">
 	  	        					<INPUT type="submit" name="Submit" value="Yes">
  	  	      					</FORM>
                			</TD>
                			<TD>
                				<FORM action="/KOADatabeheerWeb/ProcessUpload" method="POST">
	     	    					<INPUT type="hidden" name="process" value="NEE">
					 	  	        <INPUT type="submit" name="Submit" value="No">
  	  						     </FORM>
  	  						</TD>
                		</TR>
                	</TABLE>
                </TD>
                <td width="3%" align="right" height="380"><img src="images/blueline_3.gif" width="1" height="380"></td>
            </TR>
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
            
        </TABLE>
</BODY>
<HEAD>
<META http-equiv="Pragma" content="no-cache"/>
<META http-equiv="Expires" content="-1"/>
</HEAD>
</HTML>
