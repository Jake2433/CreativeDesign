<?php
$con=mysqli_connect("127.0.0.1","root","autoset","chickenrun");

mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$dName = $_POST['eventSensor'];
$dVal = $_POST['DesirVal'];

  $result = mysqli_query($con,"UPDATE sensorgoal SET $dName = $dVal");

  if($result){
    echo 'success';
  }
  else{
    echo 'failure';
  }

mysqli_close($con);
?>
