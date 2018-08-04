package com.tliknowledge.nytimes.constants;

public interface APIConstants {

    interface TimeOut {
        int IMAGE_UPLOAD_CONNECTION_TIMEOUT = 120;
        int IMAGE_UPLOAD_SOCKET_TIMEOUT = 120;
        int SOCKET_TIME_OUT = 60;
        int CONNECTION_TIME_OUT = 60;
    }

    interface UrlPath {
        String GET_MOST_VIEWED_ARTICLES = "svc/mostpopular/v2/mostviewed/{sections}/{days}.json";

    }

    interface APIFlags {
        int GET_MOST_VIEWED_ARTICLES = 1;

    }
}
