package com.example.mi_propgram.controller.consultas;

import android.content.Context;
import android.net.Uri;
import com.example.mi_propgram.models.InfoGuarda;
import com.google.firebase.database.DatabaseReference;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CsvImportarInfoGuarda {

    public static void importCsvData(Context context, Uri uri, DatabaseReference databaseReference) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);

            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(','));

            for (CSVRecord record : csvParser) {
                InfoGuarda dataModel = new InfoGuarda(
                        record.get("NOMBRE").toUpperCase(),
                        record.get("APELLIDOS"),
                        record.get("CEDULA"),
                        record.get("TELEFONO"),
                        record.get("CORREO")
                );
                databaseReference.child("datosGuarda").child(dataModel.getIdentification()).setValue(dataModel);
            }

            csvParser.close();
            reader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
