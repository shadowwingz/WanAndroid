package com.shadowwingz.wanandroid.home.domain.banner

import com.shadowwingz.wanandroid.core.domain.usebase.CoroutineUseCase
import com.shadowwingz.wanandroid.di.coroutines.IoDispatcher
import com.shadowwingz.wanandroid.home.data.banner.BannerRepository
import com.shadowwingz.wanandroid.home.ui.banner.BannerUiModel
import com.shadowwingz.wanandroid.home.ui.banner.toBannerUiModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SearchBannerUserCase @Inject constructor(
  private val bannerRepository: BannerRepository,
  @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, Result<List<BannerUiModel>>>(dispatcher) {

  override suspend fun execute(parameters: Unit): Result<List<BannerUiModel>> {
    return bannerRepository.search().map {
      it.data.map { banner ->
        banner.toBannerUiModel()
      }
    }
  }
}