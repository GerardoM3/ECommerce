<?php

    include("connection_db.php");

    $query = "SELECT id, nombre, apellidos, correo, usuario, clave, tipo, estado, pregunta, respuesta, fecha_registro FROM tb_usuarios";

        try {
            $link=conexion();    
            $comando = $link->prepare($query);
            // Ejecutar sentencia preparada
            $comando->execute();
            
            $user = array(); 
           
           while ($temp = $comando->fetch(PDO::FETCH_ASSOC)) {
                $temp['id'];
                $temp['nombre'];
                $temp['apellidos'];
                $temp['correo'];
                $temp['usuario'];
                $temp['clave'];
                $temp['tipo'];
                $temp['estado'];
                $temp['pregunta'];
                $temp['respuesta'];
                $temp['fecha_registro'];

                
                array_push($user, $temp);
		
    			$datos[] = array_map("utf8_encode", $temp);
      	        header('Content-type: application/json; charset=utf-8');
             }
             
             echo json_encode($datos, JSON_UNESCAPED_UNICODE);
           
        
        } catch (PDOException $e) {
            return false;
        }

?>