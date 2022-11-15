<?php

    class manto_Users{

            //************************** METODO PARA GUARDAR USER **************************//
        public static function guardar_usuario($a, $b, $c, $d, $e, $f, $g, $h, $i, $j){
            include("connection_db.php");
            $query = "INSERT INTO  tb_usuarios (id, nombre, apellidos, correo, usuario, clave, tipo, estado, pregunta, respuesta)
                                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try{    
              $link=conexion();    
              $comando = $link->prepare($query);
              $comando->execute(array($a, $b, $c, $d, $e, $f, $g, $h, $i, $j));
              $count = $comando->rowCount();
            
              if($count > 0){
                  return 1;
              }else{
                  return 0;
              }
            } catch (PDOException $e) {
                return -1;
            }                        
        }

    //************************** METODO PARA ELIMINAR USER **************************//
    public static function eliminar_user($id){
        include("connection_db.php");
        $query = "delete from tb_usuarios where id=?";
        try{
            $link=conexion();
            $comando=$link->prepare($query);
            $comando->execute(array($id));
            //return $comando;
            $count = $comando->rowCount(); 
            if($count>0){
                return 1;
            }else{
                return 0;   
            }
            
        }catch (PDOException $e){
            return -1;
        }
    }


    //************************** METODO PARA ACTUALIZAR USER **************************//
  public static function actualizar_usuarios($id, $nombre, $apellidos, $correo, $usuario, $clave, $tipo, $estado, $pregunta, $respuesta){
    include("connection_db.php");
    $query = "UPDATE tb_usuarios SET nombre=?, apellidos=?, correo=?, usuario=?, clave=?, tipo=?, estado=?, pregunta=?, respuesta=? WHERE id=?";

    try {    
      $link=conexion();    
      $comando = $link->prepare($query);
      $comando->execute(array($nombre, $apellidos, $correo, $usuario, $clave, $tipo, $estado, $pregunta, $respuesta, $id));
      //return $comando;
      $count = $comando->rowCount(); 
      if($count>0){
          return 1;
      }else{
          return 0;   
      }

    } catch (PDOException $e) {
        // Aqui puedes clasificar el error dependiendo de la excepcion
        // para presentarlo en la respuesta Json
        return -1;
    }
}


//************************** METODO PARA SELECCIONAR USER POR CODIGO **************************//
public static function getUserCodigo($id) {
    include("connection_db.php");
    $query = "SELECT id, nombre, apellidos, correo, usuario, clave, tipo, estado, pregunta, respuesta, fecha_registro from tb_usuarios where id = ?";
try {    
      $link=conexion();    
      $comando = $link->prepare($query);
      $comando->execute(array($id));
      $row = $comando->fetch(PDO::FETCH_ASSOC);
      return $row;

    } catch (PDOException $e) {
        // Aqui puedes clasificar el error dependiendo de la excepcion
        // para presentarlo en la respuesta Json
        return -1;
    }
}



 //************************** METODO PARA SELECCIONAR USER POR DESCRIPCION **************************//
 public static function getUserDescripcion($desc) {
    include("connection_db.php");
    $query = "SELECT id, nombre, apellidos, correo, usuario, clave, tipo, estado, pregunta, respuesta, fecha_registro from tb_usuarios where nombre = ?";
try {    
      $link=conexion();    
      $comando = $link->prepare($query);
      $comando->execute(array($desc));
      $row = $comando->fetch(PDO::FETCH_ASSOC);
      return $row;

    } catch (PDOException $e) {
        // Aqui puedes clasificar el error dependiendo de la excepcion
        // para presentarlo en la respuesta Json
        return -1;
    }
}



//************************** METODO PARA SELECCIONAR TODO LO DE USUARIOS **************************//
public static function getAllUser() {
    include("connection_db.php");
    
    $query = "SELECT id, nombre, apellidos, correo, usuario, clave, tipo, estado, pregunta, respuesta, fecha_registro FROM tb_usuarios";

    try {
        $link=conexion();    
        $comando = $link->prepare($query);
        // Ejecutar sentencia preparada
        $comando->execute();
        
        $rows_array = array();
        while($result = $comando->fetch(PDO::FETCH_ASSOC))
            {
                //$temp = array();
                //$temp['codigo'] = $result['codigo'];
                //$temp['descripcion'] = $result['descripcion'];
                //$temp['precio'] = $result['precio'];
                                        
                 $array [] = array('id' => $result['id'], 
                 'nombre' => $result['nombre'], 
                 'apellidos' => $result['apellidos'], 
                 'correo' => $result['correo'], 
                 'usuario' => $result['usuario'], 
                 'clave' => $result['clave'], 
                 'tipo' => $result['tipo'], 
                 'estado' => $result['estado'], 
                 'pregunta' => $result['pregunta'], 
                 'respuesta' => $result['respuesta'], 
                 'fecha_registro' => $result['fecha_registro']);
                
                /*
                $rows_array['codigo'] = $result['codigo'];
                $rows_array['descripcion'] = $result['descripcion'];
                $rows_array['precio'] = $result['precio'];
                */
            }
            
            array_map("utf8_encode", $array);
              header('Content-type: application/json; charset=utf-8');
              return print_r(json_encode($array), JSON_UNESCAPED_UNICODE);
              
              
              //json_encode($datos, JSON_UNESCAPED_UNICODE);
            //return (var_dump($array));
            //return print_r($array);
    } catch (PDOException $e) {
        return false;
    }
    
}
        
    }

?>