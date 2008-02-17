

<?php
 
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
	window.location.href = "http://csserver.ucd.ie/~s02bf067/web/add_topic/upload.html";

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

	$num_topic=0;
	$result = mysql_query("SELECT idNum FROM topic");
	while($row = mysql_fetch_array($result))
	{
		$id = $row["idNum"];
		if ($id > $num_topic)
		{
		  $num_topic=$id;
		}
	}
	$num_topic++;
      mysql_free_result($result);

	$num_ans=0;
	$result2 = mysql_query("SELECT idNum FROM topicAnswers");
	while($row = mysql_fetch_array($result2))
	{
		$id = $row["idNum"];
		if ($id > $num_ans)
		{
		  $num_ans=$id;
		}
	}
	$num_ans++;
       mysql_free_result($result2);

	 
        
       $query = "INSERT INTO topic VALUES ('$num_topic','$topic','$content','$sms','$smsnum','$stday','$stmonth','$styear','$endday','$endmonth','$endyr')"; 
       mysql_query($query) or die('Error, query failed');

	 $arr = array($ans1,$ans2,$ans3,$ans4,$ans5);

      	$fileName1 = $_FILES['ans1file']['name'];
		$fileName2 = $_FILES['ans2file']['name']; 
	 	$fileName3 = $_FILES['ans3file']['name']; 
	 	$fileName4 = $_FILES['ans4file']['name']; 
	 	$fileName5 = $_FILES['ans5file']['name']; 
      	$tmpName1  = $_FILES['ans1file']['tmp_name']; 
		$tmpName2  = $_FILES['ans2file']['tmp_name']; 
		$tmpName3  = $_FILES['ans3file']['tmp_name']; 
		$tmpName4  = $_FILES['ans4file']['tmp_name']; 
		$tmpName5  = $_FILES['ans5file']['tmp_name']; 
      	$fileSize1 = $_FILES['ans1file']['size']; 
		$fileSize2 = $_FILES['ans2file']['size']; 
		$fileSize3 = $_FILES['ans3file']['size']; 
		$fileSize4 = $_FILES['ans4file']['size']; 
		$fileSize5 = $_FILES['ans5file']['size']; 
      	$fileType1 = $_FILES['ans1file']['type'];
      	$fileType2 = $_FILES['ans2file']['type'];
      	$fileType3 = $_FILES['ans3file']['type'];
      	$fileType4 = $_FILES['ans4file']['type'];
      	$fileType5 = $_FILES['ans5file']['type'];

	 $filenamearr = array($fileName1,$fileName2,$fileName3,$fileName4,$fileName5);
	 $tmpnamearr = array($tmpName1,$tmpName2,$tmpName3,$tmpName4,$tmpName5);
	 $filesizearr = array($fileSize1,$fileSize2,$fileSize3,$fileSize4,$fileSize5);
	 $filetypearr = array($fileType1,$fileType2,$fileType3,$fileType4,$fileType5);


	 for ($i=0; $i<$num; $i++)
	 {


      		 $fileName = $filenamearr[$i]; 
      		 $tmpName  = $tmpnamearr[$i];
      		 $fileSize = $filesizearr[$i]; 
      		 $fileType = $filetypearr[$i];
			
	 if($fileSize != 0)
	 	{  
      			 $fp = fopen($tmpName, 'r'); 
       		$content2 = fread($fp, $fileSize); 
       		$content2 = addslashes($content2); 
       		fclose($fp);
       		if(!get_magic_quotes_gpc()) { 
           		 $fileName = addslashes($fileName); 
      			 } 
		}

		$ans = $arr[$i];
	 	$query = "INSERT INTO topicAnswers VALUES ('$ans','$num_ans','$num_topic',0,'$content2')"; 
       	mysql_query($query) or die('Error, query failed');
		$num_ans++;
	 }                     

?>
      <script type="text/javascript">
	alert("Topic entry successful!");
	window.location.href = "http://csserver.ucd.ie/~s02bf067/web/inline.htm";

	</script>