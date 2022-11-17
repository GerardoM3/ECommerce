package com.sis21a.e_commerce.Categoria;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
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
import com.sis21a.e_commerce.Dto.dto_categoria;
import com.sis21a.e_commerce.MySingleton;
import com.sis21a.e_commerce.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class modificar_categ extends AppCompatActivity {

    EditText etNombreCategoria, etDescripcionCategoria;
    CheckBox cbEstadoCategoria;
    Spinner sp_negocio;
    Button btnModificarC, btnCancelarC;

    RecyclerView listViewCategorias;
    ArrayAdapter adapter;

    ArrayList<String> lista = null;
    ArrayList<dto_categoria> listaCategorias;

    String datoNegocioCategoria = "";
    int chechStateCate = 0;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_categ);

        etNombreCategoria=findViewById(R.id.etNombreCategoria);
        etDescripcionCategoria=findViewById(R.id.etDesCategoria);
        cbEstadoCategoria=findViewById(R.id.cbEstadoCategoria);
        sp_negocio = findViewById(R.id.sp_negocio);
        btnModificarC=findViewById(R.id.btnModificarC);
        btnCancelarC=findViewById(R.id.btnCancelarC);

        Bundle objeto = getIntent().getExtras();
        dto_categoria dto = null;
        if(objeto!=null){
            dto = (dto_categoria) objeto.getSerializable("categoria");
            etNombreCategoria.setText(String.valueOf(dto.getNombre_categoria()));
            etDescripcionCategoria.setText(dto.getDescripcion_categoria());
        }

        btnModificarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_categoria = etNombreCategoria.getText().toString();
                String descripcion_categoria = etDescripcionCategoria.getText().toString();

                if(nombre_categoria.length() == 0){
                    etNombreCategoria.setError("Campo obligatorio");
                }else if(descripcion_categoria.length() == 0){
                    etDescripcionCategoria.setError("Campo obligatorio");
                }else if(sp_negocio.getSelectedItemPosition() == 0){
                    Toast.makeText(modificar_categ.this, "Debe seleccionar el negocio.", Toast.LENGTH_SHORT).show();
                }else{
                    if(cbEstadoCategoria.isChecked()==true){
                        chechStateCate = 1;
                    }else if(cbEstadoCategoria.isChecked()==false){
                        chechStateCate = 0;
                    }
                    modificando_categoria(modificar_categ.this, nombre_categoria, descripcion_categoria, String.valueOf(chechStateCate), datoNegocioCategoria);
                    finish();
                }
            }
        });


        sp_negocio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_negocio.getSelectedItemPosition() > 0){
                    datoNegocioCategoria = sp_negocio.getSelectedItem().toString();
                }else{
                    datoNegocioCategoria = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void modificando_categoria(final Context context, final String nombre_categoria, final String descripcion_categoria, final String estado_categoria, final String id_negocio){
        String url = "https://e-commerce-itca.000webhostapp.com/WS/actualizar_categoria.php";
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
                map.put("nombre_categoria", nombre_categoria);
                map.put("descripcion_categoria", descripcion_categoria);
                map.put("estado_categoria", estado_categoria);
                map.put("id_negocio", id_negocio);

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

        eliminando_categoria(modificar_categ.this, String.valueOf(dto.getId_categoria()));

    }

    private void eliminando_categoria(final Context context, final String id_categoria) {

        Bundle objeto = getIntent().getExtras();
        dto_categoria dto = null;
        dto = (dto_categoria) objeto.getSerializable("categoria");

        String id_cat = String.valueOf(dto.getId_categoria());
        String nom_cat = dto.getNombre_categoria();
        String estado_cat = String.valueOf(dto.getEstado_categoria());


        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Advertencia");
        builder.setMessage("¿Está seguro que desea borrar el siguiente registro? \nID Categoría: " + id_cat + "\nNombre de la categoría: " + nom_cat + "\nEstado de categoría: " + estado_cat);
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String url = "https://gerardomonroysis11a.000webhostapp.com/WS/service2020/eliminar_categoria.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, response + " ~ " + id_cat, Toast.LENGTH_SHORT).show();
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
                        map.put("id_categoria", id_categoria);
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
