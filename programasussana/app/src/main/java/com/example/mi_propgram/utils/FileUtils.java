package com.example.mi_propgram.utils;

import android.app.Activity;
import android.content.Intent;

public class FileUtils {
    public static final int PICK_XLS_CSV_FILE = 102; // Code select file


    public static void openFilePicker(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/vnd.ms-excel"); // Filtrar por archivos XLS.CSV
        activity.startActivityForResult(intent, PICK_XLS_CSV_FILE);
    }

}
