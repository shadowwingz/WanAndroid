package com.shadowwingz.wanandroid.di

import android.content.Context
import androidx.room.Room
import com.shadowwingz.wanandroid.core.WanAndroidDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

  @Provides
  fun provideDatabase(
    @ApplicationContext context: Context
  ): WanAndroidDatabase = Room.databaseBuilder(
    context,
    WanAndroidDatabase::class.java,
    "wanandroid_database"
  ).build()
}