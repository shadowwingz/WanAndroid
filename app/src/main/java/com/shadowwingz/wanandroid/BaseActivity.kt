package com.shadowwingz.wanandroid

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.noober.background.BackgroundLibrary

abstract class BaseActivity : AppCompatActivity() {
  
  abstract fun getLayoutId(): Int
  
  override fun onCreate(savedInstanceState: Bundle?) {
    BackgroundLibrary.inject(this)
    super.onCreate(savedInstanceState)
    if (getLayoutId() > 0) {
      setContentView(getLayoutId())
    }
    
    if (Build.VERSION.SDK_INT >= 21) {
      val decorView = window.decorView
      val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
      decorView.systemUiVisibility = option
      window.statusBarColor = Color.TRANSPARENT
    }
    supportActionBar?.hide()
  }
}