package com.sis21a.e_commerce.UI.sesion;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.sis21a.e_commerce.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public void cancelarRegister2(View v){
        Intent i = new Intent(register2.this, login.class);
        startActivity(i);
        finishActivity(1);
    }
}