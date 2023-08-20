package com.shadowwingz.wanandroid.core.data.interceptor

import com.shadowwingz.wanandroid.core.data.PreferenceManager
import com.shadowwingz.wanandroid.core.data.interceptor.ReceivedCookiesInterceptor.Companion.KEY_COOKIES
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AddCookiesInterceptor @Inject constructor(
  private val preferenceManager: PreferenceManager
) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val builder = chain.request().newBuilder()
    preferenceManager.getStringSet(KEY_COOKIES).onEach {
      builder.addHeader("Cookie", it)
    }
    return chain.proceed(builder.build())
  }
}