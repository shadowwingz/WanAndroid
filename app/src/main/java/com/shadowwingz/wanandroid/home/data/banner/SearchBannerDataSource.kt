package com.shadowwingz.wanandroid.home.data.banner

import com.shadowwingz.wanandroid.bean.BannerBean
import com.shadowwingz.wanandroid.core.data.Result
import com.shadowwingz.wanandroid.core.data.safeApiCall
import com.shadowwingz.wanandroid.home.data.api.BannerSearchService
import java.io.IOException
import javax.inject.Inject

class SearchBannerDataSource @Inject constructor(private val service: BannerSearchService) {

  suspend fun search() = safeApiCall(
    call = { requestSearch() },
    errorMessage = "轮播图加载失败"
  )

  private suspend fun requestSearch(): Result<BannerBean> {
    val response = service.getBanner()
    if (response.isSuccessful) {
      val body = response.body()
      if (body != null) {
        return Result.Success(body)
      }
    }
    return Result.Error(IOException("${response.code()} ${response.message()}"))
  }
}