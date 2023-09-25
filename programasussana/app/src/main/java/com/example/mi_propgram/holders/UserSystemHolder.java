package com.example.mi_propgram.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mi_propgram.R;
import com.example.mi_propgram.models.UsuarioModel;

public class UserSystemHolder extends RecyclerView.ViewHolder {

    private TextView tvNameUser;

    public UserSystemHolder(@NonNull View itemView) {
        super(itemView);
        tvNameUser = itemView.findViewById(R.id.tvNameUserSystem);

    }

    public void render(UsuarioModel userModel, OnItemClickListener onItemClickListener) {
        tvNameUser.setText(userModel.getNombre());
    }

    public interface OnItemClickListener {
        void onClick(UsuarioModel userModel);
    }

}
