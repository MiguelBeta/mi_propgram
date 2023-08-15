package com.example.mi_propgram.controller.securityBoss;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mi_propgram.R;
import com.example.mi_propgram.models.DataFileUsers;
import com.example.mi_propgram.utils.FileUtils;
import com.example.mi_propgram.utils.PermissionUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SecurityBossActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private Button idBtnImportInfo;
    private static final int REQUEST_CODE = 123; // Código de solicitud para onActivityResult


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_boss);
        idBtnImportInfo = findViewById(R.id.idBtnImportInfo);

        idBtnImportInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("text/csv");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, llama a la función de utilidad para abrir el selector de archivos
                FileUtils.openFilePicker(this);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference fileContentRef = databaseReference.child("fileContent");

                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length >= 1) {
                            DataFileUsers record = new DataFileUsers(parts[0].trim(), "parts[1].trim()", "parts[2].trim()",
                                    "parts[3].trim()", "parts[4].trim()", "parts[5].trim()", "parts[6].trim()",
                                    "parts[7].trim()", "parts[8].trim()", "parts[9].trim()", "parts[10].trim()",
                                    "parts[11].trim()", "parts[12].trim()", "parts[13].trim()", "parts[14].trim()");
                            Log.e(TAG, "Nombre: " + record.FechaInicialCita);
                        }


                        /*if (parts.length >= 1) { // Asegúrate de que haya suficientes atributos
                            DataFileUsers record = new DataFileUsers(parts[0], "parts[1].trim()", "parts[2].trim()",
                                    "parts[3].trim()", "parts[4].trim()", "parts[5].trim()", "parts[6].trim()",
                                    "parts[7].trim()", "parts[8].trim()", "parts[9].trim()", "parts[10].trim()",
                                    "parts[11].trim()", "parts[12].trim()", "parts[13].trim()", "parts[14].trim()");

                            fileContentRef.push().setValue(parts[0]);
                        }*/
                    }

                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}