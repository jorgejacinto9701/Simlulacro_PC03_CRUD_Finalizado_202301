package com.example.semana10_simulacro_pc03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.semana10_simulacro_pc03.adapter.ClienteCrudAdapter;
import com.example.semana10_simulacro_pc03.entity.Cliente;
import com.example.semana10_simulacro_pc03.service.ServiceCliente;
import com.example.semana10_simulacro_pc03.util.ConnectionRest;
import com.example.semana10_simulacro_pc03.util.NewAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteCrudListaActivity extends NewAppCompatActivity {

    Button  btnCrudListar, btnCrudRegistra;

    //GridView
    GridView gridCrudClientes;
    ArrayList<Cliente> data = new ArrayList<Cliente>();
    ClienteCrudAdapter adaptador;

    //service
    ServiceCliente serviceCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_crud_lista);

        btnCrudListar = findViewById(R.id.btnCrudListar);
        btnCrudRegistra = findViewById(R.id.btnCrudRegistra);
        gridCrudClientes = findViewById(R.id.gridCrudClientes);
        adaptador = new ClienteCrudAdapter(this,R.layout.activity_cliente_crud_item,data);
        gridCrudClientes.setAdapter(adaptador);

        serviceCliente =  ConnectionRest.getConnection().create(ServiceCliente.class);

        btnCrudRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        ClienteCrudListaActivity.this,
                        ClienteCrudFormularioActivity.class);
                intent.putExtra("var_titulo", "REGISTRA CLIENTE");
                intent.putExtra("var_tipo", "REGISTRA");
                startActivity(intent);
            }
        });

        btnCrudListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    lista();
            }
        });

        gridCrudClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(
                        ClienteCrudListaActivity.this,
                        ClienteCrudFormularioActivity.class);
                intent.putExtra("var_titulo", "ACTUALIZA CLIENTE");
                intent.putExtra("var_tipo", "ACTUALIZA");
                startActivity(intent);

            }
        });
    }

    public void lista(){
        Call<List<Cliente>> call = serviceCliente.listaCliente();
        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                List<Cliente> lista =response.body();
                data.clear();
                data.addAll(lista);
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {

            }
        });
    }

}