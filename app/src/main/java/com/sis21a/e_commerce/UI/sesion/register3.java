package com.sis21a.e_commerce.UI.sesion;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sis21a.e_commerce.MySingleton;
import com.sis21a.e_commerce.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register3 extends AppCompatActivity {

    Spinner spTipoCuenta;
    CheckBox cbTipoCuentaActivo, cbTipoCuentaInactivo;
    Button btnCancelarRegistro, btnFinalizarRegistro;

    String datoTipo = "";
    int checkTipo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        spTipoCuenta = findViewById(R.id.spTipoCuenta);
        cbTipoCuentaActivo = findViewById(R.id.cbTipoCuentaActivo);
        cbTipoCuentaInactivo = findViewById(R.id.cbTipoCuentaInactivo);
        btnCancelarRegistro = findViewById(R.id.btnCancelarRegistro);
        btnFinalizarRegistro = findViewById(R.id.btnFinalizarRegistro);

        spTipoCuenta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spTipoCuenta.getSelectedItemPosition()>0){
                    datoTipo = spTipoCuenta.getSelectedItem().toString();
                }else{
                    datoTipo = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void paso3(View v){


        if(spTipoCuenta.getSelectedItemPosition()==0){
            Toast.makeText(this, "Seleccione el tipo de usuario", Toast.LENGTH_SHORT).show();
        }else{
            if((cbTipoCuentaActivo.isChecked()==true)&&(cbTipoCuentaInactivo.isChecked()==false)){
                checkTipo = 1;
            }else if((cbTipoCuentaActivo.isChecked()==false)&&(cbTipoCuentaInactivo.isChecked()==true)){
                checkTipo = 0;
            }
            register_cuenta(this, datoTipo, checkTipo);
        }
    }

    public void register_cuenta(final Context context, final String tipo, final int estado){
        String url = "https://e-commerce-itca.000webhostapp.com/WS/guardar_tipo_cuenta.php";
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
                        Intent i = new Intent(register3.this, login.class);
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
                map.put("tipo", tipo);
                map.put("estado", String.valueOf(estado));
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void cancelarRegister3(View v){
        Intent i = new Intent(register3.this, login.class);
        startActivity(i);
        finishActivity(1);
    }
}