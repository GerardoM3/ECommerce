package com.sis21a.e_commerce.Categoria;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.sis21a.e_commerce.R;

public class modificar_categ extends AppCompatActivity {

    EditText etNombreCategoria, etDescripcionCategoria;
    CheckBox cbEstadoCategoria;
    Button btnGuardar, btnCancelarC;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_categ);

        etNombreCategoria=findViewById(R.id.etNombreCategoria);
        etDescripcionCategoria=findViewById(R.id.etDesCategoria);
        cbEstadoCategoria=findViewById(R.id.cbEstadoCategoria);
        btnGuardar=findViewById(R.id.btnGuardarC);
        btnCancelarC=findViewById(R.id.btnCancelarC);
    }
}
