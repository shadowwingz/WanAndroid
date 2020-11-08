package com.shadowwingz.wanandroid.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ServiceCreator {

  private const val BASE_URL = "https://www.wanandroid.com/"
  
  private val loggingInterceptor = HttpLoggingInterceptor(HttpLogger())

  private val httpClient = OkHttpClient.Builder()

  private val builder = Retrofit.Builder()
          .baseUrl(BASE_URL)
          .client(httpClient.addNetworkInterceptor(
                          loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build())
          .addConverterFactory(ScalarsConverterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())

  private val retrofit = builder.build()

  fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}