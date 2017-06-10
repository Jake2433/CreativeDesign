<?php
$con=mysqli_connect("127.0.0.1","root","autoset","chickenrun");

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


$result = mysqli_query($con,"insert into user_list (id,pwd, name, birth, sex, phone_number) values ('$userid','$userpw', '$username', '$userbirth', '$usersex', '$userphone')");

  if($result){
    echo 'success';
  }
  else{
    echo 'failure';
  }

mysqli_close($con);
?>
