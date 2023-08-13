package com.shadowwingz.wanandroid.home.ui.banner

import com.shadowwingz.wanandroid.bean.BannerData

data class BannerUiModel(
  val description: String,
  val imagePath: String,
  val title: String,
  val url: String
)

fun BannerData.toBannerUiModel(): BannerUiModel {
  return BannerUiModel(
    description = desc,
    imagePath = imagePath,
    title = title,
    url = url
  )
}
