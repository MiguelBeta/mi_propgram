package com.example.mi_propgram.controller.interfaces;

public interface ResponseListGuardsInterface<T> {
    void onSuccess(T result);
    void onError(String errorMessage);
}
