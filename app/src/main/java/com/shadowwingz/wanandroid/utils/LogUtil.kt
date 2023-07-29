package com.shadowwingz.wanandroid.utils

import timber.log.Timber

/**
 * Log 工具类
 */
object LogUtil {
  
  private const val TAG: String = "shadowwingz"
  
  @JvmStatic
  fun d(msg: String) {
    Timber.d(msg)
  }
}