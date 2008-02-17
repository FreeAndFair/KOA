<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<%@page import="ie.ucd.srg.koa.controller.client.ClientManager"%>
<%@page import="ie.ucd.srg.koa.constants.SystemState"%>
<%@page import="ie.ucd.srg.koa.constants.ComponentType"%>
<%@page import="ie.ucd.srg.koa.constants.FunctionalProps"%>
<%@page session="false" %>
<%
	response.setHeader("Pragma", "no-cache"); //http1.0
	response.setHeader("Cache-Control", "no-cache"); //http1.1
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Pragma" content="no-cache"/>
<META http-equiv="Expires" content="-1"/>
<LINK href="KiezerWeb.css" rel="stylesheet" type="text/css">
<TITLE>European Parliament Elections 2004</TITLE>
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
               		<td width="640" height="57" bgcolor="#6699CC">
					<div align="center"><font color="#FFFFFF" size="4" face="Arial, Helvetica, sans-serif"><strong>European Parliament Elections 2004
                    </strong></font></div></td>
                </tr>
              </table></td>
            </tr>
            <tr valign="top">
                <td width="3%" align="left"><img src="images/blueline_3.gif" width="1" height="380"></td>
                <td width="94%" valign="top">
                <div align="center">&nbsp;
                <table width="100%" border="0" cellpadding="3">
                    <tr>
                    	<td>
                        <p align="left"><font size="2">Casting your vote goes in four steps:</font>
                  <ol>
                    <li><font size="2">First of all you are asked your voice code and access code to introduce.</font></li>
                    <li><font size="2">The second step exists from introducing of the candidate code which is mentioned at the candidate of your choice. It is necessary for the overview of lists of candidates (that to you it has been transmitted) for this at the hand keep.</font></li>
                    <li><font size="2">The third step exists from confirming of your choice. Your vote has been effectively cast just as soon as your affirmative has been received.</font></li>
                 <%
					String show_transactioncode = FunctionalProps.getProperty(FunctionalProps.SHOW_TRANSACTIONCODE);
					if( show_transactioncode != null &&
					    show_transactioncode.equalsIgnoreCase("NO") == true )
					{
                 %>
                    <li><font size="2">Finally you receive the communication that you have voted.</font></li>
                 <% }
                    else {
                 %>
                    <li><font size="2">Finally you receive an operation code.</font></li>
                 <% } %>
                        </ol>
                       </p>
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

                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        </td>
                    </tr>
                </table>
                <table width="100%" border="0" cellpadding="3">
                    <tr>
                      <td width="173" height="38"><a href="index.jsp"><img src="images/terug_2.gif" width="108" height="46" border="0" alt="druk hierop om terug te gaan naar de vorige pagina"></a></td>
                      <td height="38">&nbsp;</td>
<%
	/* Do not show "Verder" button if not open. */
	if (SystemState.OPEN.equals(sCurrentState)) 
	{
%>                
                        <td height="38">
                        <div align="right"><a href="identification.jsp"><img src="images/verder_3.gif" width="108" height="46" border="0" alt="druk hierop indien u wilt stemmen"></a></div>
                        </td>
<%
	}
%>                
                    </tr>
                </table>
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
</html>
