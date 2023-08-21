package com.example.mi_propgram.controller.interfaces;

public interface DeleteCallback {
    void onDeleteSuccess();

    void onNotFoundDelete();

    void onDeleteError(String errorMessage);
}
