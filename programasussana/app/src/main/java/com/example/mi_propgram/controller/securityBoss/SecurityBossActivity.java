package com.example.mi_propgram.controller.securityBoss;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.consultas.CsvDataImporter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecurityBossActivity extends AppCompatActivity {

    private Button idBtnImportInfo;
    private static final int REQUEST_CODE = 123;


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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                CsvDataImporter.importCsvData(this, data.getData(), databaseReference);
            }
        }
    }
}