
<form name="myform" enctype='multipart/form-data' method="post" action="update.php">
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#000066" width="95%" cellpadding="5">
<tr>
<td colspan="3" bgcolor="#B5CBEF" height="17" width="100%" bordercolor="#FFFFFF" background="tile_back.gif">
<p align="left\><b><font face="Verdana" size="2" color="#FFFFFF"><img border="0" src="nav_m.gif" width="8" height="8">
<b><font face="Verdana" size="2" color="#FFFFFF">Choose a topic to edit</font></b></td>
</tr><tr><td colspan="3" bgcolor="#B5CBEF" height="16" width="100%" bordercolor="#FFFFFF" background="tile_sub.gif"><font size="2" face="Verdana"><b><font face="Verdana" size="2" color="#000066">
<!- You can add a brief form description here-->
&nbsp;


<font face="Verdana">
<select name='choice'>
<?php 
	mysql_connect('csserver.ucd.ie','s01bf213','eamonnlawler');
	mysql_select_db(voting) or die('Incorrect username or password!'); 
	$result = mysql_query("SELECT idNum, topic FROM topic");
	while ($row = mysql_fetch_array($result))
	{ 
	$id = $row['idNum']; 
	$topics = $row['topic']; 
	echo "<option value='$id'>$topics</option>"; 
	}
	mysql_free_result($result);
mysql_close();
?> 
</select> 
</font>


</font></b>
<input type="submit" value="Next">
</td></tr>