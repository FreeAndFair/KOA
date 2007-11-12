
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>

<%@page import="ie.ucd.srg.koa.voorzitter.command.*,ie.ucd.srg.koa.constants.*"%>

<script language="javascript">

function open_child(p_link, p_name)
{
	child_handle=window.open('about:blank', p_name, 'menubar=yes,location=no,scrollbars=yes,resizable=yes,height=550,width=816,statusbar=yes,screenX=100,screenY=100,top=100,left=100');
	
	if(child_handle) child_handle.close();
	
	child_handle=window.open(p_link, p_name, 'menubar=yes,location=no,scrollbars=yes,resizable=yes,height=550,width=816,statusbar=yes,screenX=100,screenY=100,top=100,left=100');

	window.location.reload ();
}
</script>
<%
	ie.ucd.srg.koa.voorzitter.command.SelectProcesVerbaalCommand xCommand = (SelectProcesVerbaalCommand) request.getAttribute(CommandConstants.COMMAND_IN_REQUEST_KEY);
	
	String sDutchCurrentState = "Niet bekend";
	if (xCommand != null)
	{
		sDutchCurrentState = SystemState.getDutchTranslationForState(xCommand.getCurrentState());
	}
%>
<HEAD>
<META http-equiv="Pragma" content="no-cache"/>
<META http-equiv="Expires" content="-1"/>
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>KOA Remote Voting - Voorzitter - Select logbestand</TITLE>
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
                <td width="3%" align="left" height="380"><img src="images/blueline_3.gif" width="1" height="380"></td>
                <td width="94%" valign="top" height="360">
                <table width="100%" height="100%">
                	<tr><td>Welcome to the 'selection logbestand' page of the Internet and telephone voting offices</td></tr>
                	<tr><td>The current system state is: <b><%= sDutchCurrentState %></b></td></tr>
                	<tr><td>Make a choice from the options below:</td></tr>
                	<tr>
                	  <td valign="top">
                		<ul>
		                	<li><a href="javascript:open_child('SelectProcesVerbaal?selection=STATE_CHANGES', '')">Select status modification</A>                               </li>
                			<li><a href="javascript:open_child('SelectProcesVerbaal?selection=DATA_MANAGEMENT', '')">Select data management</A></li>
                			<li><a href="javascript:open_child('SelectProcesVerbaal?selection=VOTER_EVENTS', '')">Selectie kiezers meldingen</A></li>
                			<li><a href="javascript:open_child('SelectProcesVerbaal?selection=OTHER', '')">Selectie overig</A></li>
                			<li><a href="javascript:open_child('SelectProcesVerbaal?selection=COMPLETE', '')">Alle categori&euml;n</A></li>
                		</ul>
                      </td>
                      </tr>
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
