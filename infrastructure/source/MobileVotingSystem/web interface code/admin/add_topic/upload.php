<html> 
<head> 
<title>Insert new entry to MySQL Database</title> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"> 
<style type="text/css"> 
<!-- 
.box { 
    font-family: Arial, Helvetica, sans-serif; 
    font-size: 12px; 
    border: 1px solid #000000; 
} 
--> 
</style> 
</head> 

<body> 

<?php
 
if(isset($_POST['upload'])) 
{ 

	$user = $_POST['username'];
	$pass = $_POST['password'];
	$topic = $_POST['topic'];
	$sms = $_POST['sms'];
	$smsnum = $_POST['smsnum'];
	$stday = $_POST['stday'];
	$stmonth = $_POST['stmonth'];
	$styear = $_POST['styear'];
	$endday = $_POST['endday'];
	$endmonth = $_POST['endmonth'];
	$endyr = $_POST['endyr'];
	$num = $_POST['num'];
	$ans1 = $_POST['ans1'];
	$ans2 = $_POST['ans2'];
	$ans3 = $_POST['ans3'];
	$ans4 = $_POST['ans4'];
	$ans5 = $_POST['ans5'];

	//CONNECT TO THE DATABASE
	mysql_connect('csserver.ucd.ie',$user,$pass);

	//SELECT THE DATABASE
	mysql_select_db(voting) or die('Incorrect username or password!'); 

       $fileName = $_FILES['userfile']['name']; 
       $tmpName  = $_FILES['userfile']['tmp_name']; 
       $fileSize = $_FILES['userfile']['size']; 
       $fileType = $_FILES['userfile']['type'];   
       $fp = fopen($tmpName, 'r'); 
       $content = fread($fp, $fileSize); 
       $content = addslashes($content); 
       fclose($fp);
       if(!get_magic_quotes_gpc()) { 
            $fileName = addslashes($fileName); 
       } 
	 
	 // Count the number of current topics (for IDs)
	 $count = mysql_query("SELECT * FROM topic");
	 $num_topic = mysql_num_rows($count);
	 $num_topic++;

	 // Count the number of current answers (for IDs)
	 $count = mysql_query("SELECT * FROM topicAnswers");
	 $num_ans = mysql_num_rows($count);
	 $num_ans++;
        
       $query = "INSERT INTO topic VALUES ('$num_topic','$topic','$content','$sms','$smsnum','$stday','$stmonth','$styear','$endday','$endmonth','$endyr')"; 
       mysql_query($query) or die('Error, query failed');

	 $arr = array($ans1,$ans2,$ans3,$ans4,$ans5);
	 //foreach ($arr as $value) { echo "Value: " . $value . "<br />"; } 
	 

	 for ($i=0; $i<$num; $i++)
	 {
		$ans = $arr[$i];
	 	$query = "INSERT INTO topicAnswers VALUES ('$ans','$num_ans','$num_topic',0)"; 
       	mysql_query($query) or die('Error, query failed');
		$num_ans++;
	 }                     
       mysql_free_result($count);

       echo "<br>Entry successful! <br>"; 
}         
?> 
<form action="" method="post" enctype="multipart/form-data" name="uploadform" class="box"> 
 <table id="pg_b695b49aa1" border="0" cellpadding="2" cellspacing="1" width="600" bgcolor="#2C648B" class="" style="display:;border: solid 1px #777777;"> 
	
	<tr> <input name="username" type="text"> <input name="password" type="password"> Please enter SQL username and password </tr>  
	<tr> <input name="topic" type="text"> Please enter the topic string. </tr> 
	<tr> <input name="sms" type="text"> SMS vote? 1 or 0 </tr>
	<tr> <input name="smsnum" type="text"> SMS number. </tr> 
	<tr> <input name="stday" type="text"> <input name="stmonth" type="text"> <input name="styear" type="text"> Start date (dd/mm/yyyy)</tr>
	<tr> <input name="endday" type="text"> <input name="endmonth" type="text"> <input name="endyr" type="text"> Expiration date (dd/mm/yyyy)</tr>
      <tr><input type="hidden" name="MAX_FILE_SIZE" value="2000000"> <input name="userfile" type="file" id="userfile"> Choose image.</tr>

	<br> Please select possible answers for topic <br>
	<tr> <input name="num" type="text"> How many answers? (max 5)<tr>	
	<tr> <input name="ans1" type="text"> Choice 1...</tr>	
	<tr> <input name="ans2" type="text"> Choice 2...</tr>	
	<tr> <input name="ans3" type="text"> Choice 3...</tr>	
	<tr> <input name="ans4" type="text"> Choice 4...</tr>	
	<tr> <input name="ans5" type="text"> Choice 5...</tr>	

	<br>
      <tr><input name="upload" type="submit" id="upload" value=" Upload "> Upload information! </tr> 
  </table> 
</form> 
</body> 
</html>