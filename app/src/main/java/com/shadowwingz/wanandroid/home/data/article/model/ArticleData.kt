package com.shadowwingz.wanandroid.home.data.article.model

import com.google.gson.annotations.SerializedName

data class ArticleData(
  val curPage: Int,
  @SerializedName("datas")
  val articleListBean: List<ArticleListBean>,
  val offset: Int,
  val over: Boolean,
  val pageCount: Int,
  val size: Int,
  val total: Int
)