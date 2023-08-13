package com.shadowwingz.wanandroid.home.domain.banner

import com.shadowwingz.wanandroid.core.data.Result
import com.shadowwingz.wanandroid.home.data.banner.BannerRepository
import com.shadowwingz.wanandroid.home.ui.banner.BannerUiModel
import com.shadowwingz.wanandroid.home.ui.banner.toBannerUiModel
import javax.inject.Inject

class SearchBannerUserCase @Inject constructor(
  private val bannerRepository: BannerRepository
) {

  suspend operator fun invoke(): Result<List<BannerUiModel>> {
    return when (val result = bannerRepository.search()) {
      is Result.Success -> {
        val banners = result.data.data.map { it.toBannerUiModel() }
        Result.Success(banners)
      }
      is Result.Error -> result
    }
  }
}