package com.tliknowledge.nytimes.listeners;

import com.tliknowledge.nytimes.model.ErrorModel;

public interface RetrofitListener {
    void onResponseSuccess(String responseBodyString, int flag);

    void onResponseError(ErrorModel errorModel, Throwable throwable, int apiFlag);
}