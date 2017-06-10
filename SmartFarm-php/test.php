<?php
/*
$handle = fopen("isOkToReceivePhoto.txt", "w"); //파일을 열음 또는 생성
fwrite($handle, '1'); //파일에 내용 입력
fclose($handle); //열었던 파일을 종료
*/
/*
$handle = fopen("isOkToReceivePhoto.txt", "r");
$isOkToReceive = fread($handle, 1);
fclose($handle); //열었던 파일을 종료
echo $isOkToReceive;
*/
$handle = fopen("isOkToReceivePhoto.txt", "w"); //파일을 열음 또는 생성
fwrite($handle, "1"); //파일에 내용 입력
fclose($handle); //열었던 파일을 종료
 ?>
