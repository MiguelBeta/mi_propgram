package com.example.mi_propgram.controller.consultas;


import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.mi_propgram.models.GuardData;
import com.example.mi_propgram.models.GuardSchedule;
import com.google.firebase.database.DatabaseReference;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvImportarTurnos {

    public static void importCsvData(Context context, Uri uri, DatabaseReference databaseReference) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int lineCount = 0;
            List<String> dayNames = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (lineCount == 0) {
                    // Obtener los nombres de los días para el mapeo
                    dayNames = new ArrayList<>(Arrays.asList(parts)); // Crear una nueva lista
                    dayNames.remove(0); // Remover el encabezado "GUARDAS"
                } else if (lineCount > 1) {
                    String name = parts[0];
                    for (int i = 1; i < parts.length; i++) {
                        String shift = parts[i];
                        String dayName = dayNames.get(i - 1);

                        GuardData guardData = new GuardData();
                        guardData.setName(name.toUpperCase());
                        guardData.setDayName( StringUtils.stripAccents(dayName).toUpperCase());
                        guardData.setDayNumber(i);
                        guardData.setShift(shift);

                        String guardId = databaseReference.child("guards").push().getKey();
                        databaseReference.child("guards").child(guardId).setValue(guardData);
                    }
                }

                lineCount++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
