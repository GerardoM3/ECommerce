<?php
include('manto_productos.php');

if (isset($_POST["id_producto"]) && ($_POST["nom_producto"]) && ($_POST["des_producto"]) && ($_POST["stock"]) && ($_POST["precio"]) && ($_POST["unidad_medida"]) && ($_POST["estado_producto"]) && ($_POST["categoria"])){

 	$id_producto = $_POST['id_producto'];
 	$nom_producto = $_POST['nom_producto'];
 	$des_producto = $_POST['des_producto'];
 	$stock = $_POST['stock'];
 	$precio = $_POST['precio'];
 	$unidad_medida = $_POST['unidad_medida'];
 	$estado_producto = $_POST['estado_producto'];
 	$categoria = $_POST['categoria'];

	$resultado = Mantenimiento::actualizar_productos($id_producto, $nom_producto, $des_producto, $stock, $precio, $unidad_medida, $estado_producto,  $categoria);
	
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