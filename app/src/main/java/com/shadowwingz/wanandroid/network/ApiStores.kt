package com.shadowwingz.wanandroid.network

import com.shadowwingz.wanandroid.bean.ArticleBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiStores {
  companion object {
    // base url
    const val API_SERVER_URL = "https://www.wanandroid.com/"
  }

  @GET("article/list/{pageId}/json")
  fun loadData(@Path("pageId") page: String): Observable<ArticleBean>
}