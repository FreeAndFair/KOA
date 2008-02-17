<?php
include("global.inc.php");
$errors=0;
$error="The following errors occured while processing your form input.<ul>";
pt_register('POST','Entertopic');
$Image=$HTTP_POST_FILES['Image'];
pt_register('POST','StartDate');
pt_register('POST','ExpirationDate');
pt_register('POST','SMSvote');
pt_register('POST','SMSnumber');
pt_register('POST','Numberofchoices');
pt_register('POST','Choice1');
pt_register('POST','Choice2');
pt_register('POST','Choice3');
pt_register('POST','Choice4');
pt_register('POST','Choice5');
if($Entertopic=="" ){
$errors=1;
$error.="<li>You did not enter one or more of the required fields. Please go back and try again.";
}
if($HTTP_POST_FILES['Image']['tmp_name']==""){ }
 else if(!is_uploaded_file($HTTP_POST_FILES['Image']['tmp_name'])){
$error.="<li>The file, ".$HTTP_POST_FILES['Image']['name'].", was not uploaded!";
$errors=1;
}
if($errors==1) echo $error;
else{
$image_part = date("h_i_s")."_".$HTTP_POST_FILES['Image']['name'];
$image_list[1] = $image_part;
copy($HTTP_POST_FILES['Image']['tmp_name'], "files/".$image_part);
$where_form_is="http".($HTTP_SERVER_VARS["HTTPS"]=="on"?"s":"")."://".$SERVER_NAME.strrev(strstr(strrev($PHP_SELF),"/"));
$message="Enter topic: ".$Entertopic."
Image: ".$where_form_is."files/".$image_list[1]."
Start Date: ".$StartDate."
Expiration Date: ".$ExpirationDate."
SMS vote: ".$SMSvote."
SMS number: ".$SMSnumber."
Number of choices: ".$Numberofchoices."
Choice 1: ".$Choice1."
Choice 2: ".$Choice2."
Choice 3: ".$Choice3."
Choice 4: ".$Choice4."
Choice 5: ".$Choice5."
";
$message = stripslashes($message);
mail("l1am@hotmail.com","Form Submitted at your website",$message,"From: phpFormGenerator");
$link = mysql_connect("csserver.ucd.ie","s01bf213","eamonnlawler");
mysql_select_db("voting",$link);
$query="insert into phpformtest (idNum,topic,stday,endday,sms,smsnum,x,xx,xxx,xxxx,yyyy,yyy) values ('".$Entertopic."','".$where_form_is."files/".$image_list[1]."','".$StartDate."','".$ExpirationDate."','".$SMSvote."','".$SMSnumber."','".$Numberofchoices."','".$Choice1."','".$Choice2."','".$Choice3."','".$Choice4."','".$Choice5."')";
mysql_query($query);

header("Refresh: 0;url=http://csserver.ucd.ie/~s02bf067/web");
?><?php 
}
?>