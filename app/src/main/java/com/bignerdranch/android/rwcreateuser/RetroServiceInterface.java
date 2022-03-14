package com.bignerdranch.android.rwcreateuser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetroServiceInterface {

    @POST("users")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 88b377822f5e27fad7bb8c73247388b6b48a9ad116b8e169238e1ca356cff397" })
    Call<UserResponse> createUser(@Body User params);
}
