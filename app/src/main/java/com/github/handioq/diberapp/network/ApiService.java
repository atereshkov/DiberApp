package com.github.handioq.diberapp.network;

import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    String LOGIN_URL = "/api/auth/login";
    String SIGNUP_URL = "/api/user/signup";
    String CATALOG_URL = "/api/catalog";
    String USER_URL = "/api/user";

    /*
    @POST(LOGIN_URL)
    Observable<AuthResponse> login(@Query("mail") String mail,
                                   @Query("password") String password);
    */


}