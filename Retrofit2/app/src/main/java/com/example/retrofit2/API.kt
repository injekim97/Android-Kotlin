package com.example.retrofit2

import retrofit2.http.GET


interface API {
    @GET("users/injekim97")// baseUrl + "user/{유저아이디}"

    fun getUserInfo(

    ): retrofit2.Call<UserInfo>
}