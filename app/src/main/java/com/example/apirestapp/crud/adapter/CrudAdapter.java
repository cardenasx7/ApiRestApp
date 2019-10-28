package com.example.apirestapp.crud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apirestapp.R;
import com.example.apirestapp.crud.adapter.holder.CrudHolder;
import com.example.apirestapp.crud.listener.CrudListener;
import com.example.apirestapp.crud.model.ProductosUi;

import java.util.List;

public class CrudAdapter extends RecyclerView.Adapter<CrudHolder> {

    private List<ProductosUi> productosUis;
    private CrudListener listener;

    public CrudAdapter(List<ProductosUi> productosUis, CrudListener listener) {
        this.productosUis = productosUis;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CrudHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productos, parent, false);
        return new CrudHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrudHolder holder, int position) {
        ProductosUi productosUi = productosUis.get(position);
        holder.bind(productosUi,listener);
    }

    @Override
    public int getItemCount() {
        return productosUis.size();
    }

    public void mostrarLista(List<ProductosUi> productosUis) {
        this.productosUis.clear();
        this.productosUis.addAll(productosUis);
        notifyDataSetChanged();
    }
}
