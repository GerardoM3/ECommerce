package com.sis21a.e_commerce.UI.sesion;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.sis21a.e_commerce.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public void cancelarRegister(View v){
        Intent i = new Intent(register.this, login.class);
        startActivity(i);
        finishActivity(1);
    }
}