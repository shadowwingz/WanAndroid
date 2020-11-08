package com.shadowwingz.wanandroid.network

import com.shadowwingz.wanandroid.utils.LogUtil
import okhttp3.logging.HttpLoggingInterceptor

class HttpLogger : HttpLoggingInterceptor.Logger {
  override fun log(message: String) {
    LogUtil.d("OkHttp $message")
  }
}