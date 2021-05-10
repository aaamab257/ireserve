package com.aaamab.bonappetit.utils.network;

public interface ConnectionListener<T> {
    void onSuccess(ConnectionResponse<T> connectionResponse);

    void onFail(Throwable throwable);
}
