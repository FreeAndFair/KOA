<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Pragma" content="no-cache"/>
<META http-equiv="Expires" content="-1"/>
<TITLE>Kiezen op afstand - Databeheer functionaliteit</TITLE>
<LINK href="DatabeheerWeb.css" rel="stylesheet" type="text/css">
</HEAD>

<%
	ie.ucd.srg.koa.databeheer.command.SelectUploadCommand command = 
				(ie.ucd.srg.koa.databeheer.command.SelectUploadCommand) request.getAttribute("COMMAND");
				
	String sMessage = command.getMessage();
%>

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
                <td width="3%" align="left" height="380"><img src="images/blueline_3.gif" width="1" height="380"></td>
                <td width="94%" valign="top" height="360">
					<FORM action="UploadKRReplace" method="POST" enctype="multipart/form-data">
					<TABLE border="0" align="center" cellpadding="0" cellspacing="0" height="100%" width="100%">
				    <TBODY>
				        <TR>
				            <TD><BR></TD>
				            <TD><BR></TD>
        				</TR>        

    				    <TR>
            				<TD>Location of the additional voter file</TD>
            				<TD><INPUT type="file" name="upload"></TD>
        				</TR>
 		               	<tr>
   		                 	<TD colspan="2"><h3><%= sMessage %></h3></TD>
     	               </tr>
        				<TR>
                         
                          <TD align="right">
                            <input type="submit" name="verwerk_lijst" value="Verwerk lijst" width="108" height="46" border="0" alt="druk op verder om het vervangende bestand te valideren">
                          </TD>
        				</TR>
    				</TBODY>
					</TABLE>
					</FORM>
				</TD>
				<td width="3%" align="right" height="380"><img src="images/blueline_3.gif" width="1" height="380"></td>
			</TR>		
			<TR>
                          <td align="left">
                            <BUTTON STYLE="width:200" ONCLICK="window.location='index.jsp'">Return to the Homepage</BUTTON>
                          </td>
                          
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

