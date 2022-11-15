<?php
include('manto_categorias.php');
$id = $_POST['id'];
$nombre = $_POST["nombre"];
$estado = $_POST["estado"];

if (($id!="") and
    ($nombre!="") and 
    ($estado!="")) {
     	
    $resultado = Mantenimiento::guardar_categorias($id, $nombre, $estado);

	if ($resultado==1) {
        header('Content-type: application/json; charset=utf-8');
        $json_string = json_encode(array("estado" => 1,"mensaje" => "Registro guardado correctamente."));
        echo $json_string;
        //echo "Registro guardado...";
    } else {
        header('Content-type: application/json; charset=utf-8');
        $json_string = json_encode(array("estado" => 2,"mensaje" => "No se pudo guardar nada."));
		echo $json_string;
    }
}
?>