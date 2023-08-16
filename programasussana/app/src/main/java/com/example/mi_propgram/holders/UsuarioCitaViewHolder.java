package com.example.mi_propgram.holders;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.mi_propgram.R;
import com.example.mi_propgram.models.DataFileUsers;

public class UsuarioCitaViewHolder extends RecyclerView.ViewHolder {
    private TextView idTxtNombre, idTxtIdentificacion, idTxtTipoCita, idTxtLugar;

    public UsuarioCitaViewHolder(View view) {
        super(view);
        idTxtNombre = view.findViewById(R.id.idTxtNombre);

        idTxtIdentificacion = view.findViewById(R.id.idTxtIdentificacion);
        idTxtTipoCita = view.findViewById(R.id.idTxtTipoCita);
        idTxtLugar = view.findViewById(R.id.idTxtLugar);
    }

    public void render(DataFileUsers dataFileUsers, OnItemClickListener onItemClick) {
        idTxtNombre.setText(dataFileUsers.pacienteNombre);
        idTxtIdentificacion.setText(dataFileUsers.pacienteDocumento);
        idTxtTipoCita.setText(dataFileUsers.especialidadDescripcion);

        idTxtLugar.setOnClickListener(v -> onItemClick.onClick(dataFileUsers));
    }

    public interface OnItemClickListener {
        void onClick(DataFileUsers dataFileUsers);
    }
}
