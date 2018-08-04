package com.tliknowledge.nytimes.modules.retrofitLibrary;


import com.tliknowledge.nytimes.constants.APIConstants;
import com.tliknowledge.nytimes.constants.AppConstants;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetroApi {
    @Headers({
            AppConstants.ApiHeader.ACCEPT + ":" + AppConstants.ApiHeader.APPLICATION_JSON,
            AppConstants.ApiHeader.CONTENT_TYPE + ":" + AppConstants.ApiHeader.APPLICATION_JSON
    })
    @GET(APIConstants.UrlPath.GET_MOST_VIEWED_ARTICLES)
    Call<ResponseBody> getMostViewedArticles(@Path("sections") String sections, @Path("days") String userId,
                                             @Query("api-key") String apiKey);

}
