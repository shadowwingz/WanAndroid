package com.shadowwingz.wanandroid.ui.question

import com.shadowwingz.wanandroid.bean.QuestionBean
import retrofit2.http.GET
import retrofit2.http.Path

interface QuestionService {
  @GET("wenda/list/{pageId}/json")
  suspend fun fetchQuestions(@Path("pageId") page: Int): QuestionBean
}