package com.shadowwingz.utils

import android.util.Log

/**
 * Log 工具类
 */
object SLog {

    private const val TAG: String = "shadowwingz"

    fun d(msg: String) {
        Log.d(TAG, msg)
    }
}