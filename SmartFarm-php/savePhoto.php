<?php

    $con=mysqli_connect("127.0.0.1","root","autoset","chickenrun");

    mysqli_set_charset($con,"utf8");

    if (mysqli_connect_errno($con))
    {
       echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }

    if (isset($_FILES['myFile'])) {
          // Example:
        move_uploaded_file($_FILES['myFile']['tmp_name'], "uploads/" . $_FILES['myFile']['name']);

        $result = mysqli_query($con,"INSERT INTO webcamResName(name) VALUES ('".$_FILES['myFile']['name']."')");

        if($result){
          echo 'success';
        }
        else{
          echo 'failure';
        }
    }


    mysqli_close($con);
?>
