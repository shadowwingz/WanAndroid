package com.shadowwingz.wanandroid.di

import android.content.Context
import com.shadowwingz.wanandroid.BuildConfig
import com.shadowwingz.wanandroid.core.api.APIs
import com.shadowwingz.wanandroid.core.data.PreferenceManager
import com.shadowwingz.wanandroid.core.data.interceptor.ReceivedCookiesInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class CoreDataModule {

  @Provides
  fun provideRetrofit(
    client: OkHttpClient,
    factory: Converter.Factory,
    baseUrl: String
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(baseUrl)
      .callFactory(client)
      .addConverterFactory(factory)
      .build()
  }

  @Provides
  fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    receivedCookiesInterceptor: ReceivedCookiesInterceptor
  ): OkHttpClient =
    OkHttpClient
      .Builder()
      .addInterceptor(httpLoggingInterceptor)
      .addInterceptor(receivedCookiesInterceptor)
      .build()

  @Provides
  fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
      level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
      } else {
        HttpLoggingInterceptor.Level.NONE
      }
    }

  @Provides
  fun provideFactory(): Converter.Factory = GsonConverterFactory.create()

  @Provides
  fun provideBaseUrl(): String = APIs.BASE_URL

  @Provides
  fun provideSharedPreferences(
    @ApplicationContext context: Context
  ): PreferenceManager = PreferenceManager.getInstance(context)
}