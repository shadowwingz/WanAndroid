package com.shadowwingz.wanandroid.di.question

import com.shadowwingz.wanandroid.question.data.api.QuestionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class QuestionModule {

  @Provides
  fun provideQuestionService(
    retrofit: Retrofit
  ): QuestionService = retrofit.create(QuestionService::class.java)
}