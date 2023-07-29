package com.shadowwingz.wanandroid

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
import timber.log.Timber

class WanAndroidApp : Application() {
  
  override fun onCreate() {
    super.onCreate()
    context = this
    Timber.plant(Timber.DebugTree())
    
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