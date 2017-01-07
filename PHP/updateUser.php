<?php
error_reporting(E_ALL ^ E_DEPRECATED);
$host="mysql.hostinger.in"; 
$username="u857646345_saj12"; 
$password="DIJDIJ123"; 
$db_name="u857646345_vers"; //replace with database name mad_urname
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect");
mysql_select_db("$db_name")or die("cannot select DB");
$name=$_REQUEST['USER'];
$nation=$_REQUEST['nation'];
$age=$_REQUEST['age'];
$sex=$_REQUEST['sex'];
$gpa=$_REQUEST['gpa'];


$sql="UPDATE Details set user_name='$name',nationality='$nation', age='$age' , sex='$sex',gpa='$gpa' where user_name = '$name'";


$result = mysql_query($sql);
mysql_close($con);


?>
