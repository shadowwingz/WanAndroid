package com.shadowwingz.wanandroid.ui.article.banner;

import androidx.lifecycle.MutableLiveData
import com.shadowwingz.wanandroid.bean.BannerBean
import javax.inject.Inject

/**
 * created by shadowwingz on 2021-07-11 17:13
 */
class BannerRequest @Inject constructor() {

  var bannerLiveData: MutableLiveData<BannerBean> = MutableLiveData()

  suspend fun getBanner() {
    // withContext(Dispatchers.IO) {
    //   bannerLiveData.postValue(DataRepository.requestBanner())
    // }
  }
}
