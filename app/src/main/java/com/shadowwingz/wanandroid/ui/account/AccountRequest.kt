package com.shadowwingz.wanandroid.ui.account;

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.shadowwingz.wanandroid.architecture.domain.BaseRequest
import com.shadowwingz.wanandroid.architecture.domain.DataRepository
import com.shadowwingz.wanandroid.architecture.response.DataResult
import com.shadowwingz.wanandroid.bean.User

/**
 * created by shadowwingz on 2021-06-20 22:58
 */
class AccountRequest : BaseRequest(), DefaultLifecycleObserver {
  
  private val tokenLiveData: MutableLiveData<DataResult<AccountBean>> = MutableLiveData()
  
  fun requestLogin(user: User) {
    DataRepository.requestLogin(user, object : DataResult.Result<AccountBean> {
      override fun onResult(dataResult: DataResult<AccountBean>?) {
        tokenLiveData.postValue(dataResult)
      }
    })
  }
  
  private fun cancelLogin() {
    DataRepository.cancelLogin()
  }
  
  override fun onStop(owner: LifecycleOwner) {
    super.onStop(owner)
    cancelLogin()
  }
}
