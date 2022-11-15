<?php
include('manto_categorias.php');

if (isset($_POST["id"]) && ($_POST["nombre"]) && ($_POST["estado"])){
	$id = $_POST['id'];
	$nombre = $_POST["nombre"];
	$estado = $_POST["estado"];
	
		$resultado = Mantenimiento::actualizar_categorias($id, $nombre, $estado);

	if ($resultado==1) {
        header('Content-type: application/json; charset=utf-8');
        $json_string = json_encode(array("estado" => 1,"mensaje" => "Actualizacion aplicada correctamente."));
        echo $json_string;
        
        //$json_string = json_encode(array('estado' => '1','mensaje' => 'Actualizacion aplicada correctamente.'));
        //echo json_encode($json_string, JSON_UNESCAPED_UNICODE);
		
    } else {
        header('Content-type: application/json; charset=utf-8');
        $json_string = json_encode(array("estado" => 2,"mensaje" => "No se ha modificado ningun dato."));
		echo $json_string;
    }
	 
}else{
	header('Content-type: application/json; charset=utf-8');
	$json_string = json_encode(array("estado" => 3, "mensaje" => "No se ha enviado toda la informacion."));
	echo $json_string;
}

?>