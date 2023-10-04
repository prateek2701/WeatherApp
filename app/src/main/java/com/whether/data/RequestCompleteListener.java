package com.whether.data;

public interface RequestCompleteListener<T> {
    void onRequestSuccess(T data);
    void onRequestFailed(String errorMessage);
}
