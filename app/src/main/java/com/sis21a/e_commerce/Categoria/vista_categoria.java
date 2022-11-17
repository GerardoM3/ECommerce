package com.sis21a.e_commerce.Categoria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sis21a.e_commerce.Dto.dto_categoria;
import com.sis21a.e_commerce.MySingleton;
import com.sis21a.e_commerce.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class vista_categoria extends AppCompatActivity {

    ListView lvCategorias;

    ArrayAdapter adapter;

    ArrayList<String> lista = null;
    ArrayList<dto_categoria> listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_categoria);

        lvCategorias = findViewById(R.id.lvCategorias);

        allQueryCategorias(this);

        lvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                String informacion = "ID Categoria: " + listaCategorias.get(pos).getId_categoria();
                informacion += "Nombre de la categoría: " + listaCategorias.get(pos).getNombre_categoria();
                informacion += "Descripción de la categoría: " + listaCategorias.get(pos).getDescripcion_categoria();
                informacion += "Estado de la categoría: " + listaCategorias.get(pos).getEstado_categoria();
                informacion += "ID Negocio: " + listaCategorias.get(pos).getId_negocio();

                dto_categoria categorias = listaCategorias.get(pos);
                Intent i = new Intent(vista_categoria.this,modificar_categ.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("categoria", categorias);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }


    public void allQueryCategorias(final Context context){
        listaCategorias = new ArrayList<dto_categoria>();
        lista = new ArrayList<String>();

        String url = "https://e-commerce-itca.000webhostapp.com/WS/buscar_Categoria.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    int totalEncontrados = array.length();

                    dto_categoria obj_categorias = null;

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject categoriasObject = array.getJSONObject(i);
                        int id_categoria = categoriasObject.getInt("id_categoria");
                        String nombre_categoria = categoriasObject.getString("nombre_categoria");
                        String descripcion_categoria = categoriasObject.getString("descripcion_categoria");
                        int estado_categoria = Integer.parseInt(categoriasObject.getString("estado_categoria"));
                        int id_negocio = Integer.parseInt(categoriasObject.getString("id_negocio"));
                        obj_categorias = new dto_categoria(id_categoria, nombre_categoria, descripcion_categoria, estado_categoria, id_negocio);
                        listaCategorias.add(obj_categorias);
                        lista.add("ID Categoría: " + listaCategorias.get(i).getId_categoria() + "\n" +
                                " ~ Nombre de Categoria: " + listaCategorias.get(i).getNombre_categoria() + "\n" +
                                " ~ Descripción categoría: " + listaCategorias.get(i).getDescripcion_categoria() + "\n"+
                                " ~ Estado categoría: " + listaCategorias.get(i).getEstado_categoria() + "\n" +
                                " ~ ID de negocio: " + listaCategorias.get(i).getId_negocio());

                        adapter = new ArrayAdapter<String>(vista_categoria.this, android.R.layout.simple_list_item_1,lista);
                        lvCategorias.setAdapter(adapter);

                        Log.i("Id Categoria", String.valueOf(obj_categorias.getId_categoria()));
                        Log.i("Nombre Categoria", obj_categorias.getNombre_categoria());
                        Log.i("Estado Categoria", String.valueOf(obj_categorias.getEstado_categoria()));

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



    public void btnCrearC(View v){
        Intent i = new Intent(vista_categoria.this, crear_categoria.class);
        startActivity(i);
        finishActivity(1);
    }
    public void btnmodificarC(View v){
        Intent i = new Intent(vista_categoria.this, modificar_categ.class);
        startActivity(i);
        finishActivity(1);
    }
}