package com.shadowwingz.wanandroid.utils.web;

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import com.shadowwingz.wanandroid.widget.X5WebView
import com.tencent.smtt.export.external.interfaces.IX5WebSettings
import com.tencent.smtt.sdk.CookieManager
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView


/**
 * created by shadowwingz on 2021-09-04 23:35
 */
class WebInstance private constructor() {
  
  companion object {
    
    @JvmStatic
    fun create(context: Context): X5WebView {
      return X5WebView(context).apply {
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setBackgroundColor(0)
        getBackground().setAlpha(0)
        setOverScrollMode(WebView.OVER_SCROLL_NEVER)
        getView().setOverScrollMode(View.OVER_SCROLL_NEVER)
        val webSetting: WebSettings = getSettings()
        webSetting.setJavaScriptEnabled(true)
        webSetting.setJavaScriptCanOpenWindowsAutomatically(false)
        webSetting.setAllowFileAccess(true)
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS)
        webSetting.setSupportZoom(false)
        webSetting.setBuiltInZoomControls(false)
        webSetting.setUseWideViewPort(true)
        webSetting.setLoadWithOverviewMode(true)
        webSetting.setAppCacheEnabled(true)
        webSetting.setDomStorageEnabled(true)
        webSetting.setGeolocationEnabled(true)
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE)
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND)
        webSetting.setCacheMode(WebSettings.LOAD_DEFAULT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          webSetting.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)
        }
        settingsExtension?.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
        }
      }
    }
  }
  
}
