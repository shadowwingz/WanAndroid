package com.shadowwingz.wanandroid.home.data.banner

import com.shadowwingz.wanandroid.bean.BannerBean
import com.shadowwingz.wanandroid.core.data.Result
import javax.inject.Inject

class BannerRepository @Inject constructor(private val bannerDataSource: SearchBannerDataSource) {

  private val bannerCache = mutableListOf<BannerBean>()

  suspend fun search(): Result<BannerBean> {
    val result = bannerDataSource.search()
    if (result is Result.Success) {
      cache(result.data)
    }
    return result
  }

  fun getBanner(): Result<BannerBean> {
    return if (bannerCache.isNotEmpty()) {
      Result.Success(bannerCache[0])
    } else {
      Result.Error(IllegalStateException("banner not cached"))
    }
  }

  private fun cache(banner: BannerBean) {
    bannerCache.add(banner)
  }
}