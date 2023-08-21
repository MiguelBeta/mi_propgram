package com.example.mi_propgram.controller.interfaces;

import com.example.mi_propgram.models.DataFileUsers;

public interface RegisterCallback {
    void onFindRegister(DataFileUsers registro);
    void onNotFoundRegister();
}
