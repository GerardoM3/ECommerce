<?php
  //require_once("connection_db.php");
class Mantenimiento{
    
    //************************** METODO PARA GUARDAR PRODUCTO **************************//
    public static function guardar_producto($a, $b, $c, $d, $e, $f, $g, $h){
        include("connection_db.php");
        $query = "INSERT INTO  tb_productos (id_producto, nom_producto, des_producto, stock, precio, unidad_medida, estado_producto, categoria)
                                VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try{    
          $link=conexion();    
          $comando = $link->prepare($query);
          $comando->execute(array($a, $b, $c, $d, $e, $f, $g, $h));
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
    
    //************************** METODO PARA ELIMINAR PRODUCTO **************************//
    public static function eliminar_producto($id_producto){
      include("connection_db.php");
      $query = "delete from tb_productos where id_producto=?";
      try{
          $link=conexion();
          $comando=$link->prepare($query);
          $comando->execute(array($id_producto));
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
  
  
  //************************** METODO PARA ACTUALIZAR PRODUCTO **************************//
  public static function actualizar_productos($id_producto, $nom_producto, $des_producto, $stock, $precio, $unidad_medida, $estado_producto, $categoria){
        include("connection_db.php");
        $query = "UPDATE tb_productos SET nom_producto=?, des_producto=?, stock=?, precio=?, unidad_medida=?, estado_producto=?, categoria=? WHERE id_producto =?";

        try {    
          $link=conexion();    
          $comando = $link->prepare($query);
          $comando->execute(array($nom_producto, $des_producto, $stock, $precio, $unidad_medida, $estado_producto, $categoria, $id_producto));
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
    
    
    
    //************************** METODO PARA SELECCIONAR PRODUCTOS POR CODIGO **************************//
    public static function getProductosCodigo($id_producto) {
        include("connection_db.php");
        $query = "SELECT id_producto,nom_producto,des_producto,stock,precio,unidad_medida,estado_producto,categoria,fecha_entrada from tb_productos where id_producto = ?";
    try {    
          $link=conexion();    
          $comando = $link->prepare($query);
          $comando->execute(array($id_producto));
          $row = $comando->fetch(PDO::FETCH_ASSOC);
          return $row;

        } catch (PDOException $e) {
            // Aqui puedes clasificar el error dependiendo de la excepcion
            // para presentarlo en la respuesta Json
            return -1;
        }
  }
  
  
//************************** METODO PARA SELECCIONAR PRODUCTO POR DESCRIPCION **************************//
  public static function getProductosDescripcion($desc) {
        include("connection_db.php");
        $query = "SELECT id_producto,nom_producto,des_producto,stock,precio,unidad_medida,estado_producto,categoria,fecha_entrada from tb_productos where des_producto = ?";
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
  
  
   //************************** METODO PARA SELECCIONAR TODO LO DE PRODUCTOS **************************//
   public static function getAllProductos() {
        include("connection_db.php");
        
        $query = "SELECT id_producto,nom_producto,des_producto,stock,precio,unidad_medida,estado_producto,categoria,fecha_entrada FROM tb_productos";

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
                                            
                     $array [] = array('id_producto' => $result['id_producto'], 
                     'nom_producto' => $result['nom_producto'], 
                     'des_producto' => $result['des_producto'], 
                     'stock' => $result['stock'], 
                     'precio' => $result['precio'], 
                     'unidad_medida' => $result['unidad_medida'], 
                     'estado_producto' => $result['estado_producto'], 
                     'categoria' => $result['categoria'], 
                     'fecha_entrada' => $result['fecha_entrada']);
                    
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