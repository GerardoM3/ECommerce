package com.sis21a.e_commerce.UI.sesion;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.sis21a.e_commerce.MySingleton;
import com.sis21a.e_commerce.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    TextInputEditText tiet_nombres_register, tiet_apellidos_register;
    Button btnCancelarRegistro, btnSiguienteRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tiet_nombres_register = findViewById(R.id.tiet_nombres_register);
        tiet_apellidos_register = findViewById(R.id.tiet_apellidos_register);
        btnCancelarRegistro = findViewById(R.id.btnCancelarRegistro);
        btnSiguienteRegistro = findViewById(R.id.btnSiguienteRegistro);



    }

    public void paso1(View v){
        String nombresUser = tiet_nombres_register.getText().toString();
        String apellidosUser = tiet_apellidos_register.getText().toString();

        if(nombresUser.length()==0){
            tiet_nombres_register.setError("Campo obligatorio");
        }else if(apellidosUser.length()==0){
            tiet_apellidos_register.setError("Campo obligatorio");
        }else{
            register_generales(this, nombresUser, apellidosUser);
        }
    }

    public void register_generales(final Context context, final String nombres, final String apellidos){
        String url = "https://e-commerce-itca.000webhostapp.com/WS/guardar_datos_generales.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject requestJSON = new JSONObject(response.toString());
                    String estado = requestJSON.getString("estado");
                    String mensaje = requestJSON.getString("mensaje");

                    if (estado.equals("1")) {
                        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(register.this, register2.class);
                        startActivity(i);
                        finishActivity(1);
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
                Toast.makeText(context, "No se pudo guardar. \n" + "Inténtalo más tarde.", Toast.LENGTH_SHORT).show();
            }
        }){

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json; charset=utf-8");
                map.put("nombres", nombres);
                map.put("apellidos", apellidos);
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void cancelarRegister(View v){
        Intent i = new Intent(register.this, login.class);
        startActivity(i);
        finishActivity(1);
    }
}