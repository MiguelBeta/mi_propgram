package com.example.mi_propgram.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mi_propgram.R;
import com.example.mi_propgram.holders.UserSystemHolder;
import com.example.mi_propgram.models.UsuarioModel;

import java.util.List;

public class UserSystemAdapter extends RecyclerView.Adapter<UserSystemHolder> {
    private List<UsuarioModel> list;

    public void setUserSystemList(List<UsuarioModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserSystemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_system, parent, false);
        return new UserSystemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserSystemHolder holder, int position) {
        UsuarioModel userSystem = list.get(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
