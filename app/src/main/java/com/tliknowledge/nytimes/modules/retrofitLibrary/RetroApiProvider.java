package com.tliknowledge.nytimes.modules.retrofitLibrary;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.tliknowledge.nytimes.BuildConfig;
import com.tliknowledge.nytimes.constants.APIConstants;
import com.tliknowledge.nytimes.listeners.RetrofitListener;
import com.tliknowledge.nytimes.model.ErrorModel;
import com.tliknowledge.nytimes.modules.logger.Logger;
import com.tliknowledge.nytimes.utils.HttpUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroApiProvider extends BaseRetroImpl {

    private static RetroApiProvider retroApi;
    private RetroApi restAdapter;
    private final Logger logger = new Logger(RetroApiProvider.class.getSimpleName());

    public RetroApiProvider(Context context, Activity activity) {
        super(context, activity, true);
        restAdapter = retrofit.create(RetroApi.class);
    }

    public static RetroApiProvider getRetroDoctor(Context context, Activity activity) {
        if (retroApi == null) {
            retroApi = new RetroApiProvider(context, activity);
            return retroApi;
        }
        return retroApi;
    }

    public void getMostViewedArticles(final RetrofitListener retrofitListener, String days, String section) {
        Call<ResponseBody> call = restAdapter.getMostViewedArticles(section, days, BuildConfig.MOST_VIEWWED_API_KEY);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                validateResponse(retrofitListener, response, APIConstants.APIFlags.GET_MOST_VIEWED_ARTICLES);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), t,
                        APIConstants.APIFlags.GET_MOST_VIEWED_ARTICLES);
            }

        });
    }


    private void validateResponse(final RetrofitListener retrofitListener, Response response, int apiFlag) {
        if (response.code() == 200) {
            ResponseBody responseBody = (ResponseBody) response.body();
            String responseBodyString = null;
            try {
                responseBodyString = responseBody.string();
            } catch (Exception e) {
                logger.error(e);
            }
            if (responseBodyString != null) {
                retrofitListener.onResponseSuccess(responseBodyString, apiFlag);
            } else {
                retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), null, apiFlag);
            }
        } else {
            Gson gson = new Gson();
            ErrorModel errorPojo;
            try {
                errorPojo = gson.fromJson((response.errorBody()).string(), ErrorModel.class);
                if (errorPojo == null) {
                    errorPojo = HttpUtil.getServerErrorPojo(context);
                }
                retrofitListener.onResponseError(errorPojo, null, apiFlag);
            } catch (Exception e) {
                retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), null, apiFlag);
            }
        }
    }
}
