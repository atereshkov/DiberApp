package com.github.handioq.diberapp.network;

public class NetworkConstants {

    public static final String SERVER_URL = "http://192.168.1.2:8080/";
    //public static final String SERVER_URL = "http://private-7243c-rnsurvey.apiary-mock.com";

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_AUHTORIZATION_VALUE = "Basic Y2xpZW50YXBwOjEyMzQ1Ng==";

    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_ID_VALUE = "clientapp";
    public static final String GRANT_TYPE = "grant_type";
    public static final String GRANT_TYPE_VALUE = "password";

    public static final String HEADER_APP_JSON = "application/json";

    private NetworkConstants() {
    }

}
