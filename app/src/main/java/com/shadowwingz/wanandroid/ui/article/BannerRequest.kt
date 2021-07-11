package com.shadowwingz.wanandroid.ui.article;

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.shadowwingz.wanandroid.architecture.domain.BaseRequest
import com.shadowwingz.wanandroid.architecture.domain.DataRepository
import com.shadowwingz.wanandroid.architecture.response.DataResult
import com.shadowwingz.wanandroid.bean.BannerBean

/**
 * created by shadowwingz on 2021-07-11 17:13
 */
class BannerRequest : BaseRequest(), DefaultLifecycleObserver {

  val bannerLiveData: MutableLiveData<DataResult<BannerBean>> = MutableLiveData()

  fun getBanner() {
    DataRepository.requestBanner(object : DataResult.Result<BannerBean> {
      override fun onResult(dataResult: DataResult<BannerBean>?) {
        bannerLiveData.postValue(dataResult)
      }
    })
  }
}
