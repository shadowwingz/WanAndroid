package com.shadowwingz.wanandroid.account.ui;

import androidx.lifecycle.ViewModel
import com.shadowwingz.wanandroid.account.data.model.UserBean
import com.shadowwingz.wanandroid.account.domain.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

/**
 * created by shadowwingz on 2021-06-21 22:17
 */
@HiltViewModel
class AccountViewModel @Inject constructor(
  private val loginUseCase: LoginUseCase
) : ViewModel() {

  private val _loginResult: MutableSharedFlow<Boolean> = MutableSharedFlow()
  val loginResult: SharedFlow<Boolean> = _loginResult

  suspend fun login(username: String, password: String) {
    loginUseCase(UserBean(username, password)).map {
      _loginResult.emit(it)
    }
  }
}
