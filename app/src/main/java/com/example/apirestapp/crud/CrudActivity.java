package com.example.apirestapp.crud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apirestapp.Constantes;
import com.example.apirestapp.R;
import com.example.apirestapp.agregar.AgregarActivity;
import com.example.apirestapp.crud.adapter.CrudAdapter;
import com.example.apirestapp.crud.listener.CrudListener;
import com.example.apirestapp.crud.model.ProductosUi;
import com.example.apirestapp.crud.service.CrudNodeService;
import com.example.apirestapp.crud.service.CrudPhpService;
import com.example.apirestapp.crud.service.CrudPythonService;
import com.example.apirestapp.crud.service.CrudSprintService;
import com.example.apirestapp.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.apirestapp.principal.PrincipalActivity.EXTRA_STATUS;

public class CrudActivity extends AppCompatActivity implements CrudListener {

    private RecyclerView reciclador;
    private CrudAdapter crudAdapter;
    private Button btnAddUser;
    private String status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_crud);
        reciclador = findViewById(R.id.reciclador);
        btnAddUser = findViewById(R.id.btnAddUser);
        crudAdapter = new CrudAdapter(new ArrayList<ProductosUi>(),this);
        reciclador.setLayoutManager(new LinearLayoutManager(this));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(crudAdapter);
        initView();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (status == null) return;
        initListar(status);
    }

    private void initView() {


        if (getIntent() != null) {
            String status = getIntent().getStringExtra(EXTRA_STATUS);
            this.status = status;
            initListar(status);
            Log.d("TAG", "STATUS " + status);
        }


        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrudActivity.this, AgregarActivity.class);
                intent.putExtra("product_name", "");
                intent.putExtra("product_description", "");
                intent.putExtra("product_price", "");
                intent.putExtra("product_stock", "");
                intent.putExtra(EXTRA_STATUS, status);
                startActivity(intent);
            }
        });
    }

    private void initListar(String status) {
        switch (status) {
            case "Php":
                initRetrofitPhp();
                break;
            case "Node":
                initRetrofitNode();
                break;
            case "Python":
                initRetrofitPython();
                break;
            case "Sprint":
                initRetrofitSpring();
                break;
        }


    }

    private void initRetrofitSpring() {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudSprintService sprintService = retrofitInstance.createService(CrudSprintService.class, Constantes.BASE_URL_SPRING);
        sprintService.all().enqueue(new Callback<List<ProductosUi>>() {
            @Override
            public void onResponse(Call<List<ProductosUi>> call, Response<List<ProductosUi>> response) {
                if (response.isSuccessful()) {
                    crudAdapter.mostrarLista(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ProductosUi>> call, Throwable t) {

            }
        });
    }

    private void initRetrofitPython() {
        CrudPythonService pythonService = RetrofitInstance.createService(CrudPythonService.class, Constantes.BASE_URL_PYTHON);
        pythonService.all().enqueue(new Callback<List<ProductosUi>>() {
            @Override
            public void onResponse(Call<List<ProductosUi>> call, Response<List<ProductosUi>> response) {
                if (response.isSuccessful()) {
                    crudAdapter.mostrarLista(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ProductosUi>> call, Throwable t) {

            }
        });
    }

    private void initRetrofitNode() {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudNodeService nodeService = retrofitInstance.createService(CrudNodeService.class, Constantes.BASE_URL_NODE);
        nodeService.all().enqueue(new Callback<List<ProductosUi>>() {
            @Override
            public void onResponse(Call<List<ProductosUi>> call, Response<List<ProductosUi>> response) {
                if (response.isSuccessful()) {
                    crudAdapter.mostrarLista(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ProductosUi>> call, Throwable t) {

            }
        });
    }

    private void initRetrofitPhp() {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudPhpService phpService = retrofitInstance.createService(CrudPhpService.class, Constantes.BASE_URL_PHP);
        phpService.all().enqueue(new Callback<List<ProductosUi>>() {
            @Override
            public void onResponse(Call<List<ProductosUi>> call, Response<List<ProductosUi>> response) {
                if (response.isSuccessful()) {
                    crudAdapter.mostrarLista(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ProductosUi>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onItemClick(ProductosUi productosUi) {
        Intent intent = new Intent(CrudActivity.this, AgregarActivity.class);
        intent.putExtra("product_id", productosUi.getId());
        intent.putExtra("product_name", productosUi.getName());
        intent.putExtra("product_description", productosUi.getDescription());
        intent.putExtra("product_price", productosUi.getPrice());
        intent.putExtra("product_stock", productosUi.getStock());
        intent.putExtra(EXTRA_STATUS, status);
        startActivity(intent);
    }
}
