package com.example.mi_propgram.utils;


import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FunctionsOfExtensions {
    public ArrayAdapter<CharSequence> createArrayAdapterFromResource(Context context, int resourceId) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                context,
                android.R.layout.simple_spinner_item,
                context.getResources().getTextArray(resourceId)
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapter;
    }


    public void setOnItemSelectedListener(Spinner spinner, OnItemSelectedListener listener) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                listener.onItemSelected(parent, position, selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Acción cuando no se selecciona ningún elemento
            }
        });
    }


    public interface OnItemSelectedListener {
        void onItemSelected(AdapterView<?> parent, int position, String item);
    }

}

