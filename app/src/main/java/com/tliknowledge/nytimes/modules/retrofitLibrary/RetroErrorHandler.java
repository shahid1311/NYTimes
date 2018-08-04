package com.tliknowledge.nytimes.modules.retrofitLibrary;


import com.tliknowledge.nytimes.modules.logger.Logger;

import retrofit2.Response;

public class RetroErrorHandler {

    public static RetroErrorHandler retroErrorHandler;
    private Logger logger = new Logger(RetroErrorHandler.class.getSimpleName());

    private RetroErrorHandler() {
    }

    public static RetroErrorHandler getRetroErrorHandler() {
        if (retroErrorHandler == null) {
            retroErrorHandler = new RetroErrorHandler();
        }
        return retroErrorHandler;
    }

    public int checkErrorCode(Response response) {
        if (response.code() == 200) {
            return 1;
        } else if (response.code() == 412) {
            return 2;
        } else if (response.code() == 403) {
            return 3;
        } else if (response.code() == 500) {
            return 4;
        }
        return 0;
    }
}
