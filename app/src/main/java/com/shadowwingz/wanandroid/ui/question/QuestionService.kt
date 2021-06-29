package com.shadowwingz.wanandroid.ui.question

import com.shadowwingz.wanandroid.bean.QuestionBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuestionService {
  @GET("wenda/list/{pageId}/json")
  fun fetchQuestions(@Path("pageId") page: Int): Call<QuestionBean>
}