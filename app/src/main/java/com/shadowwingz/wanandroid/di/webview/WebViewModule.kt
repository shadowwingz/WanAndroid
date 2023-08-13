package com.shadowwingz.wanandroid.di.webview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.shadowwingz.wanandroid.widget.X5WebView
import com.tencent.smtt.export.external.interfaces.IX5WebSettings
import com.tencent.smtt.sdk.CookieManager
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class WebViewModule {
  @SuppressLint("SetJavaScriptEnabled")
  @Provides
  fun provideWebView(@ApplicationContext context: Context): X5WebView {
    return X5WebView(context).apply {
      setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
      setBackgroundColor(0)
      background.alpha = 0
      overScrollMode = WebView.OVER_SCROLL_NEVER
      view.overScrollMode = View.OVER_SCROLL_NEVER
      settings.apply {
        javaScriptEnabled = true
        javaScriptCanOpenWindowsAutomatically = false
        allowFileAccess = true
        layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        setSupportZoom(false)
        builtInZoomControls = false
        useWideViewPort = true
        loadWithOverviewMode = true
        setAppCacheEnabled(true)
        domStorageEnabled = true
        setGeolocationEnabled(true)
        setAppCacheMaxSize(Long.MAX_VALUE)
        pluginState = WebSettings.PluginState.ON_DEMAND
        cacheMode = WebSettings.LOAD_DEFAULT
        mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
      }
      settingsExtension?.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY)
      CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
    }
  }
}