package com.shadowwingz.wanandroid.home.data.banner

import com.shadowwingz.wanandroid.bean.BannerBean
import timber.log.Timber
import javax.inject.Inject

class BannerRepository @Inject constructor(
  private val bannerDataSource: SearchBannerDataSource
) {

  private val bannerCache = mutableListOf<BannerBean>()

  suspend fun search(): Result<BannerBean> {
    return bannerDataSource.search()
      .onSuccess {
        cache(it)
      }.onFailure {
        Timber.d("轮播图 onFailure")
      }
  }

  fun getBanner(): Result<BannerBean> {
    return if (bannerCache.isNotEmpty()) {
      Result.success(bannerCache[0])
    } else {
      Result.failure(IllegalStateException("banner not cached"))
    }
  }

  private fun cache(banner: BannerBean) {
    bannerCache.add(banner)
  }
}