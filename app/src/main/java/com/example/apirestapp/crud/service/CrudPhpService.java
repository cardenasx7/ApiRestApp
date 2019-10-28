package com.example.apirestapp.crud.service;

import com.example.apirestapp.crud.model.ProductosUi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CrudPhpService {
    @GET("products")
    Call<List<ProductosUi>> all();

    @POST("products")
    Call<ProductosUi> add(@Body ProductosUi product);

    @PUT("products/{id}")
    Call<ProductosUi> update(@Path("id") String id, @Body ProductosUi product);

    @DELETE("products/{id}")
    Call<ProductosUi> delete(@Path("id") String id);
}
