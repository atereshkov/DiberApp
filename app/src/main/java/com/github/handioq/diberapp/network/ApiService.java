package com.github.handioq.diberapp.network;

import com.github.handioq.diberapp.model.dto.AuthResponseDto;
import com.github.handioq.diberapp.model.dto.OrderDto;
import com.github.handioq.diberapp.model.dto.UserInfoDto;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    String LOGIN_URL = "/oauth/token";
    String USER_GET_INFO = "/api/v1/users/info";
    String USER_ORDERS = "/api/v1/users/{user_id}/orders";

    @POST(LOGIN_URL)
    Observable<AuthResponseDto> login(@Query("username") String login,
                                      @Query("password") String password,
                                      @Query(NetworkConstants.GRANT_TYPE) String grantType,
                                      @Query(NetworkConstants.CLIENT_ID) String clientId);

    @GET(USER_GET_INFO)
    Observable<UserInfoDto> getUserInfo();

    @GET(USER_ORDERS)
    Observable<List<OrderDto>> getOrders(@Path("user_id") long userId);

}