package com.shadowwingz.wanandroid.home.data.banner

import com.shadowwingz.wanandroid.home.data.api.BannerSearchService
import com.shadowwingz.wanandroid.home.data.banner.model.BannerResponse
import safeApiCall
import java.io.IOException
import javax.inject.Inject

class SearchBannerDataSource @Inject constructor(private val service: BannerSearchService) {

  suspend fun search() = safeApiCall(
    call = { requestSearch() },
    errorMessage = "轮播图加载失败"
  )

  private suspend fun requestSearch(): Result<BannerResponse> {
    val response = service.getBanner()
    if (response.isSuccessful) {
      val body = response.body()
      if (body != null) {
        return Result.success(body)
      }
    }
    return Result.failure(IOException("${response.code()} ${response.message()}"))
  }
}