package com.example.semana10_simulacro_pc03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.semana10_simulacro_pc03.util.NewAppCompatActivity;

public class ClienteCrudListaActivity extends NewAppCompatActivity {

    Button  btnCrudListar, btnCrudRegistra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_crud_lista);

        btnCrudListar = findViewById(R.id.btnCrudListar);
        btnCrudRegistra = findViewById(R.id.btnCrudRegistra);

        btnCrudRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        ClienteCrudListaActivity.this,
                        ClienteCrudFormularioActivity.class);
                startActivity(intent);
            }
        });


    }
}