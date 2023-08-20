package com.shadowwingz.wanandroid.di.coroutines

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CoroutinesModule {

  @DefaultDispatcher
  @Provides
  fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

  @IoDispatcher
  @Provides
  fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

  @MainDispatcher
  @Provides
  fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

  @ApplicationScope
  @Singleton
  @Provides
  fun providesApplicationScope(
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
  ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)
}