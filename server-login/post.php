<?php
$con=mysqli_connect("127.0.0.1","root","autoset","android_test");

mysqli_set_charset($con,"utf8");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$userid = $_POST['Id'];
$userpw = $_POST['Pw'];
$username = $_POST['Name'];
$userbirth = $_POST['Birth'];
$usersex = $_POST['Sex'];
$userphone = $_POST['Phone'];


$result = mysqli_query($con,"insert into custom_info (u_id,u_pw, u_name, u_birth, u_sex, u_phone) values ('$userid','$userpw', '$username', '$userbirth', '$usersex', '$userphone')");

  if($result){
    echo 'success';
  }
  else{
    echo 'failure';
  }

mysqli_close($con);
?>
