package com.sis21a.e_commerce.UI.sesion;

import androidx.appcompat.app.AppCompatActivity;
import com.sis21a.e_commerce.R;

import android.os.Bundle;
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
}