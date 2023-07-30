package com.shadowwingz.wanandroid.data

import com.shadowwingz.wanandroid.article.domain.SearchBannerUserCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

class DataManager @Inject constructor(
  private val loadBanner: SearchBannerUserCase,
  private val dispatcherProvider: CoroutineDispatcherProvider
) {

  private val parentJob = SupervisorJob()
  private val scope = CoroutineScope(dispatcherProvider.computation + parentJob)

  // private fun loadBannerSearch() {
  //   scope.launch {
  //     val result = loadBanner()
  //     when (result) {
  //       is com.shadowwingz.wanandroid.network.Result.Success ->
  //     }
  //   }
  // }
}