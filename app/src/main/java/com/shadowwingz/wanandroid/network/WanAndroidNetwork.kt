package com.shadowwingz.wanandroid.network

import com.shadowwingz.wanandroid.network.api.WanAndroidService
import com.shadowwingz.wanandroid.utils.LogUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object WanAndroidNetwork {

  private val articleListService = ServiceCreator.create(WanAndroidService::class.java)

  suspend fun fetchArticleList(pageId: Int) = articleListService.getArticleList(pageId).await()

  suspend fun fetchBanner() = articleListService.getBanner().await()

  private suspend fun <T> Call<T>.await(): T {
    return suspendCoroutine { continuation ->
      enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
          LogUtil.d("onFailure ${t.message}")
          //continuation.resumeWithException(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
          val body = response.body()
          LogUtil.d("onResponse $body")
          if (body != null) continuation.resume(body)
          //else continuation.resumeWithException(RuntimeException("response body is null"))
        }
      })
    }
  }
}