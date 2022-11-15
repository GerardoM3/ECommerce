package com.sis21a.e_commerce.Producto;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.sis21a.e_commerce.R;

public class eliminar_producto extends AppCompatActivity {
    EditText etnompd;
    Button btn_eliminar, btn_cancel;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        etnompd=findViewById(R.id.etnompd);
        btn_eliminar=findViewById(R.id.btn_eliminar);
        btn_cancel=findViewById(R.id.btn_cancel);
    }
}
