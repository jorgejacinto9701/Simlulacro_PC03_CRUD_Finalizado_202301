package com.example.semana10_simulacro_pc03.service;

import com.example.semana10_simulacro_pc03.entity.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ServiceCliente {

    @GET("cliente")
    public abstract Call<List<Cliente>> listaCliente();

    @POST("cliente")
    public abstract Call<Cliente> registraCliente(@Body Cliente objCliente);

    @PUT("cliente")
    public abstract Call<Cliente> actualizaCliente(@Body Cliente objCliente);
}
