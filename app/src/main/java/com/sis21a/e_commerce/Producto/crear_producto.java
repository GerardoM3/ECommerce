package com.sis21a.e_commerce.Producto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sis21a.e_commerce.Categoria.crear_categoria;
import com.sis21a.e_commerce.Dto.dto_producto;
import com.sis21a.e_commerce.MySingleton;
import com.sis21a.e_commerce.R;
import com.sis21a.e_commerce.Tienda.Vista_productos_tienda;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class crear_producto extends AppCompatActivity {
    EditText etNombreProducto,etDescripcionProducto,etExistencias,etPrecioProducto;
    Spinner spCategoriaProducto;
    CheckBox cbEstadoProducto;
    Button btnGuardarP, btnCancelarP;
    int checkState = 0;
    int conta = 0;


    ArrayList<dto_producto> listaProductos;
    ArrayList<String> lista;



    String id_categoria = "";
    String nombre_categoria = "";
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        etNombreProducto = findViewById(R.id.etNombreProducto);
        etDescripcionProducto = findViewById(R.id.etDescripcionProducto);
        etExistencias = findViewById(R.id.etExistencias);
        etPrecioProducto = findViewById(R.id.etPrecioProducto);
        spCategoriaProducto = findViewById(R.id.spCategoriaProducto);
        cbEstadoProducto = findViewById(R.id.cbEstadoProducto);
        btnGuardarP = findViewById(R.id.btnGuardarP);
        btnCancelarP = findViewById(R.id.btnCancelarP);

        fk_productos(crear_producto .this);

        spCategoriaProducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(conta >= 1 && spCategoriaProducto.getSelectedItemPosition() > 0){
                    String item_spinner = spCategoriaProducto.getSelectedItem().toString();
                    String s[] = item_spinner.split("~");

                    id_categoria= s[0].trim();
                    nombre_categoria = s[1];

                    Toast toast = Toast.makeText(crear_producto.this, "Id producto: " + id_categoria + "\n" + "Nombre Categoria: " + nombre_categoria, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else{
                    id_categoria = "";
                    nombre_categoria = "";
                }

                conta++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGuardarP.setOnClickListener(this::onClick);
        btnCancelarP.setOnClickListener(this::onClick);

    }

    public void fk_productos(final Context context){
        listaProductos = new ArrayList<dto_producto>();
        lista = new ArrayList<String>();
        lista.add("Seleccionar negocio");

        String url = "https://e-commerce-itca.000webhostapp.com/WS/buscar_Producto.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    int totalEncontrados = array.length();

                    dto_producto obj_producto = null;

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject productoObject = array.getJSONObject(i);
                        int id_producto = productoObject.getInt("id_producto");
                        String nombre_producto = productoObject.getString("nombre_producto");
                        String descripcion_producto = productoObject.getString("descripcion_producto");
                        int existencias = Integer.parseInt(productoObject.getString("existencias"));
                        double precio = Integer.parseInt(productoObject.getString("precio"));
                        int estado_producto = Integer.parseInt(productoObject.getString("estado_producto"));
                        int id_categoria = Integer.parseInt(productoObject.getString("id_categoria"));

                        obj_producto = new dto_producto(id_producto, nombre_producto, descripcion_producto, existencias, precio, estado_producto, id_categoria);
                        listaProductos.add(obj_producto);
                        lista.add(listaProductos.get(i).getId_producto() + "~" + listaProductos.get(i).getNombre_producto() + "~" + listaProductos.get(i).getDescripcion_producto() + "~" + listaProductos.get(i).getExistencias() + "~" + listaProductos.get(i).getPrecio() + "~" + listaProductos.get(i).getEstado_producto() + "~" + listaProductos.get(i).getId_categoria());
                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(crear_producto.this, android.R.layout.simple_spinner_item, lista);
                        spCategoriaProducto.setAdapter(adaptador);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error. Compruebe su acceso a Internet.", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnGuardarP:
                String nombre = etNombreProducto.getText().toString();
                String descripcion = etDescripcionProducto.getText().toString();
                int Existencias = Integer.parseInt(etExistencias.getText().toString());
                Double precio = Double.valueOf(etPrecioProducto.getText().toString());
                int Estado_producto = Integer.parseInt(cbEstadoProducto.getText().toString());
                if(nombre.length() == 0){
                    etNombreProducto.setError("Campo obligatorio");
                }else if(descripcion.length() == 0){
                    etDescripcionProducto.setError("Campo obligatorio");
                }else{
                    if(cbEstadoProducto.isChecked()==true){
                        checkState = 1;
                    }else if (cbEstadoProducto.isChecked()==false){
                        checkState = 0;
                    }
                    Toast.makeText(this, "Bien...", Toast.LENGTH_SHORT).show();
                    save_server(this, nombre, descripcion, Existencias, precio, Estado_producto, checkState);

                }
                break;

            case R.id.btnCancelarC:
                Cancel();
                break;

            default:
        }
    }
    private void save_server(final Context context, final String nombre_producto, final String descripcion_producto, final int existencias, final double precio, final int estado_producto, int checkState){
        String url = "https://e-commerce-itca.000webhostapp.com/WS/guardar_producto.php";
        StringRequest request = new StringRequest(Request.Method.POST, url,  new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                JSONObject requestJSON = null;
                try{
                    requestJSON = new JSONObject(response.toString());
                    String estado = requestJSON.getString("estado");
                    String mensaje = requestJSON.getString("mensaje");

                    if(estado.equals("1")){
                        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        etNombreProducto.setText(null);
                        etDescripcionProducto.setText(null);
                        etExistencias.setText(null);
                        etPrecioProducto.setText(null);
                        cbEstadoProducto.setChecked(false);




                    }else if(estado.equals("2")){
                        Toast.makeText(context, "" + mensaje, Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError){
                Toast.makeText(context, "No se puede guardar. \nInténtelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type","application/json; charset=utf-8");
                map.put("Accept", "application/json");

                map.put("nombre_producto", nombre_producto);
                map.put("descripcion_categoria", descripcion_producto);
                map.put("existencias", String.valueOf(existencias));
                map.put("precio", String.valueOf(precio));
                map.put("estado_producto", String.valueOf(estado_producto));
                //map.put("id_categoria", String.valueOf(id_categoria));
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
    public void Cancel(){
        Intent i = new Intent(crear_producto.this, Vista_productos_tienda.class);
        startActivity(i);
        finishActivity(1);
    }
}
