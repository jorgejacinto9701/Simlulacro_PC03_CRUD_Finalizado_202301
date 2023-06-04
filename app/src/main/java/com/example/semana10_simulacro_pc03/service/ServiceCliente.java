package com.example.semana10_simulacro_pc03.service;

import com.example.semana10_simulacro_pc03.entity.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceCliente {

    @GET("cliente")
    public abstract Call<List<Cliente>> listaCliente();
}
