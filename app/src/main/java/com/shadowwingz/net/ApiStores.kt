package com.shadowwingz.net

import com.shadowwingz.bean.ArticleBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiStores {
    companion object {
        // base url
        const val API_SERVER_URL = "http://www.weather.com.cn/"
    }

    @GET("adat/sk/{pageId}.html")
    fun loadData(@Path("pageId") page: String): Observable<ArticleBean>
}