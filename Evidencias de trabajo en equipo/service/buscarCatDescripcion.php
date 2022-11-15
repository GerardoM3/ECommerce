<?php
include('manto_categorias.php');

if (isset($_POST["nom_categoria"])){
 	$desc = $_POST['nom_categoria'];

	$resultado = Mantenimiento::getCategoriasDescripcion($desc);
	//echo $retorno;
	if ($resultado) {
			
		//PRUEBA FUNCIONAL.
		//echo "1";   //solo esta línea para funcionamiento actual.
		$datos = array();
		$datos[] = array_map("utf8_encode", $resultado);
  		header('Content-type: application/json; charset=utf-8');
		//$json_array = json_encode($datos);
		//echo $json_array;

    echo json_encode($datos, JSON_UNESCAPED_UNICODE);

	}else{ 
        //envio un 0 para decirle a android que no existe el email.
        echo "0";                 //solo esta línea para funcionamiento actual. 
    } 
}

?>