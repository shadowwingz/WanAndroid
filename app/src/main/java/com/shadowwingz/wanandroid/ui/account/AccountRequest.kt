package com.shadowwingz.wanandroid.ui.account;

import androidx.lifecycle.MutableLiveData
import com.shadowwingz.wanandroid.architecture.domain.BaseRequest
import com.shadowwingz.wanandroid.architecture.domain.DataRepository
import com.shadowwingz.wanandroid.bean.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * created by shadowwingz on 2021-06-20 22:58
 */
class AccountRequest : BaseRequest() {
  
  val tokenLiveData: MutableLiveData<AccountBean> = MutableLiveData()
  
  suspend fun requestLogin(user: User) {
    withContext(Dispatchers.IO) {
      tokenLiveData.postValue(DataRepository.requestLogin(user))
    }
  }
  
}
