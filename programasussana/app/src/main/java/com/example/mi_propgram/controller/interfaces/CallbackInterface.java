package com.example.mi_propgram.controller.interfaces;

public interface CallbackInterface<T> {
    void onSuccess(T result);
    void onError(String errorMessage);
}