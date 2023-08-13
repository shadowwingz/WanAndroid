package com.shadowwingz.wanandroid.di.question

import com.shadowwingz.wanandroid.core.api.APIs
import com.shadowwingz.wanandroid.question.data.api.QuestionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter.Factory
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class QuestionModule {

  @Provides
  fun provideQuestionService(client: OkHttpClient, factory: Factory): QuestionService {
    return Retrofit.Builder()
      .baseUrl(APIs.BASE_URL)
      .callFactory(client)
      .addConverterFactory(factory)
      .build()
      .create(QuestionService::class.java)
  }
}