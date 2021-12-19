package com.shadowwingz.wanandroid.ui.account;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shadowwingz.wanandroid.bean.User
import kotlinx.coroutines.launch

/**
 * created by shadowwingz on 2021-06-21 22:17
 */
class AccountViewModel : ViewModel() {
  
  val accountRequest = AccountRequest()
  
  fun login(user: User) {
    viewModelScope.launch {
      accountRequest.requestLogin(user)
    }
  }
}
