package com.tliknowledge.nytimes.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.tliknowledge.nytimes.constants.AppConstants;
import com.tliknowledge.nytimes.model.ErrorModel;
import com.tliknowledge.nytimes.modules.logger.Logger;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HttpUtil {
    private static Logger logger = new Logger(HttpUtil.class.getSimpleName());

    /**
     * This method returns a Json object for handling Force update error
     *
     * @return
     */
    public static JSONObject getServerErrorJsonObject(Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(AppConstants.ErrorClass.STATUS, "ERROR");
            jsonObject.put(AppConstants.ErrorClass.CODE, 3000);
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("We are currently not able to process your request. Please check your internet connection and try again.");
            jsonObject.put(AppConstants.ErrorClass.ERROR, errorMessages);
        } catch (Exception e) {
            logger.error(e);
        }
        return jsonObject;
    }

    /**
     * This method returns a Json object for handling Force update error
     *
     * @return
     */
    public static ErrorModel getServerErrorPojo(Context context) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(getServerErrorJsonObject(context).toString(), ErrorModel.class);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}
