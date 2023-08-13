package com.shadowwingz.wanandroid.home.data.article

import com.shadowwingz.wanandroid.core.api.APIs
import com.shadowwingz.wanandroid.ui.account.AccountBean
import com.shadowwingz.wanandroid.ui.account.AccountService
import com.shadowwingz.wanandroid.ui.account.UserBean
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

  suspend fun requestLogin(userBean: UserBean): AccountBean {
    return retrofit.create(AccountService::class.java).login(userBean.name, userBean.password)
  }
  
}