
<form name="myform" enctype='multipart/form-data' method="post" action="update.php">
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#000066" width="95%" cellpadding="5">
<tr>
<td colspan="3" bgcolor="#B5CBEF" height="17" width="100%" bordercolor="#FFFFFF" background="tile_back.gif">
<p align="left\><b><font face="Verdana" size="2" color="#FFFFFF"><img border="0" src="nav_m.gif" width="8" height="8">
<b><font face="Verdana" size="2" color="#FFFFFF">Edit a topic</font></b></td>
</tr><tr><td colspan="3" bgcolor="#B5CBEF" height="16" width="100%" bordercolor="#FFFFFF" background="tile_sub.gif"><font size="2" face="Verdana"><b><font face="Verdana" size="2" color="#000066">
<!- You can add a brief form description here-->
&nbsp;


<font face="Verdana">
<select name='choice'>
<?php 


foreach (glob("*.jpg") as $x) {
unlink($x);
}


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
<input type="submit" value="Load values">

</td></tr>
</table></form>

<!--------------------------------------NEXT FORM------------------------------------>

	<?php  

	$choice = $_POST['choice'];
	mysql_connect('csserver.ucd.ie','s01bf213','eamonnlawler');
	mysql_select_db(voting) or die('Incorrect username or password!'); 

	if(isset($choice))
	{	
	$query = "SELECT topic,image,sms,smsnum,stday,stmonth,styear,expday,expmonth,expyear FROM topic WHERE idNum=$choice";
	$ansquery = "SELECT topicAnswers, ansimage FROM topicAnswers WHERE topicId=$choice"; 
	$name = "temp".$choice.".jpg";
	}else{
	$query = "SELECT topic,image,sms,smsnum,stday,stmonth,styear,expday,expmonth,expyear FROM topic WHERE idNum=1";
	$ansquery = "SELECT topicAnswers, ansimage FROM topicAnswers WHERE topicId=1"; 
	$name = "temp1.jpg";
	}
	$result = mysql_query($query);
	$numt = mysql_num_rows($result);
	$row = mysql_fetch_array($result);
	$topic = $row["topic"];
	$sms = $row["sms"];
	$smsnum = $row["smsnum"];
	$stday = $row["stday"];
	$stmonth = $row["stmonth"];
	$styear = $row["styear"];
	$expday = $row["expday"];
	$expmonth = $row["expmonth"];
	$expyear = $row["expyear"];

	$img = $row["image"];
	
	$file_handle = fopen($name,"wb");
      fwrite($file_handle, $img, strlen($img));
	fclose($file_handle); 
	
	$ansresult = mysql_query($ansquery);
	$numa = mysql_num_rows($ansresult);
	$cnt=1;

	while($row = mysql_fetch_array($ansresult))
	{
		$ansstring = $row["topicAnswers"];
		if($cnt==1) {$ans1 = $ansstring; $ran1=rand(1,1000); $fname = "ans".$ran1.".jpg";}
		if($cnt==2) {$ans2 = $ansstring; $ran2=rand(1,1000); $fname = "ans".$ran2.".jpg";}
		if($cnt==3) {$ans3 = $ansstring; $ran3=rand(1,1000); $fname = "ans".$ran3.".jpg";}
		if($cnt==4) {$ans4 = $ansstring; $ran4=rand(1,1000); $fname = "ans".$ran4.".jpg";}
		if($cnt==5) {$ans5 = $ansstring; $ran5=rand(1,1000); $fname = "ans".$ran5.".jpg";}


		$ansimg = $row["ansimage"];

		$file_handle2 = fopen($fname,"wb");
      	fwrite($file_handle2, $ansimg, strlen($ansimg));
		fclose($file_handle2); 

		$cnt++;
	}
	mysql_free_result($ansresult);
	mysql_free_result($result);
	mysql_close();

	?>

<form name="form" enctype='multipart/form-data' method="post" action="uploadform.php">
<?php
	$choice = $_POST['choice'];

?>
<input type="hidden" id="chice" name="chice" value=<?php echo $choice; ?>>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#000066" width="95%" cellpadding="5">
<tr><td colspan="3" bgcolor="#D6DFEF" height="16" width="100%" bordercolor="#FFFFFF"><font size="1" face="Verdana">Please fill in all fields marked with a *</font></td></tr><tr><td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<img border="0" src="bc_new.gif" width="28" height="28"></td><td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana" size="2">Enter topic</td>
<td height="30" width="469" bgcolor="#EFF3F7" bordercolor="#FFFFFF">

<font face="Verdana"><input type=text name='topic' value="<?php echo $topic; ?>">*</td></tr><tr><td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<img border="0" src="bc_new.gif" width="28" height="28"></td><td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF">

<font face="Verdana" size="2">Image (optional) </td>
<td height="30" width="469" bgcolor="#EFF3F7" bordercolor="#FFFFFF">

<font face="Verdana"><img src=<?php echo $name?> width="35" height="35"> <input type="hidden" name="MAX_FILE_SIZE" value="2000000"> <input name="userfile" type="file" id="userfile">  

</td></tr><tr><td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<img border="0" src="bc_new.gif" width="28" height="28"></td><td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF">

