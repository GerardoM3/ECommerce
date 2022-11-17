package com.sis21a.e_commerce.UI.sesion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.sis21a.e_commerce.MenuFloating;
import com.sis21a.e_commerce.MySingleton;
import com.sis21a.e_commerce.R;
import com.sis21a.e_commerce.Tienda.Vista_productos_tienda;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class login extends AppCompatActivity {

    TextInputEditText tiet_userLogin, tiet_contraLogin;
    Button btnlogin;
    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tiet_userLogin = findViewById(R.id.tiet_userLogin);
        tiet_contraLogin = findViewById(R.id.tiet_contraLogin);
        btnlogin = findViewById(R.id.btnlogin);
        tv_register = findViewById(R.id.tv_register);
        tv_register.setText(Html.fromHtml("¿No tienes una cuenta?  <u>Regístrate ahora</u>."));


    }

    public void LogIn(View v){
        String loginUser = tiet_userLogin.getText().toString();
        String loginPass = tiet_contraLogin.getText().toString();

        if(loginUser.length() == 0){
            tiet_userLogin.setError("Campo obligatorio");
        }else if(loginPass.length() == 0){
            tiet_contraLogin.setError("Campo obligatorio");
        }else{
            LogOn(login.this, loginUser, loginPass);

        }
    }

    public void LogOn(final Context context, final String usuario, final String clave){
        String url = "https://e-commerce-itca.000webhostapp.com/WS/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject requestJSON = new JSONObject(response.toString());
                    String estado = requestJSON.getString("estado");
                    String mensaje = requestJSON.getString("mensaje");

                    if (estado.equals("1")) {
                        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(login.this, Vista_productos_tienda.class);
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

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json;charset=utf-8");
                map.put("usuario", usuario);
                map.put("clave", clave);
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    public void clickRegister(View v){
        Intent i = new Intent(login.this, register.class);
        startActivity(i);
        finishActivity(1);
    }
}