package com.sis21a.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class MenuFloating extends AppCompatActivity {

FloatingActionsMenu grupoBotones;
FloatingActionButton fbCrear, fbModif,fbEl, fbConsul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}