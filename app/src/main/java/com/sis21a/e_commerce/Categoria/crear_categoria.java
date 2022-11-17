package com.sis21a.e_commerce.Categoria;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sis21a.e_commerce.MySingleton;
import com.sis21a.e_commerce.R;
import com.sis21a.e_commerce.Tienda.Vista_productos_tienda;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class crear_categoria extends AppCompatActivity {

    EditText etNombreCategoria, etDescripcionCategoria;
    CheckBox cbEstadoCategoria;
    Button btnGuardar, btnCancelarC;

    int checkState = 0;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_categoria);

        etNombreCategoria=findViewById(R.id.etNombreCategoria);
        etDescripcionCategoria=findViewById(R.id.etDescripcionCategoria);
        cbEstadoCategoria=findViewById(R.id.cbEstadoCategoria);
        btnGuardar=findViewById(R.id.btnGuardarC);
        btnCancelarC=findViewById(R.id.btnCancelarC);


        btnGuardar.setOnClickListener(this::onClick);
        btnCancelarC.setOnClickListener(this::onClick);
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
