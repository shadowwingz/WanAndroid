package com.shadowwingz.wanandroid.ui.account;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * created by shadowwingz on 2021-06-21 22:17
 */
class AccountViewModel : ViewModel() {
  
  val accountRequest = AccountRequest()
  
  fun login(userBean: UserBean) {
    viewModelScope.launch {
      accountRequest.requestLogin(userBean)
    }
  }
}
