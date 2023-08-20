package com.shadowwingz.wanandroid.profile.data.model

import androidx.annotation.DrawableRes
import com.shadowwingz.wanandroid.R

data class ProfileUiModel(
  @DrawableRes val header: Int = R.drawable.ic_avatar,
  var username: String = "",
  var coinCount: Int = 0,
  var level: Int = 0,
  var rank: String = "",
)

fun ProfileResponseData.toProfileUiModel(): ProfileUiModel {
  return ProfileUiModel(
    header = R.drawable.ic_avatar,
    username = username,
    coinCount = coinCount,
    level = level,
    rank = rank,
  )
}