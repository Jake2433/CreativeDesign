<?php

    $handle = fopen("isOkToReceivePhoto.txt", "r");
    $isOkToReceive = fread($handle, 1);
    fclose($handle); //열었던 파일을 종료

    if($isOkToReceive == 1){
      echo 'OK';
    }
    else{
      echo 'notReady';
    }

?>
