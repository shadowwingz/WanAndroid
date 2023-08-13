package com.shadowwingz.wanandroid.home.data.banner.model

import androidx.annotation.Keep

@Keep
data class BannerData(
  val desc: String,
  val id: Int,
  val imagePath: String,
  val isVisible: Int,
  val order: Int,
  val title: String,
  val type: Int,
  val url: String
)