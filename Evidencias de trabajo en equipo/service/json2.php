<?php
//echo "Trabajando con JSON en PHP";
    $json = '["HTTPCLIENT", "HTTPURLCONNECTION", "VOLLEY", "RETROFIT"]';
    $json1 = '["ANDROID", "IOS", "macOs", "chomeOS"]';

    $data = json_decode($json);

    echo $data[1];
    //echo $data;
?>