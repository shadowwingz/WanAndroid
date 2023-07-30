package com.shadowwingz.wanandroid.ui.article

import com.shadowwingz.wanandroid.bean.ArticleBean
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {
  
  @GET("article/list/{pageId}/json")
  suspend fun searchRepos(@Path("pageId") page: Int): ArticleBean
}