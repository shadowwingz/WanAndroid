package com.shadowwingz.wanandroid.ui.article;

import androidx.lifecycle.MutableLiveData
import com.shadowwingz.wanandroid.architecture.domain.BaseRequest
import com.shadowwingz.wanandroid.architecture.domain.DataRepository
import com.shadowwingz.wanandroid.bean.BannerBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * created by shadowwingz on 2021-07-11 17:13
 */
class BannerRequest : BaseRequest() {
  
  var bannerLiveData: MutableLiveData<BannerBean> = MutableLiveData()
  
  suspend fun getBanner() {
    withContext(Dispatchers.IO) {
      bannerLiveData.postValue(DataRepository.requestBanner())
    }
  }
}
