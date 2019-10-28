package com.example.apirestapp.crud.adapter.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apirestapp.R;
import com.example.apirestapp.crud.listener.CrudListener;
import com.example.apirestapp.crud.model.ProductosUi;

public class CrudHolder extends RecyclerView.ViewHolder {

    TextView txtProductId, txtName, txtDesc, txtPrice, txtStock;
    ProductosUi productosUi;
    CrudListener listener;

    public CrudHolder(@NonNull View itemView) {
        super(itemView);

        txtProductId = itemView.findViewById(R.id.txtProductId);
        txtName = itemView.findViewById(R.id.txtName);
        txtDesc = itemView.findViewById(R.id.txtDescription);
        txtPrice = itemView.findViewById(R.id.txtPrice);
        txtStock = itemView.findViewById(R.id.txtStock);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(productosUi);
            }
        });
    }

    public void bind(ProductosUi productosUi, CrudListener listener) {
        this.productosUi = productosUi;
        this.listener = listener;
        txtProductId.setText("PRODUCT ID: "+ productosUi.getId());
        txtName.setText(String.format("PRODUCT NAME: %s", productosUi.getName()));
        txtDesc.setText(String.format("PRODUCT DESCRIPTION: %s", productosUi.getDescription()));
        txtPrice.setText(String.format("PRODUCT PRICE: %s", productosUi.getPrice()));
        txtStock.setText(String.format("PRODUCT STOCK: %s", productosUi.getStock()));
    }
}
