package com.github.handioq.diberapp.network;

import com.github.handioq.diberapp.BuildConfig;
import com.github.handioq.diberapp.util.AuthPreferences;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.github.handioq.diberapp.network.NetworkConstants.HEADER_AUTHORIZATION;

public class NetworkService {

    private static final String USER_AGENT_HEADER = "Retrofit-Diber-App";
    private static final String HEADER_USER_AGENT = "User-Agent";
    public static final int READ_TIMEOUT = 60;
    public static final int CONNECT_TIMEOUT = 60;

    private ApiService apiService;

    public static <E> Observable.Transformer<E, E> applyScheduler() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public NetworkService() {

        Interceptor interceptor = chain -> {
            Request newRequest = chain.request()
                    .newBuilder()
                    .addHeader(HEADER_AUTHORIZATION, NetworkConstants.HEADER_AUHTORIZATION_VALUE)
                    .addHeader(HEADER_USER_AGENT, USER_AGENT_HEADER).build();
            return chain.proceed(newRequest);
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BODY);
        builder.interceptors().add(loggingInterceptor);

        builder.interceptors().add(interceptor);

        builder.interceptors().add(chain -> {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder();

            if (AuthPreferences.token != null) {
                requestBuilder = original.newBuilder()
                        .header(HEADER_AUTHORIZATION, "Bearer " + AuthPreferences.getUserToken());
            }

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }

}
