package com.sis21a.e_commerce.Producto;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sis21a.e_commerce.Categoria.modificar_categ;
import com.sis21a.e_commerce.Dto.dto_categoria;
import com.sis21a.e_commerce.Dto.dto_producto;
import com.sis21a.e_commerce.MySingleton;
import com.sis21a.e_commerce.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class modificar_producto extends AppCompatActivity {
    EditText etNombreProducto,etDescripcionProducto,etExistenciaProducto,etPrecioProducto;
    Spinner spCategoriaProducto;
    CheckBox cbEstadoProducto;
    Button btnCancelarP,btnActualizarPro;

    Spinner sp_producto;
    RecyclerView listViewCProducto;
    ArrayAdapter adapter;

    ArrayList<String> lista = null;
    ArrayList<dto_producto> listaProducto;

    String datoCategoria = "";
    int chechStateCate = 0;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        etNombreProducto = findViewById(R.id.etNombreProducto);
        etDescripcionProducto = findViewById(R.id.etDescripcionProducto);
        etExistenciaProducto = findViewById(R.id.etExistenciaProducto);
        etPrecioProducto = findViewById(R.id.etPrecioProducto);
        spCategoriaProducto = findViewById(R.id.spCategoriaProducto);
        cbEstadoProducto = findViewById(R.id.cbEstadoProducto);
        btnCancelarP = findViewById(R.id.btnCancelarP);
        btnActualizarPro = findViewById(R.id.btnActualizarPro);

        Bundle objeto = getIntent().getExtras();
        dto_producto dto = null;
        if(objeto!=null){
            dto = (dto_producto) objeto.getSerializable("producto");
            etNombreProducto.setText(String.valueOf(dto.getNombre_producto()));
            etDescripcionProducto.setText(dto.getDescripcion_producto());
        }

        btnActualizarPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_producto = etNombreProducto.getText().toString();
                String descripcion_producto = etDescripcionProducto.getText().toString();
                String existencias = etExistenciaProducto.getText().toString();
                double precio = Double.parseDouble(etPrecioProducto.getText().toString());
                String estado_producto = cbEstadoProducto.getText().toString();
                if(nombre_producto.length() == 0){
                    etNombreProducto.setError("Campo obligatorio");
                }else if(descripcion_producto.length() == 0){
                    etDescripcionProducto.setError("Campo obligatorio");
                }else if(existencias.length() == 0){
                    etExistenciaProducto.setError("Campo obligatorio");
                }else if(estado_producto.length() == 0){
                    cbEstadoProducto.setError("Campo obligatorio");
                }
                if(sp_producto.getSelectedItemPosition() == 0){
                    Toast.makeText(modificar_producto.this, "Debe seleccionar el negocio.", Toast.LENGTH_SHORT).show();
                }else{
                    if(cbEstadoProducto.isChecked()==true){
                        chechStateCate = 1;
                    }else if(cbEstadoProducto.isChecked()==false){
                        chechStateCate = 0;
                    }
                    modificando_producto(modificar_producto.this, nombre_producto, descripcion_producto, Integer.parseInt(String.valueOf(existencias)),precio, Integer.parseInt(estado_producto));
                    finish();
                }
            }
        });


        sp_producto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_producto.getSelectedItemPosition() > 0){
                    datoCategoria = sp_producto.getSelectedItem().toString();
                }else{
                    datoCategoria = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void modificando_producto(final Context context, final String nombre_producto, final String descripcion_producto, final int existencias, final double precio, final int estado_producto){
        String url = "https://e-commerce-itca.000webhostapp.com/WS/actualizar_producto.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject requestJSON = new JSONObject(response.toString());
                    String estado = requestJSON.getString("estado");
                    String mensaje = requestJSON.getString("mensaje");

                    if (estado.equals("1")) {
                        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                    } else if (estado.equals("2")) {
                        Toast.makeText(context, "" + mensaje, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "No se pudo actualizar. \n" + "Inténtelo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
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

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
        finish();
    }

    public void eliminar_Categoria(View v){

        Bundle objeto = getIntent().getExtras();
        dto_categoria dto = null;
        dto = (dto_categoria) objeto.getSerializable("categoria");

        eliminando_categoria(modificar_producto.this, String.valueOf(dto.getId_categoria()));

    }

    private void eliminando_categoria(final Context context, final String id_producto) {

        Bundle objeto = getIntent().getExtras();
        dto_producto dto = null;
        dto = (dto_producto) objeto.getSerializable("producto");

        String id_pro = String.valueOf(dto.getId_producto());
        String nom_pro = dto.getNombre_producto();
        String descripcion_pro = dto.getDescripcion_producto();
        int Existencias = dto.getExistencias();
        Double precio = dto.getPrecio();
        String estado_pro = String.valueOf(dto.getEstado_producto());




        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Advertencia");
        builder.setMessage("¿Está seguro que desea borrar el siguiente registro? \nID Categoría: " + id_pro + "\nNombre de la categoría: " + nom_pro + "\nEstado de categoría: " + estado_pro);
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String url = "https://e-commerce-itca.000webhostapp.com/WS/eliminar_producto.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, response + " ~ " + id_pro, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject requestJSON = new JSONObject(response.toString());
                            String estado = requestJSON.getString("estado");
                            String mensaje = requestJSON.getString("mensaje");

                            if (estado.equals("1")) {
                                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                            } else if (estado.equals("2")) {
                                Toast.makeText(context, "" + mensaje, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "No se pudo eliminar el registro", Toast.LENGTH_SHORT).show();
                    }
                }){

                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("Content-Type", "application/json; charset=utf-8");
                        map.put("Accept", "application/json");
                        map.put("id_producto", id_producto);
                        return map;
                    }
                };
                MySingleton.getInstance(context).addToRequestQueue(stringRequest);
                finish();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }
}
