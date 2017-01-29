package com.github.handioq.diberapp.util;

import com.github.handioq.diberapp.model.dto.ErrorDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

public class ErrorUtils {

    private static final String ERROR_NO_INTERNET = "No internet connection";
    private static final String ERROR_SERVER_UNAVAILABLE = "Server unavailable";

    public static String getMessage(Throwable t) {
        String error = "";

        if (t instanceof UnknownHostException) {
            error = ERROR_NO_INTERNET;
        } else if (t instanceof HttpException) {
            error = ERROR_SERVER_UNAVAILABLE;

            try {
                switch (((HttpException) t).code()) {
                    case 400:
                        error = handleError(((HttpException) t).response().errorBody().string());
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            error = t.getMessage();
        }

        return error;
    }

    private static String handleError(String errorBody) {
        String error = "";

        Gson gson = new GsonBuilder().create();
        ErrorDto errorDto = gson.fromJson(errorBody, ErrorDto.class);
        error = errorDto.getErrorDescription();

        return error;
    }

}
