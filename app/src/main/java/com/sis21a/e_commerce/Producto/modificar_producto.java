package com.sis21a.e_commerce.Producto;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.sis21a.e_commerce.R;

public class modificar_producto extends AppCompatActivity {
    EditText etNombreProducto,etDescripcionProducto,etStockProducto,etPrecioProducto;
    Spinner spCategoriaProducto;
    CheckBox cbEstadoProducto;
    Button btnCancelarP,btnActualizarPro;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        etNombreProducto = findViewById(R.id.etNombreProducto);
        etDescripcionProducto = findViewById(R.id.etDescripcionProducto);
        etStockProducto = findViewById(R.id.etStockProducto);
        etPrecioProducto = findViewById(R.id.etPrecioProducto);
        spCategoriaProducto = findViewById(R.id.spCategoriaProducto);
        cbEstadoProducto = findViewById(R.id.cbEstadoProducto);
        btnCancelarP = findViewById(R.id.btnCancelarP);
        btnActualizarPro = findViewById(R.id.btnActualizarPro);
    }
}
