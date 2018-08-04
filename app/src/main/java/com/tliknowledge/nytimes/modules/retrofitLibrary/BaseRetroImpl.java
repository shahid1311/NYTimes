package com.tliknowledge.nytimes.modules.retrofitLibrary;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tliknowledge.nytimes.BuildConfig;
import com.tliknowledge.nytimes.constants.APIConstants;
import com.tliknowledge.nytimes.modules.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetroImpl {
    protected Retrofit retrofit;
    protected Activity activity;
    protected Context context;
    private Logger logger;


    public BaseRetroImpl(Context context, boolean addTimeout) {
        this(context, null, addTimeout);
    }

    public BaseRetroImpl(Context context, Activity activity, boolean addTimeout) {
        this.context = context;
        this.activity = activity;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("debug")) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder().addInterceptor(logging);
        if (addTimeout) {
            httpClientBuilder.readTimeout(APIConstants.TimeOut.SOCKET_TIME_OUT, TimeUnit.SECONDS);
            httpClientBuilder.connectTimeout(APIConstants.TimeOut.CONNECTION_TIME_OUT, TimeUnit.SECONDS);
        } else {
            httpClientBuilder.readTimeout(APIConstants.TimeOut.IMAGE_UPLOAD_SOCKET_TIMEOUT, TimeUnit.SECONDS);
            httpClientBuilder.connectTimeout(APIConstants.TimeOut.IMAGE_UPLOAD_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        }

        OkHttpClient httpClient = httpClientBuilder.build();

        logger = new Logger(BaseRetroImpl.class.getSimpleName());

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
