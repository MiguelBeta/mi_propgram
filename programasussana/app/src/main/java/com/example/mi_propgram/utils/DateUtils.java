package com.example.mi_propgram.utils;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.mi_propgram.controller.interfaces.DateUtilsHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtils implements DateUtilsHelper {
    @Override
    public String showDatePickerDialog(Button textButton) {
        Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        textButton.clearFocus();

        DatePickerDialog datePicker = new DatePickerDialog(textButton.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(selectedYear, selectedMonth, selectedDay);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                textButton.setText(dateFormat.format(selectedDate.getTime()));
            }
        }, year, month, day);

        datePicker.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                textButton.clearFocus();
            }
        });

        datePicker.show();
        return null;
    }

}
