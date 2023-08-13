package com.shadowwingz.wanandroid.home.data.banner

import com.shadowwingz.wanandroid.home.data.banner.model.BannerResponse
import timber.log.Timber
import javax.inject.Inject

class BannerRepository @Inject constructor(
  private val bannerDataSource: SearchBannerDataSource
) {

  private val bannerCache = mutableListOf<BannerResponse>()

  suspend fun search(): Result<BannerResponse> {
    return bannerDataSource.search()
      .onSuccess {
        cache(it)
      }.onFailure {
        Timber.d("轮播图 onFailure")
      }
  }

  fun getBanner(): Result<BannerResponse> {
    return if (bannerCache.isNotEmpty()) {
      Result.success(bannerCache[0])
    } else {
      Result.failure(IllegalStateException("banner not cached"))
    }
  }

  private fun cache(banner: BannerResponse) {
    bannerCache.add(banner)
  }
}