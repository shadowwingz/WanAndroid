package com.shadowwingz.wanandroid.di.account

import com.shadowwingz.wanandroid.account.data.api.AccountService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class AccountModule {

  @Provides
  fun provideAccountService(
    retrofit: Retrofit
  ): AccountService = retrofit.create(AccountService::class.java)
}