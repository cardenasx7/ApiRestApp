package com.example.apirestapp.agregar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apirestapp.Constantes;
import com.example.apirestapp.R;
import com.example.apirestapp.crud.model.ProductosUi;
import com.example.apirestapp.crud.service.CrudNodeService;
import com.example.apirestapp.crud.service.CrudPhpService;
import com.example.apirestapp.crud.service.CrudPythonService;
import com.example.apirestapp.crud.service.CrudSprintService;
import com.example.apirestapp.principal.PrincipalActivity;
import com.example.apirestapp.retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarActivity extends AppCompatActivity {

    EditText edtUId;
    EditText edtName;
    EditText edtDesc;
    EditText edtPrice;
    EditText edtStock;
    Button btnSave;
    Button btnDel;
    TextView txtUId;
    String status;

    String productId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        txtUId = findViewById(R.id.txtUId);
        edtUId = findViewById(R.id.edtUId);
        edtName = findViewById(R.id.edtName);
        edtDesc = findViewById(R.id.edtDesc);
        edtPrice = findViewById(R.id.edtPrice);
        edtStock = findViewById(R.id.edtStock);

        btnSave = findViewById(R.id.btnSave);
        btnDel = findViewById(R.id.btnDel);


        Bundle extras = getIntent().getExtras();
        final String productId = extras.getString("product_id");
         String name = extras.getString("product_name");
         String description = extras.getString("product_description");
         String price = extras.getString("product_price");
         String stock = extras.getString("product_stock");
        status = extras.getString(PrincipalActivity.EXTRA_STATUS);

        Log.d("TAG", "STATUS : "+status);
        edtUId.setText(productId);
        edtName.setText(name);
        edtDesc.setText(description);
        edtPrice.setText(price);
        edtStock.setText(stock);

        if (productId != null && productId.trim().length() > 0) {
            edtUId.setFocusable(false);
            //btnDel.setVisibility(View.VISIBLE);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductosUi p = new ProductosUi();
                p.setName(edtName.getText().toString());
                p.setDescription(edtDesc.getText().toString());
                p.setPrice(edtPrice.getText().toString());
                p.setStock(edtStock.getText().toString());

                if (productId != null && productId.trim().length() > 0) {
                    updateProduct(productId, p);
                } else {
                    addProduct(p);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct(productId);

            }
        });
    }



    public void addProduct(ProductosUi productosUi) {
        switch (status) {
            case "Php":
                agregrarProductoPhp(productosUi);
                break;
            case "Node":
                agregrarProductoNode(productosUi);
                break;
            case "Python":
                agregrarProductoPython(productosUi);
                break;
            case "Sprint":
                agregrarProductoSpring(productosUi);
                break;
        }

    }

    private void agregrarProductoPhp(ProductosUi productosUi) {

        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudPhpService phpService = retrofitInstance.createService(CrudPhpService.class, Constantes.BASE_URL_PHP);
        phpService.add(productosUi).enqueue(new Callback<ProductosUi>() {
            @Override
            public void onResponse(Call<ProductosUi> call, Response<ProductosUi> response) {
                 /*if (response.isSuccessful()) {
                    Toast.makeText(AgregarActivity.this, "Producto Agregado!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }*/
                Toast.makeText(AgregarActivity.this, "Producto Agregado!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ProductosUi> call, Throwable t) {

            }
        });
    }

    private void agregrarProductoNode(ProductosUi productosUi) {

        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudNodeService nodeService = retrofitInstance.createService(CrudNodeService.class, Constantes.BASE_URL_NODE);
        nodeService.add(productosUi).enqueue(new Callback<ProductosUi>() {
            @Override
            public void onResponse(Call<ProductosUi> call, Response<ProductosUi> response) {

                Toast.makeText(AgregarActivity.this, "Producto Agregado!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ProductosUi> call, Throwable t) {

            }
        });
    }

    private void agregrarProductoPython(ProductosUi productosUi) {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudPythonService pythonService = retrofitInstance.createService(CrudPythonService.class, Constantes.BASE_URL_PYTHON);
        pythonService.add(productosUi).enqueue(new Callback<ProductosUi>() {
            @Override
            public void onResponse(Call<ProductosUi> call, Response<ProductosUi> response) {
                /*if (response.isSuccessful()) {
                    Toast.makeText(AgregarActivity.this, "Producto Agregado!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }*/
                Toast.makeText(AgregarActivity.this, "Producto Agregado!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ProductosUi> call, Throwable t) {

            }
        });
    }

    private void agregrarProductoSpring(ProductosUi productosUi) {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudSprintService sprintService = retrofitInstance.createService(CrudSprintService.class, Constantes.BASE_URL_SPRING);
        sprintService.add(productosUi).enqueue(new Callback<ProductosUi>() {
            @Override
            public void onResponse(Call<ProductosUi> call, Response<ProductosUi> response) {
                 /*if (response.isSuccessful()) {
                    Toast.makeText(AgregarActivity.this, "Producto Agregado!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }*/
                Toast.makeText(AgregarActivity.this, "Producto Agregado!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ProductosUi> call, Throwable t) {

            }
        });
    }


    public void updateProduct(String id, ProductosUi productosUi) {

        switch (status) {
            case "Php":
                updateProductoPhp(id, productosUi);
                break;
            case "Node":
                updateProductoNode(id, productosUi);
                break;
            case "Python":
                updateProductoPython(id, productosUi);
                break;
            case "Sprint":
                updateProductoSpring(id, productosUi);
                break;
        }


    }

    private void updateProductoSpring(String id, ProductosUi productosUi) {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudSprintService springService = retrofitInstance.createService(CrudSprintService.class, Constantes.BASE_URL_SPRING);
        springService.update(id, productosUi).enqueue(new Callback<ProductosUi>() {
            @Override
            public void onResponse(Call<ProductosUi> call, Response<ProductosUi> response) {
                Toast.makeText(AgregarActivity.this, "Producto Actualizado!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ProductosUi> call, Throwable t) {

            }
        });
    }

    private void updateProductoPython(String id, ProductosUi productosUi) {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudPythonService pythonService = retrofitInstance.createService(CrudPythonService.class, Constantes.BASE_URL_PYTHON);
        pythonService.update(id, productosUi).enqueue(new Callback<ProductosUi>() {
            @Override
            public void onResponse(Call<ProductosUi> call, Response<ProductosUi> response) {
                Toast.makeText(AgregarActivity.this, "Producto Actualizado!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ProductosUi> call, Throwable t) {

            }
        });
    }

    private void updateProductoNode(String id, ProductosUi productosUi) {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudNodeService nodeService = retrofitInstance.createService(CrudNodeService.class, Constantes.BASE_URL_NODE);
        nodeService.update(id, productosUi).enqueue(new Callback<ProductosUi>() {
            @Override
            public void onResponse(Call<ProductosUi> call, Response<ProductosUi> response) {
                Toast.makeText(AgregarActivity.this, "Producto Actualizado!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ProductosUi> call, Throwable t) {

            }
        });

    }

    private void updateProductoPhp(String id, ProductosUi productosUi) {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudPhpService phpService = retrofitInstance.createService(CrudPhpService.class, Constantes.BASE_URL_PHP);
        phpService.update(id, productosUi).enqueue(new Callback<ProductosUi>() {
            @Override
            public void onResponse(Call<ProductosUi> call, Response<ProductosUi> response) {
                Toast.makeText(AgregarActivity.this, "Producto Actualizado!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ProductosUi> call, Throwable t) {

            }
        });

    }

    public void deleteProduct(String id) {
        switch (status) {
            case "Php":
                deleteProductoPhp(id);
                break;
            case "Node":
                daleProductoNode(id);
                break;
            case "Python":
                deleteProductoPython(id);
                break;
            case "Sprint":
                deleteProductoSpring(id);
                break;
        }
    }

    private void deleteProductoSpring(String id) {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudSprintService springService = retrofitInstance.createService(CrudSprintService.class, Constantes.BASE_URL_SPRING);
        springService.delete(id).enqueue(new Callback<ProductosUi>() {
            @Override
            public void onResponse(Call<ProductosUi> call, Response<ProductosUi> response) {
                Toast.makeText(AgregarActivity.this, "Producto Eliminado!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ProductosUi> call, Throwable t) {

            }
        });
    }

    private void deleteProductoPython(String id) {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudPythonService pythonService = retrofitInstance.createService(CrudPythonService.class, Constantes.BASE_URL_PYTHON);
        pythonService.delete(id).enqueue(new Callback<ProductosUi>() {
            @Override
            public void onResponse(Call<ProductosUi> call, Response<ProductosUi> response) {
                Toast.makeText(AgregarActivity.this, "Producto Eliminado!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ProductosUi> call, Throwable t) {

            }
        });
    }

    private void daleProductoNode(String id) {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudNodeService nodeService = retrofitInstance.createService(CrudNodeService.class, Constantes.BASE_URL_NODE);
        nodeService.delete(id).enqueue(new Callback<ProductosUi>() {
            @Override
            public void onResponse(Call<ProductosUi> call, Response<ProductosUi> response) {
                Toast.makeText(AgregarActivity.this, "Producto Eliminado!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ProductosUi> call, Throwable t) {

            }
        });
    }

    private void deleteProductoPhp(String id) {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        CrudPhpService phpService = retrofitInstance.createService(CrudPhpService.class, Constantes.BASE_URL_PHP);
        phpService.delete(id).enqueue(new Callback<ProductosUi>() {
            @Override
            public void onResponse(Call<ProductosUi> call, Response<ProductosUi> response) {
                Toast.makeText(AgregarActivity.this, "Producto Eliminado!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ProductosUi> call, Throwable t) {

            }
        });
    }

}
