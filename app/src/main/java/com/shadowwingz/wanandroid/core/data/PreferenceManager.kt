package com.shadowwingz.wanandroid.core.data

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager private constructor(context: Context) {

  private val sharedPreferences: SharedPreferences =
    context.applicationContext.getSharedPreferences("wanandroid_pref", Context.MODE_PRIVATE)

  companion object {
    @Volatile
    private var INSTANCE: PreferenceManager? = null

    fun getInstance(context: Context): PreferenceManager =
      INSTANCE ?: synchronized(this) {
        INSTANCE ?: PreferenceManager(context).also { INSTANCE = it }
      }
  }

  fun putString(key: String, value: String) {
    sharedPreferences.edit().putString(key, value).apply()
  }

  fun getString(key: String): String? {
    return sharedPreferences.getString(key, "")
  }

  fun putInt(key: String, value: Int) {
    sharedPreferences.edit().putInt(key, value).apply()
  }

  fun getInt(key: String): Int {
    return sharedPreferences.getInt(key, 0)
  }

  fun putStringSet(key: String, set: Set<String>) {
    sharedPreferences.edit().putStringSet(key, set).apply()
  }
}
