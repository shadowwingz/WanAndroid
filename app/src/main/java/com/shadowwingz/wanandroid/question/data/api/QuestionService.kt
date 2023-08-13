package com.shadowwingz.wanandroid.question.data.api

import com.shadowwingz.wanandroid.question.data.model.QuestionBean
import retrofit2.http.GET
import retrofit2.http.Path

interface QuestionService {
  @GET("wenda/list/{pageId}/json")
  suspend fun fetchQuestions(@Path("pageId") page: Int): QuestionBean
}