package com.github.handioq.diberapp.network;

import com.github.handioq.diberapp.model.dto.AddressDto;
import com.github.handioq.diberapp.model.dto.AuthResponseDto;
import com.github.handioq.diberapp.model.dto.NewOrderDto;
import com.github.handioq.diberapp.model.dto.OrderDto;
import com.github.handioq.diberapp.model.dto.RegisterDto;
import com.github.handioq.diberapp.model.dto.ShopDto;
import com.github.handioq.diberapp.model.dto.UserDto;
import com.github.handioq.diberapp.model.dto.UserInfoDto;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    String API_BASE_URL = "/api/v1";

    String LOGIN_URL = "/oauth/token";
    String SIGNUP_URL = API_BASE_URL + "/auth/register";

    String USER_GET_INFO = API_BASE_URL + "/users/info";
    String USER_ORDERS = API_BASE_URL + "/users/{user_id}/orders";
    String USER_SHOPS = API_BASE_URL + "/users/{user_id}/shops";
    String USER_ADDRESSES = API_BASE_URL + "/users/{user_id}/addresses";
    String ORDER_INFO = API_BASE_URL + "/orders/{order_id}";

    @POST(LOGIN_URL)
    Observable<AuthResponseDto> login(@Query("username") String login,
                                      @Query("password") String password,
                                      @Query(NetworkConstants.GRANT_TYPE) String grantType,
                                      @Query(NetworkConstants.CLIENT_ID) String clientId);

    @POST(SIGNUP_URL)
    Observable<UserDto> signup(@Body RegisterDto registerDto);

    @GET(USER_GET_INFO)
    Observable<UserInfoDto> getUserInfo();

    @GET(USER_ORDERS)
    Observable<List<OrderDto>> getUserOrders(@Path("user_id") long userId);

    @POST(USER_ORDERS)
    Observable<OrderDto> addOrder(@Path("user_id") long userId,
                                  @Body NewOrderDto orderDto);

    @POST(USER_SHOPS)
    Observable<ShopDto> addShop(@Path("user_id") long userId,
                                @Body ShopDto shopDto);

    @POST(USER_ADDRESSES)
    Observable<AddressDto> addAddress(@Path("user_id") long userId,
                                @Body AddressDto addressDto);

    @GET(USER_SHOPS)
    Observable<List<ShopDto>> getUserShops(@Path("user_id") long userId);

    @GET(USER_ADDRESSES)
    Observable<List<AddressDto>> getUserAddresses(@Path("user_id") long userId);

    @GET(ORDER_INFO)
    Observable<OrderDto> getOrder(@Path("order_id") long orderId);

}