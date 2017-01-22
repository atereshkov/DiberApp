package com.github.handioq.diberapp.network;

import com.github.handioq.diberapp.model.dto.AuthResponseDto;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    String LOGIN_URL = "/oauth/token";
    String SIGNUP_URL = "/api/user/signup";
    String CATALOG_URL = "/api/catalog";
    String USER_URL = "/api/user";

    @POST(LOGIN_URL)
    Observable<AuthResponseDto> login(@Query("username") String login,
                                      @Query("password") String password,
                                      @Query(NetworkConstants.GRANT_TYPE) String grantType,
                                      @Query(NetworkConstants.CLIENT_ID) String clientId);



}