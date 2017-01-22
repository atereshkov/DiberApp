package com.github.handioq.diberapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class AuthPreferences {

    private static final String TAG = "AuthPreferences";
    public static String token;
    public static String refreshToken;

    private static final String AUTH_PREFERENCES = "auth";
    private static final String TOKEN = "token";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String TOKEN_NULL = "null";
    private static final String USER_ID = "userId";
    private static final long USER_ID_NULL = -1;

    private static SharedPreferences sharedPreferences;
    private Context context;

    public AuthPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setUserToken(String token) {
        sharedPreferences.edit().putString(TOKEN, token).apply();

        AuthPreferences.token = token;
    }

    public static String getUserToken() {
        String token = TOKEN_NULL;

        if (sharedPreferences.contains(TOKEN)) {
            token = sharedPreferences.getString(TOKEN, TOKEN_NULL);
        }

        return token;
    }

    public void setUserRefreshToken(String refreshToken) {
        sharedPreferences.edit().putString(REFRESH_TOKEN, refreshToken).apply();

        AuthPreferences.refreshToken = refreshToken;
    }

    public static String getUserRefreshToken() {
        String refreshToken = TOKEN_NULL;

        if (sharedPreferences.contains(REFRESH_TOKEN)) {
            refreshToken = sharedPreferences.getString(REFRESH_TOKEN, TOKEN_NULL);
        }

        return refreshToken;
    }

    public void setUserId(long id) {
        sharedPreferences.edit().putLong(USER_ID, id).apply();
    }

    public static long getUserId() {
        long userId = USER_ID_NULL;

        if (sharedPreferences.contains(USER_ID)) {
            userId = sharedPreferences.getLong(USER_ID, USER_ID_NULL);
        }

        return userId;
    }

    public boolean isUserLoggedIn() {
        boolean isLogged = true;

        if (sharedPreferences.getString(TOKEN, TOKEN_NULL).equals(TOKEN_NULL)) {
            isLogged = false;
        }

        return isLogged;
    }

    public void logout() {
        Log.i(TAG, "logout");
        sharedPreferences.edit()
                .putString(TOKEN, TOKEN_NULL)
                .putLong(USER_ID, USER_ID_NULL).apply();
    }
}
