package com.example.mi_propgram.controller.consultas;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mi_propgram.controller.interfaces.ResponseListGuardsInterface;
import com.example.mi_propgram.models.GuardData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ListarCuadroTurnosPorDia {
    public static void listGuardsByDayAndSort(@Nullable String targetDay, @Nullable String nameGuard, final ResponseListGuardsInterface<List<GuardData>> callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("guards");
        String filter = "";
        String paramEqualTo = "";

        if (targetDay != null) {
            filter = "dayName";
            paramEqualTo = targetDay;
        } else if (nameGuard != null) {
            filter = "name";
            paramEqualTo = nameGuard;
        }


        Query query = databaseReference.orderByChild(filter).equalTo(paramEqualTo);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<GuardData> response = new ArrayList<>();
                for (DataSnapshot guardSnapshot : dataSnapshot.getChildren()) {

                    GuardData guardData = guardSnapshot.getValue(GuardData.class);
                    response.add(guardData);
                }

                response.sort(new Comparator<GuardData>() {
                    @Override
                    public int compare(GuardData guard1, GuardData guard2) {
                        return Integer.compare(guard1.getDayNumber(), guard2.getDayNumber());
                    }
                });

                callback.onSuccess(response);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }
}
