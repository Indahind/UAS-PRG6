package com.polytechnic.astra.ac.id.mycobacobaapplication.API.Service;

import com.polytechnic.astra.ac.id.mycobacobaapplication.API.VO.UserListVO;
import com.polytechnic.astra.ac.id.mycobacobaapplication.API.VO.UserVO;
import com.polytechnic.astra.ac.id.mycobacobaapplication.Model.UserModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @GET("mhs/login")
    Call<UserVO> login (@Query("nim") String nim, @Query("password") String password);

    @GET("mhs/getUsers")
    Call<UserListVO> getAllUser();
    @GET("mhs/getNIM")
    Call<UserListVO> getAllNIM();
    @POST("mhs/saveUser")
    Call<UserVO> saveUser(@Body UserModel user);

    @POST("mhs/updateUser")
    Call<UserVO> updateUser(@Body UserModel updateUser);

    @POST("mhs/deleteUser/{username}")
    Call<UserVO> deleteUser(@Path("username") String username);
}
