<?php
  //require_once("connection_db.php");
class Mantenimiento{

    //************************** METODO PARA GUARDAR CATEGORIAS **************************//

    public static function guardar_categorias($id, $nombre, $estado){
        include("connection_db.php");
        $query = "INSERT INTO  tb_categorias (id_categoria, nom_categoria, estado_categoria)
                                VALUES (?, ?, ?)";
        try{    
          $link=conexion();    
          $comando = $link->prepare($query);
          $comando->execute(array($id, $nombre, $estado));
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
    

    
    //************************** METODO PARA ELIMINAR CATEGORIA **************************//
    public static function eliminar_categoria($id_categoria){
      include("connection_db.php");
      $query = "delete from tb_categorias where id_categoria=?";
      try{
          $link=conexion();
          $comando=$link->prepare($query);
          $comando->execute(array($id_categoria));
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
  
  
  
  //************************** METODO PARA ACTUALIZAR CATEGORIA **************************//
  public static function actualizar_categorias($id_categoria, $nom_categoria, $estado_categoria){
    include("connection_db.php");
    $query = "UPDATE tb_categorias SET nom_categoria=?, estado_categoria=? WHERE id_categoria=?";

    try {    
      $link=conexion();    
      $comando = $link->prepare($query);
      $comando->execute(array($nom_categoria, $estado_categoria, $id_categoria));
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



    //************************** METODO PARA SELECCIONAR CATEGORIA POR CODIGO **************************//
    public static function getCategoria($id_categoria) {
        include("connection_db.php");
        $query = "SELECT id_categoria,nom_categoria,estado_categoria from tb_categorias where id_categoria = ?";
    try {    
          $link=conexion();    
          $comando = $link->prepare($query);
          $comando->execute(array($id_categoria));
          $row = $comando->fetch(PDO::FETCH_ASSOC);
          return $row;

        } catch (PDOException $e) {
            // Aqui puedes clasificar el error dependiendo de la excepcion
            // para presentarlo en la respuesta Json
            return -1;
        }
  }
  
  
    //************************** METODO PARA SELECCIONAR CATEGORIA POR DESCRIPCION **************************//
  public static function getCategoriasDescripcion($desc) {
        include("connection_db.php");
        $query = "SELECT id_categoria,nom_categoria,estado_categoria from tb_categorias where nom_categoria = ?";
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
  
  
    //************************** METODO PARA SELECCIONAR TODO LO DE CATEGORIA **************************//
   public static function getAllCategoria() {
        include("connection_db.php");
        
        $query = "SELECT id_categoria,nom_categoria,estado_categoria FROM tb_categorias";

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
                                            
                     $array [] = array('id_categoria' => $result['id_categoria'], 'nom_categoria' => $result['nom_categoria'], 'estado_categoria' => $result['estado_categoria']);
                    
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