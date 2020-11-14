package com.shadowwingz.wanandroid.base

import android.app.Application
import android.content.Context

class WanAndroidApplication : Application() {
  
  override fun onCreate() {
    super.onCreate()
    context = this
  }
  
  companion object {
    lateinit var context: Context
  }
}