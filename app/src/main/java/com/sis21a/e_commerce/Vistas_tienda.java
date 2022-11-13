package com.sis21a.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Vistas_tienda extends AppCompatActivity {

    RecyclerView rvTiendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistas_tienda);

        rvTiendas = findViewById(R.id.rvTiendas);

    }
}