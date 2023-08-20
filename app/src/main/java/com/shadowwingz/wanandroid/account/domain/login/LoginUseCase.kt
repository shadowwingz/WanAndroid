package com.shadowwingz.wanandroid.account.domain.login

import com.shadowwingz.wanandroid.account.data.AccountRepository
import com.shadowwingz.wanandroid.core.domain.usebase.CoroutineUseCase
import com.shadowwingz.wanandroid.di.coroutines.IoDispatcher
import com.shadowwingz.wanandroid.account.data.model.UserBean
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoginUseCase @Inject constructor(
  private val accountRepository: AccountRepository,
  @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CoroutineUseCase<UserBean, Result<Boolean>>(dispatcher) {

  override suspend fun execute(parameters: UserBean): Result<Boolean> {
    return accountRepository.login(parameters.username, parameters.password).map {
      it.data.username.isNotEmpty()
    }
  }
}