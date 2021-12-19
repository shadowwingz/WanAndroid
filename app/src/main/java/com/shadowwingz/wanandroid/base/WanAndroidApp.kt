package com.shadowwingz.wanandroid.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk


class WanAndroidApp : Application() {
  
  override fun onCreate() {
    super.onCreate()
    context = this
    
    initX5WebView()
  }
  
  private fun initX5WebView() {
    val map = mutableMapOf<String, Any>()
    map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
    map[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
    QbSdk.initTbsSettings(map)
  }
  
  companion object {
    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
  }
}