package com.example.mi_propgram.controller.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.concierge.ListadoPacientesActivity;

public class MenuActivity extends AppCompatActivity {

    private CardView idBtnManageQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setupInitializeUi();
        setupClickListeners();

    }

    private void setupClickListeners() {
        idBtnManageQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListadoPacientesActivity.class);
                intent.putExtra("isAdmin", "adminParam");
                startActivity(intent);
            }
        });
    }

    private void setupInitializeUi() {
        idBtnManageQuotes = findViewById(R.id.idBtnManageQuotes);
        idBtnManageQuotes = findViewById(R.id.idBtnManageQuotes);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}