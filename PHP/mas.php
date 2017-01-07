<?php
error_reporting(E_ALL ^ E_DEPRECATED);
$host="mysql.hostinger.in"; 
$username="u857646345_saj12"; 
$password="DIJDIJ123"; 
$db_name="u857646345_vers"; //replace with database name mad_urname
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect");
mysql_select_db("$db_name")or die("cannot select DB");

if(function_exists($_GET['method'])){
$_GET['method']();

}



function getUniList()
{
   $uni_sql=mysql_query("select id,name from uni_details ");
   $unis=array();
if(mysql_num_rows($uni_sql)){
   while($row =mysql_fetch_assoc($uni_sql))
{
       $unis['uni_List'][]=$row;
}
}
echo json_encode($unis);

} 


function getUniDetails()
{
   $uid=$_REQUEST['uid'];
   $uni_sql=mysql_query("select name,course,rank,fees from  uni_details where id='$uid' ");
   $unis=array();
  if(mysql_num_rows($uni_sql)){
   while($row =mysql_fetch_assoc($uni_sql))
{
       $unis['uni_details'][]=$row;
}
}
echo json_encode($unis);

} 




function getFacDetails()
{
   $uid=$_REQUEST['uid'];
   $uni_sql=mysql_query("select Accomodation,ComputerFacilities,Cafeteria,SportsCenter,HealthCare,ConvStore,Library,StudentLabs,ShuttleServices,CulinaryStudio,AirCraftSimulationLab,ResearchLab,MusicStudio,Mosque,FashionWorkshop from uni_facilities where id='$uid' ");
   $unis=array();
if(mysql_num_rows($uni_sql)){
   while($row =mysql_fetch_assoc($uni_sql))
{
       $unis['uni_facilities'][]=$row;
}
}
echo json_encode($unis);

} 






function getIntakeDetails()
{
   $uid=$_REQUEST['uid'];
   $uni_sql=mysql_query("select id,intakes from uni_intakes where id='$uid' ");
   $unis=array();
if(mysql_num_rows($uni_sql)){
   while($row =mysql_fetch_assoc($uni_sql))
{
       $unis['uni_Intakes'][]=$row;
}
}
echo json_encode($unis);

} 




function getResultDetails()
{
   $uid=$_REQUEST['uid'];
   $uni_sql=mysql_query("select * from uni_results where id='$uid' ");
   $unis=array();
if(mysql_num_rows($uni_sql)){
   while($row =mysql_fetch_assoc($uni_sql))
{
       $unis['uni_Results'][]=$row;
}
}
echo json_encode($unis);

} 


function getAllDetails()
{
   $uid=$_REQUEST['uid'];
   $uni_sql=mysql_query("select d.*,i.*,f.*,r.* from uni_results r,uni_details d,uni_facilities f,uni_intakes i where i.id=d.id and d.id=f.id and f.id=r.id and d.id='$uid' ");
   $unis=array();
if(mysql_num_rows($uni_sql)){
   while($row =mysql_fetch_assoc($uni_sql))
{
       $unis['uni_Full_Details'][]=$row;
}
}
echo json_encode($unis);

} 


?>