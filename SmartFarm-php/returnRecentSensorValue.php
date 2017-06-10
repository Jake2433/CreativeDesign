<?php
// set isOk to 0
$handle = fopen("isOkToReceivePhoto.txt", "w"); //파일을 열음 또는 생성
fwrite($handle, "0"); //파일에 내용 입력
fclose($handle); //열었던 파일을 종료


$con=mysqli_connect("127.0.0.1","root","autoset","chickenrun");

mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$result = mysqli_query($con,"SELECT * FROM sensorValue ORDER BY Datetime DESC limit 1");

    if($result){
      while ($row = mysqli_fetch_array ($result, MYSQL_ASSOC)) {
          $sensorVal = array('light'=>$row['light'],'temperature'=>$row['temperature'], 'air'=>$row['air'], 'humid'=>$row['humid']);
      }
      echo json_encode($sensorVal);
    }
    else{
      echo 'failure';
    }

mysqli_close($con);
?>
