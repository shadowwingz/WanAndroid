package com.shadowwingz.wanandroid.core.data

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

private const val START_SPLIT_STRING = "Expires="
private const val END_SPLIT_STRING = "; Path"

val String.expireDate: String
  get() = substring(
    indexOf(START_SPLIT_STRING) + START_SPLIT_STRING.length,
    indexOf(END_SPLIT_STRING)
  )

val expireDateFormat: SimpleDateFormat = SimpleDateFormat(
  "EEE, dd-MMM-yyyy HH:mm:ss z",
  Locale.ENGLISH
).apply {
  timeZone = TimeZone.getTimeZone("GMT")
}