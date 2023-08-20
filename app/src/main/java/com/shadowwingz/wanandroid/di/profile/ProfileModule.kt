package com.shadowwingz.wanandroid.di.profile

import com.shadowwingz.wanandroid.core.WanAndroidDatabase
import com.shadowwingz.wanandroid.profile.data.ProfileDao
import com.shadowwingz.wanandroid.profile.data.api.ProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ProfileModule {

  @Provides
  fun provideProfileService(
    retrofit: Retrofit
  ): ProfileService = retrofit.create(ProfileService::class.java)

  @Provides
  fun provideProfileDao(
    wanAndroidDatabase: WanAndroidDatabase
  ): ProfileDao = wanAndroidDatabase.profileDao()
}