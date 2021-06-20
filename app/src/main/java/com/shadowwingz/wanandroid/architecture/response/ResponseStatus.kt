package com.shadowwingz.wanandroid.architecture.response;

/**
 * created by shadowwingz on 2021-06-20 14:50
 */
data class ResponseStatus(
  val responseCode: String?,
  val success: Boolean = true,
  val source: ResultSource = ResultSource.NETWORK
)
