package com.sis21a.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import com.sis21a.e_commerce.R;

public class MenuFloating extends AppCompatActivity {

FloatingActionsMenu btnM;
FloatingActionButton btncrearFAB, btnmodificarFAB,btneliminarFAB, btnconsultarFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnM = findViewById(R.id.btnM);
        btncrearFAB = findViewById(R.id.btncrearFAB);
        btnmodificarFAB = findViewById(R.id.btnmodificarFAB);
        btneliminarFAB = findViewById(R.id.btneliminarFAB);
        btnconsultarFAB = findViewById(R.id.btnconsultarFAB);

    }
}
