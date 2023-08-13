package com.shadowwingz.wanandroid.question.data.model

import com.google.gson.annotations.SerializedName

data class Data(
  var curPage: Int,
  @SerializedName("datas")
  var data: List<Question>,
  var offset: Int,
  var over: Boolean,
  var pageCount: Int,
  var size: Int,
  var total: Int
)