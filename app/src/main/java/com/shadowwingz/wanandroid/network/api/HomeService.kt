package com.shadowwingz.wanandroid.network.api

import com.shadowwingz.wanandroid.bean.ArticleBean
import com.shadowwingz.wanandroid.bean.BannerBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 主页的网络请求
 */
interface HomeService {
  /**
   * 首页文章列表
   */
  @GET("article/list/{pageId}/json")
  fun getArticleList(@Path("pageId") page: Int): Call<ArticleBean>
  
  /**
   * 首页 banner
   */
  @GET("banner/json")
  fun getBanner(): Call<BannerBean>
}