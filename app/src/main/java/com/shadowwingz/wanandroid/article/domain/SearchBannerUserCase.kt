package com.shadowwingz.wanandroid.article.domain

import com.shadowwingz.wanandroid.article.domain.model.BannerUiModel
import com.shadowwingz.wanandroid.article.domain.model.toBannerUiModel
import com.shadowwingz.wanandroid.core.data.Result
import com.shadowwingz.wanandroid.home.data.banner.BannerRepository
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