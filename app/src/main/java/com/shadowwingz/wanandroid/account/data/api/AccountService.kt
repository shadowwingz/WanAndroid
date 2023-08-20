package com.shadowwingz.wanandroid.account.data.api

import com.shadowwingz.wanandroid.account.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AccountService {
  @FormUrlEncoded
  @POST("user/login")
  suspend fun login(
    @Field("username") username: String,
    @Field("password") password: String
  ): Response<LoginResponse>
}