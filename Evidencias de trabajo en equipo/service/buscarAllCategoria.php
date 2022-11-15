<?php

    include("connection_db.php");

    $query = "SELECT id_categoria,nom_categoria,estado_categoria FROM tb_categorias";

        try {
            $link=conexion();    
            $comando = $link->prepare($query);
            // Ejecutar sentencia preparada
            $comando->execute();
            
            $categoria = array(); 
           
           while ($temp = $comando->fetch(PDO::FETCH_ASSOC)) {
                $temp['id_categoria'];
                $temp['nom_categoria'];
                $temp['estado_categoria'];
                
                array_push($categoria, $temp);
		
    			$datos[] = array_map("utf8_encode", $temp);
      	        header('Content-type: application/json; charset=utf-8');
             }
             
             echo json_encode($datos, JSON_UNESCAPED_UNICODE);
           
        
        } catch (PDOException $e) {
            return false;
        }

?>