<?php
 error_reporting(E_ALL ^ E_DEPRECATED);
$host="mysql.hostinger.in"; 
$username="u857646345_saj12"; 
$pass_word="DIJDIJ123"; 
$db_name="u857646345_vers";
$con=mysqli_connect("$host", "$username", "$pass_word","$db_name")or die("cannot connect");

    $user=$_REQUEST['user'];
    $pass=$_REQUEST['pass'];
    $nationality=$_REQUEST['nationality'];
    $age=$_REQUEST['age'];
    $sex=$_REQUEST['sex'];
    $gpa=$_REQUEST['gpa'];
	  if($username == '' || $pass == ''){
	 	echo 'please fill all values';
	 }else{
	 	$sql = "SELECT * FROM Details WHERE user_name='$user'";
                $result = $con->query($sql);
	 if($result->num_rows!=0){
	      echo 'username already exists';
	 }else{ 
	 	$sql = "INSERT INTO Details (user_name,password,nationality,age,sex,gpa) VALUES('$user','$pass','$nationality','$age','$sex','$gpa')";
	 if(mysqli_query($con,$sql)){
	 	echo 'successfully registered';
	 }else{
	 	echo 'oops! Please try again!';
	 }
	 }
	 mysqli_close($con);
 }