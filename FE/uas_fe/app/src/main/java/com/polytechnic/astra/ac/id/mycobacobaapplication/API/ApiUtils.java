package com.polytechnic.astra.ac.id.mycobacobaapplication.API;

import com.polytechnic.astra.ac.id.mycobacobaapplication.API.Service.UserService;

public class ApiUtils {

    //public static final String API_BASE_URL = "http://10.1.9.219:8080/";
    public static final String API_BASE_URL = "http://192.168.139.30:8080/";

    public ApiUtils() {
    }

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_BASE_URL).create(UserService.class);
    }

}
