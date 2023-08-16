package com.example.mi_propgram.controller;

import android.content.Context;
import android.net.Uri;

import com.example.mi_propgram.models.DataFileUsers;
import com.google.firebase.database.DatabaseReference;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CsvDataImporter {

    public static void importCsvData(Context context, Uri uri, DatabaseReference databaseReference) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);

            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(';'));

            for (CSVRecord record : csvParser) {
                DataFileUsers dataModel = new DataFileUsers(
                        record.get("ActividadNombre"),
                        record.get("Duracion"),
                        record.get("EspecialidadDescripcion"),
                        record.get("FechaInicialCita"),
                        record.get("PacienteDocumento"),
                        record.get("PacienteNombre"),
                        record.get("PacienteTelefono"),
                        record.get("PacienteEdadPaciente"),
                        record.get("PacienteSexo"),
                        record.get("TipoCita"),
                        record.get("TurnoMedicoCentroAtencionCodigo"),
                        record.get("TurnoMedicoFechaTurno"),
                        record.get("TurnoMedicoMedicoCodigo"),
                        record.get("TurnoMedicoMedicoNombreCompleto"),
                        record.get("TurnoMedicoCentroAtencionNombre")
                );

                databaseReference.child("datosPacientes").child(dataModel.pacienteDocumento).setValue(dataModel);
            }

            csvParser.close();
            reader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
