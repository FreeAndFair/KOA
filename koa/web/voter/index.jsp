<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<%@page import="ie.ucd.srg.koa.controller.client.ClientManager"%>
<%@page import="ie.ucd.srg.koa.constants.SystemState"%>
<%@page import="ie.ucd.srg.koa.constants.ComponentType"%>
<%@page session="false" %>
<%
	response.setHeader("Pragma", "no-cache"); //http1.0
	response.setHeader("Cache-Control", "no-cache"); //http1.1
%>

<head>
<title>European Parliament Elections 2004</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK href="KiezerWeb.css" rel="stylesheet" type="text/css">
<META http-equiv="Pragma" content="no-cache"/>
<META http-equiv="Expires" content="-1"/>
</head>

<%
	String sCurrentState = ClientManager.getLocalState (ComponentType.WEB);
	String sElectionText = SystemState.getWebTextForState (sCurrentState);	
%>

<body>

        <table width="725" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                
          <td colspan="3"><div align="left">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <!-- <td width="85" bgcolor="#6699CC"><div align="left"><img src="images/eu_logo.gif" width="85" height="57"></div></td>
                 --> 
            <td width="640" height="57" bgcolor="#6699CC">
<div align="center"><font color="#FFFFFF" size="4" face="Arial, Helvetica, sans-serif"><strong>European Parliament Elections 2004
                </strong></font></div></td>
                </tr>
              </table>
            </div></td>
            </tr>
            <tr valign="top">
                <td width="3%" align="left"><img src="images/blueline_3.gif" width="1" height="380"></td>
                <td width="94%" valign="top">
                <div align="center">&nbsp;
                <div align="center">
                <center>
                <table width="100%" border="0" cellpadding="3">
                    <tr>
                        <td height="270">
                        <p align="left"><font size="2">Welcome at the Internet voice office for the election of the members of the European Parliament.
                          </font></p>
						<p align="left"><font size="2">The possibility of voting by means of Internet it has been reserved to:</font></p>
                        <ul>
                          <li><font size="2">Dutch who abroad to live</font></li>
                          <li><font size="2">In the Netherlands living molar beneficiaries that on the day of poll (10 June 2004) because of profession if activities stay abroad, as well as their there staying family members</font></li>
                        </ul>
 
                  		<p align="left"><font size="2">To be able vote by means of Internet, serves you register one to be able. See www.ukomttochook.nl for further information on the recording procedure their there staying family members.</font></p>
                                                                       
                        <p align="left"><font size="2">You can cast your vote up to Thursday 10 June 2004, 21.00 (Dutch time).</font></p>
<%	
	if (sElectionText != null) 
	{
%>
                        <p><b><%= sElectionText %></b></p>           
<%
    } else {
%>                        
                        <p><b>&nbsp;</b></p>           
<%
    } 
%>                        
                        <p></p>
                        </td>
                    </tr>
                </table>
                </center>
                </div>
<%
	if (SystemState.OPEN.equals(sCurrentState)) 
	{
%>
                <table width="100%" border="0" cellpadding="3">
                    <tr>
                        <td width="133"><img src="images/filler.gif" width="218" height="1"></td>
                        <td height="38">&nbsp;</td>
                        <td height="38">
                        <div align="right"><a href="uitleg.jsp"><img src="images/verder_3.gif" width="108" height="46" border="0" alt="Druk hierop om naar de uitleg te gaan"></a></div>
                        </td>
                    </tr>
                </table>
<%
	}
%>
                </div>

                </td>
                <td width="3%" align="right"><img src="images/blueline_3.gif" width="1" height="380"></td>
            </tr>
            <tr valign="top">
                <td height="1" colspan="3"><img src="images/horizontalline_2.gif" width="726" height="1"></td>
            </tr>
            <tr valign="top">
                <td colspan="3">
                <div align="center">
                <H2>The responsibility for this site rests at the Ministry of the Interior and Kingdom Relations</H2>
                </div>
                </td>
            </tr>
        </table>

</body>
<HEAD>
<META http-equiv="Pragma" content="no-cache"/>
<META http-equiv="Expires" content="-1"/>
</HEAD>
</HTML>
