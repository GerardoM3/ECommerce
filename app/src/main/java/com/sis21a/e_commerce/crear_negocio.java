package com.sis21a.e_commerce;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class crear_negocio extends AppCompatActivity {
    EditText etNombreNegocio, etTipoNegocio;
    Button btnGuardarN,btnCancelarN;

    @Override
    public void onCreate(Bundle SavedInstenceState){
        super.onCreate(SavedInstenceState);
        setContentView(R.layout.acticity_crear_negocio);


        etNombreNegocio=findViewById(R.id.etNombreNegocio);
        etTipoNegocio=findViewById(R.id.etTipoNegocio);
     btnCancelarN=findViewById(R.id.btnCancelarN);
    btnGuardarN = findViewById(R.id.btnGuardarN);


    }
}
