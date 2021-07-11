package com.shadowwingz.wanandroid.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class WanAndroidApp : Application() {

  override fun onCreate() {
    super.onCreate()
    context = this
  }

  companion object {
    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
  }
}