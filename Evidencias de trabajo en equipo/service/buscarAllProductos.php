<?php

    include("connection_db.php");

    $query = "SELECT id_producto,nom_producto,des_producto,stock,precio,unidad_medida,estado_producto,categoria,fecha_entrada FROM tb_productos";

        try {
            $link=conexion();    
            $comando = $link->prepare($query);
            // Ejecutar sentencia preparada
            $comando->execute();
            
            $productos = array(); 
           
           while ($temp = $comando->fetch(PDO::FETCH_ASSOC)) {
                $temp['id_producto'];
                $temp['nom_producto'];
                $temp['des_producto'];
                $temp['stock'];
                $temp['precio'];
                $temp['unidad_medida'];
                $temp['estado_producto'];
                $temp['categoria'];
                $temp['fecha_entrada'];
                
                array_push($productos, $temp);
		
    			$datos[] = array_map("utf8_encode", $temp);
      	        header('Content-type: application/json; charset=utf-8');
             }
             
             echo json_encode($datos, JSON_UNESCAPED_UNICODE);
           
        
        } catch (PDOException $e) {
            return false;
        }

?>