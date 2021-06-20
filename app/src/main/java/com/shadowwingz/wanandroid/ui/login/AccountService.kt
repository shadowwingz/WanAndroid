package com.shadowwingz.wanandroid.ui.login

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AccountService {
  @POST("user/login")
  @FormUrlEncoded
  fun login(@Field("username") username: String, @Field("password") password: String): Call<String>
}