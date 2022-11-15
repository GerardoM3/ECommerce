<?php
header('Content-type: application/json; charset=utf-8');
$json_string = json_encode(array("idcategoria" => 1,"nombre" => "Tablet", "estadocategoria" => "1"));
echo $json_string;
?>