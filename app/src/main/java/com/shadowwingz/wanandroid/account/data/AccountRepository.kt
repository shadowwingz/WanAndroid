package com.shadowwingz.wanandroid.account.data

import com.shadowwingz.wanandroid.account.data.model.LoginResponse
import com.shadowwingz.wanandroid.account.data.model.toLoginUiModel
import timber.log.Timber
import javax.inject.Inject

class AccountRepository @Inject constructor(
  private val accountDataRemoteSource: AccountDataRemoteSource,
  private val accountDataLocalSource: AccountDataLocalSource
) {

  suspend fun login(username: String, password: String): Result<LoginResponse> {
    return accountDataRemoteSource
      .login(username, password)
      .onSuccess {
        accountDataLocalSource.saveAccount(it.data.toLoginUiModel())
      }
      .onFailure {
        Timber.d(it)
      }
  }
}