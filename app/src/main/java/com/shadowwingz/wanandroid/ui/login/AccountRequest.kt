package com.shadowwingz.wanandroid.ui.login;

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.shadowwingz.wanandroid.architecture.domain.BaseRequest
import com.shadowwingz.wanandroid.architecture.domain.DataRepository

/**
 * created by shadowwingz on 2021-06-20 22:58
 */
class AccountRequest : BaseRequest(), DefaultLifecycleObserver {

  fun requestLogin() {

  }

  private fun cancelLogin() {
    DataRepository.cancelLogin()
  }

  override fun onStop(owner: LifecycleOwner) {
    super.onStop(owner)
    cancelLogin()
  }
}
