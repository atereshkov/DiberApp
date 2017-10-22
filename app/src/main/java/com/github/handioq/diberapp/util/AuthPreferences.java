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
    private static final String USER_ID = "userId";
    private static final String USER_FULLNAME = "fullname";
    private static final String USER_EMAIL = "email";

    public static final String TOKEN_NULL = "null";
    public static final long USER_ID_NULL = -1;

    private static SharedPreferences sharedPreferences;
    private Context context;

    public AuthPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void setUserToken(String token) {
        sharedPreferences.edit().putString(TOKEN, token).apply();

        AuthPreferences.token = token;
        //Log.i(TAG, "setUserToken: " + token);
    }

    public static String getUserToken() {
        String token = TOKEN_NULL;

        if (sharedPreferences.contains(TOKEN)) {
            token = sharedPreferences.getString(TOKEN, TOKEN_NULL);
        }

        //Log.i(TAG, "getUserToken" + token);

        return token;
    }

    public static void setUserFullname(String fullname) {
        sharedPreferences.edit().putString(USER_FULLNAME, fullname).apply();
    }

    public static String getUserFullname() {
        String fullname = TOKEN_NULL;

        if (sharedPreferences.contains(USER_FULLNAME)) {
            fullname = sharedPreferences.getString(USER_FULLNAME, TOKEN_NULL);
        }

        return fullname;
    }

    public static void setUserEmail(String email) {
        sharedPreferences.edit().putString(USER_EMAIL, email).apply();
    }

    public static String getUserEmail() {
        String email = TOKEN_NULL;

        if (sharedPreferences.contains(USER_EMAIL)) {
            email = sharedPreferences.getString(USER_EMAIL, TOKEN_NULL);
        }

        return  email;
    }

    public static void setUserRefreshToken(String refreshToken) {
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

    public static void setUserId(long id) {
        sharedPreferences.edit().putLong(USER_ID, id).apply();
    }

    public static long getUserId() {
        long userId = USER_ID_NULL;

        if (sharedPreferences.contains(USER_ID)) {
            userId = sharedPreferences.getLong(USER_ID, USER_ID_NULL);
        }

        return userId;
    }

    public static boolean isUserLoggedIn() {
        boolean isLogged = true;

        if (sharedPreferences.getString(TOKEN, TOKEN_NULL).equals(TOKEN_NULL)) {
            isLogged = false;
        }

        if (getUserToken().equals(TOKEN_NULL) || getUserToken().equals("") || getUserToken().isEmpty()) {
            isLogged = false;
        }

        if (getUserId() == USER_ID_NULL) {
            isLogged = false;
        }

        return isLogged;
    }

    public static void logout() {
        Log.i(TAG, "logout");
        sharedPreferences.edit()
                .putString(TOKEN, TOKEN_NULL)
                .putLong(USER_ID, USER_ID_NULL).apply();

        Log.i(TAG, "Token cleared.");
        Log.i(TAG, "isUserLoggedIn: " + isUserLoggedIn());
    }
}
