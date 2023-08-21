package com.example.mi_propgram.controller.interfaces;

public interface UpdateCallback {
    void onSuccessUpdate();

    void onNotFountRegister();

    void onErrorUpdate(String errorMessage);
}
