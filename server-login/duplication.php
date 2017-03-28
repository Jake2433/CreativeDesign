<?php

    $hostname_localhost ="localhost";
    $database_localhost ="android_test";
    $username_localhost ="root";
    $password_localhost ="autoset";
    $localhost = @mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
    or
    trigger_error(mysql_error(),E_USER_ERROR);

    mysql_select_db($database_localhost, $localhost);

    $newId = $_POST['Id'];
    $query_search = "select * from custom_info where u_id = '".$newId."'";
    $query_exec = mysql_query($query_search) or die(mysql_error());
    $rows = mysql_num_rows($query_exec);

    if($rows == 0) {
        echo "No Such User Found";
    }
    else  {
        echo "User Found";
    }
?>
