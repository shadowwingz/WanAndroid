package com.shadowwingz.wanandroid.home.data.api

import com.shadowwingz.wanandroid.bean.BannerBean
import retrofit2.Response
import retrofit2.http.GET

interface BannerSearchService {

  @GET("banner/json")
  suspend fun getBanner(): Response<BannerBean>
}