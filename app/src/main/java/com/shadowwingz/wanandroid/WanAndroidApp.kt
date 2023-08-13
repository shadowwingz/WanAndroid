package com.shadowwingz.wanandroid

import android.app.Application
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WanAndroidApp : Application() {
  
  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
    
    initX5WebView()
  }
  
  private fun initX5WebView() {
    val map = mutableMapOf<String, Any>()
    map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
    map[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
    QbSdk.initTbsSettings(map)
  }
}