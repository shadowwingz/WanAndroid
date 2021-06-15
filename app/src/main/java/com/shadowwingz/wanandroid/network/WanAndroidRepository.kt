package com.shadowwingz.wanandroid.network

import com.shadowwingz.wanandroid.database.WanAndroidDao
import com.shadowwingz.wanandroid.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WanAndroidRepository constructor(
  private val wanAndroidDao: WanAndroidDao,
  private val network: WanAndroidNetwork
) {
  suspend fun getArticleList(pageId: Int) = withContext(Dispatchers.IO) {
    var list = wanAndroidDao.getItems()
    LogUtil.d("缓存 $list")
    if (list.isEmpty()) {
      list = network.fetchArticleList(pageId).data.articleListBean
      wanAndroidDao.insert(list)
      LogUtil.d("缓存为空，从网络获取 $list")
    }
    list
  }

  suspend fun getBanner() = withContext(Dispatchers.IO) {
    var banner = network.fetchBanner()
    banner
  }

  companion object {
    private var instance: WanAndroidRepository? = null

    fun getInstance(
      wanAndroidDao: WanAndroidDao,
      network: WanAndroidNetwork
    ): WanAndroidRepository {
      if (instance == null) {
        synchronized(WanAndroidRepository::class.java) {
          if (instance == null) {
            instance = WanAndroidRepository(wanAndroidDao, network)
          }
        }
      }
      return instance!!
    }
  }
}