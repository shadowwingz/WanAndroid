package com.shadowwingz.wanandroid.ui.web;

import android.os.Bundle
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.WanAndroidApp
import com.shadowwingz.wanandroid.base.BaseActivity
import com.shadowwingz.wanandroid.utils.LogUtil
import com.shadowwingz.wanandroid.utils.web.WebInstance
import kotlinx.android.synthetic.main.activity_web.*

/**
 * created by shadowwingz on 2021-09-05 12:00
 */
class WebActivity : BaseActivity() {
  
  override fun getLayoutId(): Int = R.layout.activity_web
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_web)
    
    val url = intent.getStringExtra("url") ?: ""
    LogUtil.d(url)
    
    val mWebView = WebInstance.create(WanAndroidApp.context)
    flContainer.addView(mWebView)
    mWebView.loadUrl(url)
  }
}
