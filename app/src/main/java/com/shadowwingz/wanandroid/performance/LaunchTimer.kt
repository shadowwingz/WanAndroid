package com.shadowwingz.wanandroid.performance

import com.shadowwingz.wanandroid.utils.LogUtil

object LaunchTimer {
  
  private var time: Long = 0
  
  fun startRecord() {
    time = System.currentTimeMillis();
  }
  
  fun endRecord() {
    var cost = System.currentTimeMillis() - time
    LogUtil.d("cost $cost")
  }
  
  fun endRecord(msg: String) {
    var cost = System.currentTimeMillis() - time
    LogUtil.d("$msg cost $cost")
  }
}