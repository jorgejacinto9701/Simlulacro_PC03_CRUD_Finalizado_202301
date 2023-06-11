package com.example.semana10_simulacro_pc03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.semana10_simulacro_pc03.entity.Categoria;
import com.example.semana10_simulacro_pc03.entity.Cliente;
import com.example.semana10_simulacro_pc03.service.ServiceCategoria;
import com.example.semana10_simulacro_pc03.service.ServiceCliente;
import com.example.semana10_simulacro_pc03.util.ConnectionRest;
import com.example.semana10_simulacro_pc03.util.NewAppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteCrudFormularioActivity extends NewAppCompatActivity {

    Button btnCrudRegresar , btnCrudRegistra;
    TextView txtTitulo;

    EditText txtNombre, txtDNI;
    Spinner spnCategoria, spnEstado;
    ArrayAdapter<String> adaptadorCategoria;
    ArrayList<String> lstNombresCategoria = new ArrayList<String>();

    ServiceCategoria serviceCategoria ;
    ServiceCliente serviceCliente ;

    ArrayAdapter<String> adaptadorEstado;
    ArrayList<String> lstNombresEstado = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_crud_formulario);


        serviceCategoria = ConnectionRest.getConnection().create(ServiceCategoria.class);
        serviceCliente = ConnectionRest.getConnection().create(ServiceCliente.class);

        btnCrudRegistra = findViewById(R.id.btnCrudRegistrar);
        txtTitulo = findViewById(R.id.idCrudTituloCliente);

        txtNombre = findViewById(R.id.txtCrudNombre);
        txtDNI = findViewById(R.id.txtCrudDni);
        spnEstado = findViewById(R.id.spnCrudClienteEstado);
        spnCategoria = findViewById(R.id.spnCrudClienteCategoria);

        adaptadorCategoria = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lstNombresCategoria);
        spnCategoria.setAdapter(adaptadorCategoria);


        adaptadorEstado = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lstNombresEstado);
        spnEstado.setAdapter(adaptadorEstado);

        lstNombresEstado.add("1:Activo");
        lstNombresEstado.add("0:Inactivo");
        adaptadorEstado.notifyDataSetChanged();

        Bundle extras = getIntent().getExtras();
        String tipo = (String) extras.get("var_tipo");
        String titulo = (String) extras.get("var_titulo");


        if (tipo.equals("ACTUALIZA")){
            Cliente objCliente = (Cliente) extras.get("var_objeto");
            txtNombre.setText(objCliente.getNombre());
            txtDNI.setText(objCliente.getDni());
            if (objCliente.getEstado() == 0){
                spnEstado.setSelection(1);
            }else{
                spnEstado.setSelection(0);
            }



            Categoria objCategoria = objCliente.getCategoria();
            String aux2 = objCategoria.getIdCategoria()+":"+objCategoria.getDescripcion();
            int pos = -1;
            for(int i=0; i< lstNombresCategoria.size(); i++){
                if (lstNombresCategoria.get(i).equals(aux2)){
                      pos = i;
                      break;
                }
            }

            spnCategoria.setSelection(pos);
        }


        btnCrudRegistra.setText(tipo);
        txtTitulo.setText(titulo);

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

        btnCrudRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String nom = txtNombre.getText().toString();
                    String dni = txtDNI.getText().toString();
                    String estado = spnEstado.getSelectedItem().toString();
                    String categoria = spnCategoria.getSelectedItem().toString();

                    Categoria objNewCategoria = new Categoria();
                    objNewCategoria.setIdCategoria(Integer.parseInt(categoria.split(":")[0]));

                    Cliente objNewCliente = new Cliente();
                    objNewCliente.setNombre(nom);
                    objNewCliente.setDni(dni);
                    objNewCliente.setEstado(Integer.parseInt(estado.split(":")[0]));
                    objNewCliente.setCategoria(objNewCategoria);

                    if (tipo.equals("REGISTRA")){
                        insertaCliente(objNewCliente);
                    }else if (tipo.equals("ACTUALIZA")){
                        Cliente objAux = (Cliente) extras.get("var_objeto");
                        objNewCliente.setIdCliente(objAux.getIdCliente());
                        actualizaCliente(objNewCliente);
                    }
            }
        });

        cargaCategoria();
    }

    public void insertaCliente(Cliente objCliente){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(objCliente);
        // mensajeAlert(json);
        Call<Cliente> call = serviceCliente.registraCliente(objCliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.isSuccessful()){
                    Cliente objSalida = response.body();
                    String msg="Se registró el Cliente con exito\n";
                    msg+="ID : "+ objSalida.getIdCliente() +"\n";
                    msg+="NOMBRE : "+ objSalida.getNombre() ;
                    mensajeAlert(msg);
                }else {
                    mensajeAlert(response.toString());
                }
            }
            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                mensajeAlert("Error al acceder al Servicio Rest >>> " + t.getMessage());
            }
        });

    }

    public void actualizaCliente(Cliente objCliente){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(objCliente);
        // mensajeAlert(json);
        Call<Cliente> call = serviceCliente.actualizaCliente(objCliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.isSuccessful()){
                    Cliente objSalida = response.body();
                    String msg="Se actualizó el Cliente con exito\n";
                    msg+="ID : "+ objSalida.getIdCliente() +"\n";
                    msg+="NOMBRE : "+ objSalida.getNombre() ;
                    mensajeAlert(msg);
                }else {
                    mensajeAlert(response.toString());
                }
            }
            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                mensajeAlert("Error al acceder al Servicio Rest >>> " + t.getMessage());
            }
        });

    }

    public void cargaCategoria(){
        Call<List<Categoria>> call = serviceCategoria.listaCategoria();
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if(response.isSuccessful()){
                    List<Categoria> lst = response.body();
                    for(Categoria obj:lst){
                        lstNombresCategoria.add(obj.getIdCategoria()+":"+obj.getDescripcion());
                    }
                    adaptadorCategoria.notifyDataSetChanged();
                }else{
                    mensajeAlert(""+response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                mensajeAlert("Error al acceder al Servicio Rest >>> " + t.getMessage());
            }
        });
    }
}