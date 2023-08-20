package com.shadowwingz.wanandroid.account.data

import com.shadowwingz.wanandroid.account.data.model.LoginUiModel
import com.shadowwingz.wanandroid.core.data.PreferenceManager
import com.shadowwingz.wanandroid.di.coroutines.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountDataLocalSource @Inject constructor(
  private val preferenceManager: PreferenceManager,
  @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

  companion object {
    private const val KEY_USERNAME = "username"
    private const val KEY_COIN_COUNT = "coin_count"
  }

  suspend fun saveAccount(loginUiModel: LoginUiModel) {
    withContext(dispatcher) {
      preferenceManager.apply {
        putString(KEY_USERNAME, loginUiModel.username)
        putInt(KEY_COIN_COUNT, loginUiModel.coinCount)
      }
    }
  }
}