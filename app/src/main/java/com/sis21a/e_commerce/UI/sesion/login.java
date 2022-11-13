package com.sis21a.e_commerce.UI.sesion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.sis21a.e_commerce.MainActivity;
import com.sis21a.e_commerce.R;



public class login extends AppCompatActivity {

    TextInputEditText tiet_userLogin, tiet_contraLogin;
    Button btnlogin;
    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tiet_userLogin = findViewById(R.id.tiet_userLogin);
        tiet_contraLogin = findViewById(R.id.tiet_contraLogin);
        btnlogin = findViewById(R.id.btnlogin);
        tv_register = findViewById(R.id.tv_register);
        tv_register.setText(Html.fromHtml("¿No tienes una cuenta?  <u>Regístrate ahora</u>."));
    }

    public void clickRegister(View v){
        Intent i = new Intent(login.this, register.class);
        startActivity(i);
    }
}