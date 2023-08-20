package com.shadowwingz.wanandroid.account.data

import com.shadowwingz.wanandroid.account.data.api.AccountService
import com.shadowwingz.wanandroid.account.data.model.LoginResponse
import safeApiCall
import java.io.IOException
import javax.inject.Inject

class AccountDataRemoteSource @Inject constructor(
  private val accountService: AccountService
) {

  suspend fun login(username: String, password: String) = safeApiCall(
    call = { requestLogin(username, password) },
    errorMessage = "登录失败"
  )

  private suspend fun requestLogin(username: String, password: String): Result<LoginResponse> {
    val response = accountService.login(username, password)
    if (response.isSuccessful) {
      val body = response.body()
      if (body != null) {
        return Result.success(body)
      }
    }
    return Result.failure(IOException("${response.code()} ${response.message()}"))
  }
}