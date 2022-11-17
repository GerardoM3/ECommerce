package com.sis21a.e_commerce.UI.sesion;

import androidx.appcompat.app.AppCompatActivity;
import com.sis21a.e_commerce.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class register3 extends AppCompatActivity {

    Spinner spTipoCuenta;
    CheckBox cbTipoCuentaActivo, cbTipoCuentaInactivo;
    Button btnCancelarRegistro, btnFinalizarRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        spTipoCuenta = findViewById(R.id.spTipoCuenta);
        cbTipoCuentaActivo = findViewById(R.id.cbTipoCuentaActivo);
        cbTipoCuentaInactivo = findViewById(R.id.cbTipoCuentaInactivo);
        btnCancelarRegistro = findViewById(R.id.btnCancelarRegistro);
        btnFinalizarRegistro = findViewById(R.id.btnFinalizarRegistro);

    }

    public void cancelarRegister3(View v){
        Intent i = new Intent(register3.this, login.class);
        startActivity(i);
        finishActivity(1);
    }
}