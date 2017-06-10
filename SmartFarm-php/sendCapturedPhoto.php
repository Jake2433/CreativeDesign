<?php
    // set isOk to 1

    $handle = fopen("isOkToReceivePhoto.txt", "w"); //파일을 열음 또는 생성
    fwrite($handle, "1"); //파일에 내용 입력
    fclose($handle); //열었던 파일을 종료


    $con=mysqli_connect("127.0.0.1","root","autoset","chickenrun");

    mysqli_set_charset($con,"utf8");

    if (mysqli_connect_errno($con))
    {
       echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }

    $result = mysqli_query($con,"SELECT name FROM webcamResName ORDER BY name DESC LIMIT 1");

    if($result){
      // Fetch one row
      $row=mysqli_fetch_row($result);

      // send image data
      $im = file_get_contents("uploads/" .$row[0]);
      $imdata = base64_encode($im);
      echo $imdata;

      // Free result set
      //mysqli_free_result($result);
    }
    else{
      echo 'failure';
    }

    mysqli_close($con);

?>
