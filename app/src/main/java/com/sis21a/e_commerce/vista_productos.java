package com.sis21a.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class vista_productos extends AppCompatActivity {

    RecyclerView rv_productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_productos);

        rv_productos = findViewById(R.id.rv_productos);

    }
}