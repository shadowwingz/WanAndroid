package com.shadowwingz.wanandroid.network.api

import com.shadowwingz.wanandroid.bean.ArticleBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleListService {
  @GET("article/list/{pageId}/json")
  fun getArticleList(@Path("pageId") page: Int): Call<ArticleBean>
}