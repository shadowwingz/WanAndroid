package com.shadowwingz.wanandroid.home.data.article.model

data class ArticleResponse(
  val data: ArticleData,
  val errorCode: Int,
  val errorMsg: String
)