<?php

    $hostname_localhost ="localhost";
    $database_localhost ="chickenrun";
    $username_localhost ="root";
    $password_localhost ="autoset";
    $localhost = @mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
    or
    trigger_error(mysql_error(),E_USER_ERROR);

    mysql_select_db($database_localhost, $localhost);

    $username = $_POST['username'];
    $password = $_POST['password'];
    $query_search = "select * from user_list where id = '".$username."' AND pwd = '".$password. "'";
    $query_exec = mysql_query($query_search) or die(mysql_error());
    $rows = mysql_num_rows($query_exec);

    if($rows == 0) {
        echo "No Such User Found";
    }
    else  {
        echo "User Found";
    }

?>
