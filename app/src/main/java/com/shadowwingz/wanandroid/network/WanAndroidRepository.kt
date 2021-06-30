package com.shadowwingz.wanandroid.network

import com.shadowwingz.wanandroid.database.WanAndroidDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WanAndroidRepository(private val wanAndroidDao: WanAndroidDao) {
  suspend fun getArticleList(pageId: Int) = withContext(Dispatchers.IO) {
    var list = WanAndroidNetwork.fetchArticleList(pageId).data.articleListBean
    //var list = wanAndroidDao.getArticles()
    //if (list.isEmpty()) {
    //  list = WanAndroidNetwork.fetchArticleList(pageId).data.articleListBean
    //  WanAndroidNetwork.fetchArticleList(pageId)
    //  wanAndroidDao.insertArticle(list)
    //}
    list
  }

  suspend fun refreshArticleList(pageId: Int) = withContext(Dispatchers.IO) {
    //wanAndroidDao.deleteArticles()
    val list = WanAndroidNetwork.fetchArticleList(pageId).data.articleListBean
    //wanAndroidDao.insertArticle(list)
    list
  }

  suspend fun getBanner() = withContext(Dispatchers.IO) {
    val banner = WanAndroidNetwork.fetchBanner()
    banner
  }

  companion object {
    private var instance: WanAndroidRepository? = null

    fun getInstance(
      wanAndroidDao: WanAndroidDao
    ): WanAndroidRepository {
      if (instance == null) {
        synchronized(WanAndroidRepository::class.java) {
          if (instance == null) {
            instance = WanAndroidRepository(wanAndroidDao)
          }
        }
      }
      return instance!!
    }
  }
}