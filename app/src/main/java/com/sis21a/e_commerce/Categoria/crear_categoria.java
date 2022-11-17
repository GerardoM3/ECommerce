package com.sis21a.e_commerce.Categoria;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sis21a.e_commerce.Dto.dto_negocio;
import com.sis21a.e_commerce.MySingleton;
import com.sis21a.e_commerce.R;
import com.sis21a.e_commerce.Tienda.Vista_productos_tienda;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class crear_categoria extends AppCompatActivity {

    EditText etNombreCategoria, etDescripcionCategoria;
    CheckBox cbEstadoCategoria;
    Spinner sp_negocio;
    Button btnGuardar, btnCancelarC;

    ArrayList<dto_negocio> listaNegocios;
    ArrayList<String> lista;

    String id_negocio = "";
    String nombre_negocio = "";


    int checkState = 0;
    int conta = 0;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_categoria);

        etNombreCategoria=findViewById(R.id.etNombreCategoria);
        etDescripcionCategoria=findViewById(R.id.etDescripcionCategoria);
        cbEstadoCategoria=findViewById(R.id.cbEstadoCategoria);
        sp_negocio = findViewById(R.id.sp_negocio);
        btnGuardar=findViewById(R.id.btnGuardarC);
        btnCancelarC=findViewById(R.id.btnCancelarC);

        fk_negocios(crear_categoria.this);


        sp_negocio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(conta >= 1 && sp_negocio.getSelectedItemPosition() > 0){
                    String item_spinner = sp_negocio.getSelectedItem().toString();
                    String s[] = item_spinner.split("~");

                    id_negocio = s[0].trim();
                    nombre_negocio = s[1];

                    Toast toast = Toast.makeText(crear_categoria.this, "Id cat: " + id_negocio + "\n" + "Nombre Categoria: " + nombre_negocio, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else{
                    id_negocio = "";
                    nombre_negocio = "";
                }

                conta++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnGuardar.setOnClickListener(this::onClick);
        btnCancelarC.setOnClickListener(this::onClick);
    }

    public void fk_negocios(final Context context){
        listaNegocios = new ArrayList<dto_negocio>();
        lista = new ArrayList<String>();
        lista.add("Seleccionar negocio");

        String url = "https://e-commerce-itca.000webhostapp.com/WS/buscar_negocio.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    int totalEncontrados = array.length();

                    dto_negocio obj_negocio = null;

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject negocioObject = array.getJSONObject(i);
                        int id_negocio = negocioObject.getInt("id_negocio");
                        String nombre_negocio = negocioObject.getString("nombre_negocio");
                        String tipo_negocio = negocioObject.getString("tipo_negocio");
                        int id_usuario = Integer.parseInt(negocioObject.getString("id_usuario"));
                        obj_negocio = new dto_negocio(id_negocio, nombre_negocio, tipo_negocio, id_usuario);
                        listaNegocios.add(obj_negocio);
                        lista.add(listaNegocios.get(i).getId_negocio() + "~" + listaNegocios.get(i).getNombre_negocio());
                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(crear_categoria.this, android.R.layout.simple_spinner_item, lista);
                        sp_negocio.setAdapter(adaptador);

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
            case R.id.btnGuardarC:
                String nombre = etNombreCategoria.getText().toString();
                String descripcion = etDescripcionCategoria.getText().toString();
                if(nombre.length() == 0){
                    etNombreCategoria.setError("Campo obligatorio");
                }else if(descripcion.length() == 0){
                    etDescripcionCategoria.setError("Campo obligatorio");
                }else{
                    if(cbEstadoCategoria.isChecked()==true){
                        checkState = 1;
                    }else if (cbEstadoCategoria.isChecked()==false){
                        checkState = 0;
                    }
                    Toast.makeText(this, "Bien...", Toast.LENGTH_SHORT).show();
                    save_server(this, nombre, descripcion, checkState);

                }
                break;

            case R.id.btnCancelarC:
                Cancel();
                break;

            default:
        }
    }

    private void save_server(final Context context, final String nombre_categoria, final String descripcion_categoria, final int estado_categoria){
        String url = "https://gerardomonroysis11a.000webhostapp.com/WS/service2020/guardar.php";
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
                        etNombreCategoria.setText(null);
                        etDescripcionCategoria.setText(null);
                        cbEstadoCategoria.setChecked(false);
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
                map.put("nombre_categoria", nombre_categoria);
                map.put("descripcion_categoria", descripcion_categoria);
                map.put("estado_categoria", String.valueOf(estado_categoria));
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public void Cancel(){
        Intent i = new Intent(crear_categoria.this, Vista_productos_tienda.class);
        startActivity(i);
        finishActivity(1);
    }
}
