package com.shadowwingz.wanandroid.ui.web;

import android.content.Context
import android.os.Bundle
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.base.BaseActivity
import com.shadowwingz.wanandroid.databinding.ActivityWebBinding
import com.shadowwingz.wanandroid.utils.LogUtil
import com.shadowwingz.wanandroid.utils.web.WebInstance
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * created by shadowwingz on 2021-09-05 12:00
 */
@AndroidEntryPoint
class WebActivity : BaseActivity() {

  @ApplicationContext
  @Inject
  lateinit var context: Context

  private val binding by lazy { ActivityWebBinding.inflate(layoutInflater) }

  override fun getLayoutId(): Int = R.layout.activity_web
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    
    val url = intent.getStringExtra("url") ?: ""
    LogUtil.d(url)

    WebInstance.create(context).apply {
      binding.flContainer.addView(this)
      loadUrl(url)
    }
  }
}
