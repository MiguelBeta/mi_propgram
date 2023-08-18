package com.example.mi_propgram.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GenericAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private List<T> list;
    private int layoutResId;
    private ViewCreator<VH> viewHolderCreator;
    private ItemClickListener<VH, T> onItemClick;

    public GenericAdapter(
            List<T> list,
            int layoutResId,
            ViewCreator<VH> viewHolderCreator,
            ItemClickListener<VH, T> onItemClick) {
        this.list = list;
        this.layoutResId = layoutResId;
        this.viewHolderCreator = viewHolderCreator;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return viewHolderCreator.create(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        T item = list.get(position);
        onItemClick.onClick(holder, item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ViewCreator<VH extends RecyclerView.ViewHolder> {
        VH create(View view);
    }

    public interface ItemClickListener<VH extends RecyclerView.ViewHolder, T> {
        void onClick(VH holder, T item);
    }
}
