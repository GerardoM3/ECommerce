<?php
include('manto_usuarios.php');
//require 'main.class.php'

if (isset($_POST['id_usuario']) && ($_POST['nom_usuario']) && ($_POST['apellido_usuarios']) && ($_POST['correo'])) {
    
    $id_usuario = $_POST['id_usuario'];
    $nombre_usuario = $_POST['nom_usuario'];
    $apellidos_usuario = $_POST['apellido_usuarios'];
    $correo = $_POST['correo'];
    $usuario = $_POST['usuario'];
    $clave = $_POST['clave'];
    $tipo_usuario = $_POST['tipo'];
    $estado_usuario = $_POST['estado'];
    $pregunta = $_POST['pregunta'];
    $respuesta = $_POST['respuesta'];
    
    $resultado = manto_Users::guardar_usuario($id_usuario, $nombre_usuario, $apellidos_usuario, $correo, $usuario, $clave, $tipo_usuario, $estado_usuario, $pregunta, $respuesta);
    
    if($resultado==1){
        header('Content-type: application/json; charset=utf-8');
        $json_string = json_encode(array("estado" => 1, "mensaje" => "Registro guardado correctamente."));
        echo $json_string;
    }else{
        header('Content-type: application/json; charset=utf-8');
        $json_string = json_encode(array("estado" => 2, "mensaje" => "Error. No se puede guardar."));
        echo $json_string;
    }
}else{
        header('Content-type: application/json; charset=utf-8');
        $json_string = json_encode(array("estado" => 3, "mensaje" => "No se ha enviado toda la informacion."));
        echo $json_string;
}
?>