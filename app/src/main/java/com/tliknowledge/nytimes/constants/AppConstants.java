package com.tliknowledge.nytimes.constants;

public interface AppConstants {

    interface ApiHeader {
        String CONTENT_TYPE = "Content-Type";
        String APPLICATION_JSON = "application/json";
        String ACCEPT = "Accept";
    }

    interface ErrorClass {
        String CODE = "code";
        String STATUS = "status";
        String ERROR = "errors";
    }

    interface PrefClass {
        String DEFAULT = "th";
    }

    interface PrefKey {
        String AUTH_KEY = "authKey";
        String MOBILE_NUMBER = "mobileNumber";
        String USER_CURRENT_LOCATION = "userCurrentLocation";
        String PROFILE_PIC_LINK = "profilePicLink";
        String USER_LAST_KNOWN_ADDRESS = "last_known_address";
        String IS_FIRST_RUN = "IS_FIRST_RUN";
    }

    interface ImageFormat {
        String STANDARD_THUMBNAIL = "Standard Thumbnail";
    }

    interface IntentData {
        String HYPERLINK = "HYPERLINK";
        String ARTICLE = "ARTICLE";
    }
}
