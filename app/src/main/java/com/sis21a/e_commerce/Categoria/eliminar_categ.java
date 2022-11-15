package com.sis21a.e_commerce.Categoria;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.sis21a.e_commerce.R;

public class eliminar_categ extends AppCompatActivity {

    EditText etnom;
    Button btn_eliminar,btn_cancel;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_categ);

        etnom=findViewById(R.id.etnom);
        btn_eliminar=findViewById(R.id.btn_eliminar);
        btn_cancel=findViewById(R.id.btn_cancel);

    }
}
