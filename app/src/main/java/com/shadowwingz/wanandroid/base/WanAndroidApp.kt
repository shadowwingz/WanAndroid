package com.shadowwingz.wanandroid.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.shadowwingz.wanandroid.database.WanAndroidDatabase
import com.shadowwingz.wanandroid.network.WanAndroidRepository

class WanAndroidApp : Application() {
  
  override fun onCreate() {
    super.onCreate()
    context = this

    repository = WanAndroidRepository(WanAndroidDatabase.getDatabase(this).wanandroidDao())
  }
  
  companion object {
    lateinit var repository: WanAndroidRepository

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
  }
}