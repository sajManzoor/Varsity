<?php
error_reporting(E_ALL ^ E_DEPRECATED);
$host="mysql.hostinger.in"; 
$username="u857646345_saj12"; 
$password="DIJDIJ123"; 
$db_name="u857646345_vers"; //replace with database name mad_urname
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect");
mysql_select_db("$db_name")or die("cannot select DB");
$code=$_REQUEST['code'];
$sql = "select * from webometric_universities where country_id=(Select id from countries where iso3='$code')";
$result = mysql_query($sql);
$json = array();
if(mysql_num_rows($result)){
while($row=mysql_fetch_assoc($result)){
$json['countries'][]=$row;
}
}
mysql_close($con);
echo json_encode($json);

?>