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

public class register2 extends AppCompatActivity {

    TextInputEditText tiet_correo_register, tiet_user_register, tiet_contra_register;
    Button btnCancelarRegistro, btnSiguienteRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        tiet_correo_register = findViewById(R.id.tiet_correo_register);
        tiet_user_register = findViewById(R.id.tiet_user_register);
        tiet_contra_register = findViewById(R.id.tiet_contra_register);
        btnCancelarRegistro = findViewById(R.id.btnCancelarRegistro);
        btnSiguienteRegistro = findViewById(R.id.btnSiguienteRegistro);

    }

    public void paso2(View v){
        String usuario = tiet_user_register.getText().toString();
        String correo = tiet_correo_register.getText().toString();
        String clave = tiet_contra_register.getText().toString();

        if(usuario.length()==0){
            tiet_user_register.setError("Campo obligatorio");
        }else if(correo.length()==0){
            tiet_correo_register.setError("Campo obligatorio");
        }else if(clave.length()==0){
            tiet_contra_register.setError("Campo obligatorio");
        }else{
            register_cuenta(this, usuario, correo, clave);
        }
    }

    public void register_cuenta(final Context context, final String usuario, final String correo, final String clave){
        String url = "https://e-commerce-itca.000webhostapp.com/WS/guardar_datos_cuenta.php";
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
                        Intent i = new Intent(register2.this, register3.class);
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
                map.put("usuario", usuario);
                map.put("correo", correo);
                map.put("clave", clave);
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void cancelarRegister2(View v){
        Intent i = new Intent(register2.this, login.class);
        startActivity(i);
        finishActivity(1);
    }
}