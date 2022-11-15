package com.example.ecomercecopy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.sis21a.e_commerce.R;

public class MainActivity extends AppCompatActivity {
    Button btnMainCategoria, btnMainProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistascat);

        btnMainCategoria=findViewById(R.id.btnMainCategoria);
        btnMainProducto=findViewById(R.id.btnMainProducto);

    }
}