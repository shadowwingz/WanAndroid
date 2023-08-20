package com.shadowwingz.wanandroid.profile.data.api

import com.shadowwingz.wanandroid.profile.data.model.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProfileService {

  @GET("lg/coin/userinfo/json")
  suspend fun query(): Response<ProfileResponse>
}