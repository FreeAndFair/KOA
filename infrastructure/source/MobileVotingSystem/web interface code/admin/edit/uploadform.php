

<?php
 
	$choice  = $_POST['chice'];
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

	if($topic=="" ){ ?>
	      <script type="text/javascript">
	alert("Error: Please fill in the required field");
	window.location.href = "http://csserver.ucd.ie/~s02bf067/web/admin/edit/update.php";

	</script>
		<?php
	}

	//CONNECT TO THE DATABASE
	mysql_connect('csserver.ucd.ie','s01bf213','eamonnlawler');

	//SELECT THE DATABASE
	mysql_select_db(voting) or die('Incorrect username or password!'); 

       $fileName = $_FILES['userfile']['name']; 
       $tmpName  = $_FILES['userfile']['tmp_name']; 
       $fileSize = $_FILES['userfile']['size']; 
       $fileType = $_FILES['userfile']['type'];
	 if($fileSize != 0)
	 {  
       $fp = fopen($tmpName, 'r'); 
       $content = fread($fp, $fileSize); 
       $content = addslashes($content); 
       fclose($fp);
       if(!get_magic_quotes_gpc()) { 
            $fileName = addslashes($fileName); 
       } 
	}

	 $queryi ="SELECT image FROM topic WHERE idNum=$choice";
	 $resulti = mysql_query($queryi);
	$row = mysql_fetch_array($resulti);
	$img = $row["image"];

	 // if new image specified
	 if($fileSize!=0)
	 {
		  mysql_query("DELETE FROM topic WHERE idNum=$choice");
		$query = "INSERT INTO topic VALUES ('$choice','$topic','$content','$sms','$smsnum','$stday','$stmonth','$styear','$endday','$endmonth','$endyr')"; 
       	mysql_query($query) or die('Error, query failed');
	 }
	 else if(strlen($img) !=0)
	 {
       	 $query = "UPDATE topic SET topic='$topic',sms='$sms',smsnum='$smsnum',stday='$stday',stmonth='$stmonth',styear='$styear',expday='$endday',expmonth='$endmonth',expyear='$endyr' WHERE idNum=$choice";
       	 mysql_query($query) or die('Error, query failed');
	 }
	 else
	 {
		  mysql_query("DELETE FROM topic WHERE idNum=$choice");
		$query = "INSERT INTO topic VALUES ('$choice','$topic','$content','$sms','$smsnum','$stday','$stmonth','$styear','$endday','$endmonth','$endyr')"; 
       	mysql_query($query) or die('Error, query failed');
	 }                     

?>
      <script type="text/javascript">
	alert("Topic edit successful!");
	window.location.href = "http://csserver.ucd.ie/~s02bf067/web/inline.htm";

	</script>