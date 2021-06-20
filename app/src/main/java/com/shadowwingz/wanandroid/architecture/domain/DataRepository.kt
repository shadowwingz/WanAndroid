package com.shadowwingz.wanandroid.architecture.domain

import com.shadowwingz.wanandroid.architecture.response.DataResult
import com.shadowwingz.wanandroid.architecture.response.ResponseStatus
import com.shadowwingz.wanandroid.architecture.response.ResultSource
import com.shadowwingz.wanandroid.architecture.testpage.TestWanAndroidService
import com.shadowwingz.wanandroid.bean.ArticleBean
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataRepository {

  private val retrofit: Retrofit

  init {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder()
      .connectTimeout(8, TimeUnit.SECONDS)
      .readTimeout(8, TimeUnit.SECONDS)
      .writeTimeout(8, TimeUnit.SECONDS)
      .addInterceptor(logging)
      .build()
    retrofit = Retrofit.Builder()
      .baseUrl(APIs.BASE_URL)
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  private var mArticleCall: Call<ArticleBean>? = null

  fun fetchArticle(pageId: Int, result: DataResult.Result<ArticleBean>) {
    mArticleCall = retrofit.create(TestWanAndroidService::class.java).getArticleList(pageId)
    mArticleCall?.enqueue(object : Callback<ArticleBean> {
      override fun onResponse(call: Call<ArticleBean>, response: Response<ArticleBean>) {
        val responseStatus = ResponseStatus(response.code().toString(), response.isSuccessful, ResultSource.NETWORK)
        result.onResult(DataResult(response.body(), responseStatus))
      }

      override fun onFailure(call: Call<ArticleBean>, t: Throwable) {
        result.onResult(DataResult(null, ResponseStatus(t.message, false, ResultSource.NETWORK)))
        mArticleCall = null
      }
    })
  }

  fun cancelFetch() {
    mArticleCall?.cancel()
    mArticleCall = null
  }
}