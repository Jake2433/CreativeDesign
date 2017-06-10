<?php
$con=mysqli_connect("127.0.0.1","root","autoset","chickenrun");

mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}


$result = mysqli_query($con,"SELECT Date_format(Datetime, '%H:%i') as Time, light, temperature, air, humid FROM sensorValue WHERE Date(Datetime) = CURDATE() ORDER BY Datetime DESC");

$return_arr = array();

while ($row = mysqli_fetch_array($result, MYSQL_ASSOC)) {
    $row_array['time'] = $row['Time'];
    $row_array['light'] = $row['light'];
    $row_array['temperature'] = $row['temperature'];
    $row_array['air'] = $row['air'];
    $row_array['humid'] = $row['humid'];

    array_push($return_arr,$row_array);
}

echo json_encode($return_arr);
/*
$return_arr = array();

$fetch = mysql_query("SELECT * FROM table");

while ($row = mysql_fetch_array($fetch, MYSQL_ASSOC)) {
    $row_array['id'] = $row['id'];
    $row_array['col1'] = $row['col1'];
    $row_array['col2'] = $row['col2'];

    array_push($return_arr,$row_array);
}

echo json_encode($return_arr);
*/
/*
  $return_arr = array();

    if($result){
      while ($row = mysqli_fetch_array ($result, MYSQL_ASSOC)) {
          $sensorVal = array('light'=>$row['light'],'temperature'=>$row['temperature'], 'air'=>$row['air'], 'humid'=>$row['humid']);
      }
      echo json_encode($sensorVal);
    }
    else{
      echo 'failure';
    }
*/
mysqli_close($con);
?>
