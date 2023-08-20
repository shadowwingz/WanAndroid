package com.shadowwingz.wanandroid.profile.data.model

import android.app.Activity

data class ProfileItemUiModel(
  var icon: Int,
  val desc: String,
  var onClick: ((activity: Activity?) -> Unit)? = null
)