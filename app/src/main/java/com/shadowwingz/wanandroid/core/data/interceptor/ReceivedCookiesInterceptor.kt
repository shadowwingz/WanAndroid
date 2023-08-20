package com.shadowwingz.wanandroid.core.data.interceptor

import com.shadowwingz.wanandroid.core.data.PreferenceManager
import com.shadowwingz.wanandroid.core.data.expireDate
import com.shadowwingz.wanandroid.core.data.expireDateFormat
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.util.Date
import javax.inject.Inject

class ReceivedCookiesInterceptor @Inject constructor(
  private val preferenceManager: PreferenceManager
) : Interceptor {

  companion object {
    const val KEY_COOKIES = "cookies"
    private const val KEY_EXPIRE_DATE = "expire_date"
  }

  private val String.isLoginUrl
    get() = contains("user/login")

  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val originResponse = chain.proceed(request)
    val cookies = originResponse.headers("Set-Cookie")
    if (request.url().toString().isLoginUrl && cookies.isNotEmpty()) {
      Timber.d("保存 cookies: ${cookies.toSet()}")
      preferenceManager.putStringSet(KEY_COOKIES, cookies.toSet())

      updateExpireDate(cookies)
    }
    return originResponse
  }

  /**
   * 从原始 Cookies 中解析并保存过期时间 expireDate
   */
  private fun updateExpireDate(cookies: MutableList<String>) {
    val cookie = cookies.find {
      it.contains("loginUserName")
    } ?: ""
    if (cookie.isNotEmpty()) {
      val expireDateStr = cookie.expireDate
      preferenceManager.putString(KEY_EXPIRE_DATE, expireDateStr)
      calculateExpireDate(expireDateStr)
    }
  }

  private fun calculateExpireDate(expireDateStr: String) {
    val expireDate = expireDateFormat.parse(expireDateStr)
    expireDate?.let {
      val currentDate = Date()
      Timber.d("过期时间: $expireDateStr")
      Timber.d("当前时间: ${expireDateFormat.format(currentDate)}")
      if (currentDate.after(expireDate)) {
        Timber.d("登录失效")
      } else {
        val betweenDays = (expireDate.time - currentDate.time) / (1000 * 60 * 60 * 24)
        Timber.d("距离失效时间还有 $betweenDays 天")
      }
    }
  }
}