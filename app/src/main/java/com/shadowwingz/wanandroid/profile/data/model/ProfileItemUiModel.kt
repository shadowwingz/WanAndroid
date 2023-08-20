package com.shadowwingz.wanandroid.profile.data.model

import androidx.annotation.DrawableRes

data class ProfileItemUiModel(
  val id: String = "",
  @DrawableRes var icon: Int,
  val leftDesc: String,
  var rightDesc: String = ""
)