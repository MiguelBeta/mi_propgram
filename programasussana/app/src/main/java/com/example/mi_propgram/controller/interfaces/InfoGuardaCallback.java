package com.example.mi_propgram.controller.interfaces;

import com.example.mi_propgram.models.DataFileUsers;
import com.example.mi_propgram.models.InfoGuarda;

public interface InfoGuardaCallback {
    void onFindRegister(InfoGuarda registro);
    void onNotFoundRegister();
}
