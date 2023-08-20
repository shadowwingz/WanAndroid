package com.shadowwingz.wanandroid.account.data.model;

/**
 * created by shadowwingz on 2021-06-21 22:49
 */
data class LoginResponse(
  val data: LoginResponseData,
  val errorCode: Int,
  val errorMsg: String
)

fun LoginResponseData.toLoginUiModel(): LoginUiModel {
  return LoginUiModel(
    id = id,
    username = username,
    password = password,
    coinCount = coinCount
  )
}