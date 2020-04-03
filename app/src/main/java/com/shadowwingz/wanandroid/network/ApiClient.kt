package com.shadowwingz.wanandroid.network

import com.shadowwingz.wanandroid.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun retrofit(): ApiStores {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            // log 信息拦截器
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        val okHttpClient = builder.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiStores.API_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(ApiStores::class.java)
    }
}