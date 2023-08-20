package com.shadowwingz.wanandroid.di.banner

import com.shadowwingz.wanandroid.home.data.api.BannerSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class BannerModule {

  @Provides
  fun provideBannerSearchService(
    retrofit: Retrofit
  ): BannerSearchService = retrofit.create(BannerSearchService::class.java)
}