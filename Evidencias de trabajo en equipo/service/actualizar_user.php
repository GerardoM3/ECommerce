<?php
include('manto_usuarios.php');

if (isset($_POST["id"]) && ($_POST["nombre"]) && ($_POST["apellidos"]) && ($_POST["correo"]) && ($_POST["usuario"]) && ($_POST["clave"]) && ($_POST["tipo"]) && ($_POST["estado"]) && ($_POST["pregunta"]) && ($_POST["respuesta"])){

 	$id = $_POST['id'];
 	$nombre = $_POST['nombre'];
 	$apellidos = $_POST['apellidos'];
 	$correo = $_POST['correo'];
 	$usuario = $_POST['usuario'];
 	$clave = $_POST['clave'];
 	$tipo = $_POST['tipo'];
 	$estado = $_POST['estado'];
 	$pregunta = $_POST['pregunta'];
 	$respuesta = $_POST['respuesta'];


	$resultado = manto_Users::actualizar_usuarios($id, $nombre, $apellidos, $correo, $usuario, $clave, $tipo, $estado, $pregunta, $respuesta);
	
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