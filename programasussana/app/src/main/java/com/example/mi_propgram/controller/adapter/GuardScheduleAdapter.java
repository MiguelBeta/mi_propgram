package com.example.mi_propgram.controller.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.admin.DetalleInfoGuardaActivity;
import com.example.mi_propgram.models.GuardData;
import com.example.mi_propgram.models.GuardSchedule;

import java.util.List;

public class GuardScheduleAdapter extends RecyclerView.Adapter<GuardScheduleAdapter.ViewHolder> {

    private List<GuardData> guardSchedules;
    private String typeFilter;

    public void setGuardSchedules(List<GuardData> guardSchedules, String typeFilter) {
        this.guardSchedules = guardSchedules;
        this.typeFilter = typeFilter;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_turno_guarda, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GuardData guardSchedule = guardSchedules.get(position);

        holder.diaTextView.setText(String.valueOf(guardSchedule.getDayNumber()));
        holder.nombreGuardaMananaTextView.setText(guardSchedule.getName());
        holder.horarioTurnoMananaTextView.setText(guardSchedule.getShift());
        if (typeFilter.equals("nameGuard")) {
            holder.dayName.setVisibility(View.VISIBLE);
            holder.dayName.setTextSize(14f);
            holder.dayName.setText(guardSchedule.getDayName());
        } else {
            holder.dayName.setVisibility(View.GONE);
        }

        holder.idCardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DetalleInfoGuardaActivity.class);
                i.putExtra("nameGuard", guardSchedule.getName());
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return guardSchedules != null ? guardSchedules.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView diaTextView, dayName, nombreGuardaMananaTextView, horarioTurnoMananaTextView;
        CardView idCardItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            diaTextView = itemView.findViewById(R.id.dia);
            dayName = itemView.findViewById(R.id.dayName);
            nombreGuardaMananaTextView = itemView.findViewById(R.id.nombreGuardaManana);
            horarioTurnoMananaTextView = itemView.findViewById(R.id.horario_turnoManana);
            idCardItem = itemView.findViewById(R.id.idCardItem);

        }
    }
}
