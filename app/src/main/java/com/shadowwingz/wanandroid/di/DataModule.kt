package com.shadowwingz.wanandroid.di

import com.shadowwingz.wanandroid.network.APIs
import com.shadowwingz.wanandroid.ui.article.banner.BannerSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

  @Provides
  fun provideBannerSearchService(
    client: OkHttpClient,
    factory: Converter.Factory
  ): BannerSearchService = Retrofit.Builder()
    .baseUrl(APIs.BASE_URL)
    .callFactory(client)
    .addConverterFactory(factory)
    .build()
    .create(BannerSearchService::class.java)
}