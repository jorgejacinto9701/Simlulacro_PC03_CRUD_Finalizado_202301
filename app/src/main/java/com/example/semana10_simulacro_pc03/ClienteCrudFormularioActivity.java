package com.example.semana10_simulacro_pc03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.semana10_simulacro_pc03.util.NewAppCompatActivity;

public class ClienteCrudFormularioActivity extends NewAppCompatActivity {

    Button btnCrudRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_crud_formulario);

        btnCrudRegresar = findViewById(R.id.btnCrudRegresar);
        btnCrudRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        ClienteCrudFormularioActivity.this,
                        ClienteCrudListaActivity.class);
                startActivity(intent);
            }
        });
    }
}