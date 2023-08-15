package com.example.mi_propgram.controller.concierge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mi_propgram.R;

public class ConciergeActivity extends AppCompatActivity {

    private CardView idBtnReadCredetials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concierge);
        idBtnReadCredetials = findViewById(R.id.idBtnReadCredetials);

        setupButtonsListener();
    }

    private void setupButtonsListener() {
        final Intent[] intent = new Intent[1];
        idBtnReadCredetials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent[0] = new Intent(v.getContext(), ReadCredentialsActivity.class);
                startActivity(intent[0]);
            }
        });
    }
}