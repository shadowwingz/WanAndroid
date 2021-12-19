package com.shadowwingz.wanandroid.ui.account;

import androidx.lifecycle.MutableLiveData
import com.shadowwingz.wanandroid.network.BaseRequest
import com.shadowwingz.wanandroid.network.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * created by shadowwingz on 2021-06-20 22:58
 */
class AccountRequest : BaseRequest() {
  
  val tokenLiveData: MutableLiveData<AccountBean> = MutableLiveData()
  
  suspend fun requestLogin(userBean: UserBean) {
    withContext(Dispatchers.IO) {
      tokenLiveData.postValue(DataRepository.requestLogin(userBean))
    }
  }
  
}
