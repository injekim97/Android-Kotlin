package com.example.retrofit2

import com.google.gson.annotations.SerializedName //import 추가로 Unresolved reference: SerializedName 에러 해결


data class UserInfo(
    @SerializedName("login")
    val userId: String,
    val blog: String,
    val bio: String,
    val public_repos: Int,
)