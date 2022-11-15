package com.sis21a.e_commerce.Producto;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.sis21a.e_commerce.R;

public class crear_producto extends AppCompatActivity {

EditText etNombreProducto, etDescripcionProducto;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
    }
}