<font face="Verdana" size="2">Start Date</td>
<td height="30" width="469" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana"><select name='stday'> <option value = <?php echo $stday; ?>><?php echo $stday;?><option value='1'>1<option value='2'>2<option value='3'>3<option value='4'>4<option value='5'>5<option value='6'>6<option value='7'>7<option value='8'>8<option value='9'>9<option value='10'>10<option value='11'>11<option value='12'>12<option value='13'>13<option value='14'>14<option value='15'>15<option value='16'>16<option value='17'>17<option value='18'>18<option value='19'>19<option value='20'>20<option value='21'>21<option value='22'>22<option value='23'>23<option value='24'>24<option value='25'>25<option value='26'>26<option value='27'>27<option value='28'>28<option value='29'>29<option value='30'>30<option value='31'>31</select>
<select name='stmonth'><option value = <?php echo $stmonth; ?>><?php echo $stmonth;?><option value='1'>1<option value='2'>2<option value='3'>3<option value='4'>4<option value='5'>5<option value='6'>6<option value='7'>7<option value='8'>8<option value='9'>9<option value='10'>10<option value='11'>11<option value='12'>12</select>
<select name='styear'><option value = <?php echo $styear; ?>><?php echo $styear;?><option value='2006'>2006<option value='2007'>2007<option value='2008'>2008<option value='2009'>2009<option value='2010'>2010</select>
</td></tr><tr><td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<img border="0" src="bc_new.gif" width="28" height="28"></td><td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF">

<font face="Verdana" size="2">Expiration Date</font></td>
<td height="30" width="469" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana"><select name='endday'><option value = <?php echo $expday; ?>><?php echo $expday;?><option value='1'>1<option value='2'>2<option value='3'>3<option value='4'>4<option value='5'>5<option value='6'>6<option value='7'>7<option value='8'>8<option value='9'>9<option value='10'>10<option value='11'>11<option value='12'>12<option value='13'>13<option value='14'>14<option value='15'>15<option value='16'>16<option value='17'>17<option value='18'>18<option value='19'>19<option value='20'>20<option value='21'>21<option value='22'>22<option value='23'>23<option value='24'>24<option value='25'>25<option value='26'>26<option value='27'>27<option value='28'>28<option value='29'>29<option value='30'>30<option value='31'>31</select>
<select name='endmonth'><option value = <?php echo $expmonth; ?>><?php echo $expmonth;?><option value='1'>1<option value='2'>2<option value='3'>3<option value='4'>4<option value='5'>5<option value='6'>6<option value='7'>7<option value='8'>8<option value='9'>9<option value='10'>10<option value='11'>11<option value='12'>12</select>
<select name='endyr'><option value = <?php echo $expyear; ?>><?php echo $expyear;?><option value='2006'>2006<option value='2007'>2007<option value='2008'>2008<option value='2009'>2009<option value='2010'>2010</select>
</td></tr><tr><td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<img border="0" src="bc_new.gif" width="28" height="28"></td><td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF">

<font face="Verdana" size="2">SMS vote</td>
<td height="30" width="469" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana"><select name='sms'><option value = <?php echo $sms; ?>><?php if($sms ==0){echo "No";}else{echo "Yes";}?><option value='0'>No<option value='1'>Yes</select></td></tr><tr><td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<img border="0" src="bc_new.gif" width="28" height="28"></td><td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF">

<font face="Verdana" size="2">SMS number</td>
<td height="30" width="469" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana"><input type=text name='smsnum' id='smsnum' value="<?php echo $smsnum; ?>"></td></tr><tr><td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<img border="0" src="bc_new.gif" width="28" height="28"></td><td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF">


<font face="Verdana" size="2">Number of choices</td>
<td height="30" width="469" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana"><select name='num'><option value = <?php echo $numa; ?>><?php echo $numa;?><option value='1'>1<option value='2'>2<option value='3'>3<option value='4'>4<option value='5'>5</select></td></tr><tr><td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<img border="0" src="bc_new.gif" width="28" height="28"></td>
<td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana" size="2">Choice 1</td>
<td height="30" width="469" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana"><input type=text name='ans1' value="<?php echo $ans1; ?>"> <img src=<?php echo "ans".$ran1.".jpg"; ?> width="35" height="35"> <input name="ans1file" type="file" id="ans1file"></td></tr><tr><td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<img border="0" src="bc_new.gif" width="28" height="28"></td><td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana" size="2">Choice 2</td>
<td height="30" width="469" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana"><input type=text name='ans2' value="<?php echo $ans2; ?>"> <img src=<?php echo "ans".$ran2.".jpg"; ?> width="35" height="35"> <input name="ans2file" type="file" id="ans2file"></td></tr><tr><td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<img border="0" src="bc_new.gif" width="28" height="28"></td><td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana" size="2">Choice 3</td>
<td height="30" width="469" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana"><input type=text name='ans3' value="<?php echo $ans3; ?>"> <img src=<?php echo "ans".$ran3.".jpg"; ?> width="35" height="35"> <input name="ans3file" type="file" id="ans3file"></td></tr><tr><td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<img border="0" src="bc_new.gif" width="28" height="28"></td><td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana" size="2">Choice 4</td>
<td height="30" width="469" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana"><input type=text name='ans4' value="<?php echo $ans4; ?>"> <img src=<?php echo "ans".$ran4.".jpg"; ?> width="35" height="35"> <input name="ans4file" type="file" id="ans4file"></td></tr><tr><td height="30" width="55" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<img border="0" src="bc_new.gif" width="28" height="28"></td><td height="30" width="189" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana" size="2">Choice 5</td>
<td height="30" width="469" bgcolor="#EFF3F7" bordercolor="#FFFFFF">
<font face="Verdana"><input type=text name='ans5' value="<?php echo $ans5; ?>"> <img src=<?php echo "ans".$ran5.".jpg"; ?> width="35" height="35"> <input name="ans5file" type="file" id="ans5file"></td></tr><tr><td colspan="3" bgcolor="#B5CBEF" height="25" width="737" background="tile_sub.gif"><p align="center"><font face="Verdana" size="2"><input type=submit value='Submit Form'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=reset value='Reset Form'></font></td></tr>
</table></form></body>
