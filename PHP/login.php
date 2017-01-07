<?php

$host="mysql.hostinger.in"; 
$username="u857646345_saj12"; 
$pass_word="DIJDIJ123"; 
$db_name="u857646345_vers"; 

$con=mysqli_connect("$host", "$username", "$pass_word","$db_name")or die("cannot connect");

      if ($_SERVER['REQUEST_METHOD'] == 'POST' ) {
		$user = $_POST['user'];
		$pass = $_POST['pass'];


		if ($user == '' || $pass == '') {
			echo "Please fill all values";
		}
		else{
			$sql = "SELECT * from Details where user_name = '$user' and password = '$pass' ";
			$result = $con->query($sql);

			if($result->num_rows == 1){
			   echo "success";
			}else{
				echo "Invalid Credentials!";
			}
		}

            }		
	
?>