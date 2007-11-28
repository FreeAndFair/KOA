<?php

//SET SQL INFO
$username='s01bf213';
$password='eamonnlawler';
$database='voting';

//READ IN FROM THE FORM

//CONNECT TO THE DATABASE, localhost is used if your testing stuff on your own pc
mysql_connect('csserver.ucd.ie',$username,$password);

//SELECT THE DATABASE
@mysql_select_db($database) or die( "Unable to select database");


?>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#000066" width="95%" cellpadding="5">
<tr>
	<td colspan="3" bgcolor="#B5CBEF" height="17" width="100%" bordercolor="#FFFFFF" background="tile_back.gif"> <p align="left\><b><font face="Verdana" size="2" color="#FFFFFF"><img border="0" src="nav_m.gif" width="8" height="8">
		<font face='Verdana' size=2 color='#FFFFFF'><b> Vote statistics &nbsp; </font><font face="Verdana" size="2" color="#000066"> </font></b>
	</td>
</tr>

<tr>
	<td colspan="3" bgcolor="#B5CBEF" height="16" width="100%" bordercolor="#FFFFFF" background="tile_sub.gif"><font size="2" face="Verdana"><b><font face="Verdana" size="2" color="#000066">
	<!- You can add a brief form description here-->
	&nbsp;</font>
</tr>
<?php


$result = mysql_query("SELECT idNum, topic, image FROM topic");
$cnt = 1;
$acnt = 1;
while($row = mysql_fetch_array($result))
{

	$id = $row["idNum"];
	$topicstring = $row["topic"];
	$img = $row["image"];



	$file_handle = fopen("temp".$cnt.".jpg","wb");
      fwrite($file_handle, $img, strlen($img)); 
	
	?>
	<tr> 
	  <td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF"><img border="0" src="bc_new.gif" width="28" height="28"></td>	
 	  <td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF"><font color="blue"><?php echo "<strong>".$topicstring."</strong>" ?></td> 
	  <td height="30" width="450" bgcolor="#EFF3F7" bordercolor="#FFFFFF"><img src=<?php echo "temp".$cnt.".jpg"; ?> width="60" height="60">  </td>

      </tr>
      
	

	<?php
	$cnt++;
	$ans1result = mysql_query("SELECT topicAnswers, registerAns FROM topicAnswers WHERE topicId='$id'");
	$ansresult = mysql_query("SELECT topicAnswers, registerAns, ansimage FROM topicAnswers WHERE topicId='$id'");

	$totalreg = 0;
	while($ansarr = mysql_fetch_array($ans1result))
	{
		$totalreg += $ansarr["registerAns"];
	}

	while($ansrow = mysql_fetch_array($ansresult))
	{

		$aimg = $ansrow["ansimage"];
		$file_handle = fopen("tempans".$acnt.".jpg","wb");
      	fwrite($file_handle, $aimg, strlen($aimg));
		$tempcnt = $acnt;
		$acnt++;
		//echo $ansrow["topicAnswers"]." ".$ansrow["registerAns"]."<br />";
		$ansstring = $ansrow["topicAnswers"];
		$reg = $ansrow["registerAns"];
		$percent = 0;
		if($totalreg > 0) {
		  $percent = (int)(($reg / $totalreg) * 100);		
		}

		?>
	<tr> 
	  <td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF"><img src=<?php echo "tempans".$tempcnt.".jpg"; ?> width="40" height="40">  </td>	
 	  <td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF"><?php echo $ansstring ?></td> 
	  <td height="30" width="450" bgcolor="#EFF3F7" bordercolor="#FFFFFF"><?php echo $reg." votes (".$percent."%)" ?></td>
      </tr>

		<?php
	}
}

mysql_free_result($result);
mysql_free_result($ansresult);
mysql_free_result($ans1result);
?>
</table>
