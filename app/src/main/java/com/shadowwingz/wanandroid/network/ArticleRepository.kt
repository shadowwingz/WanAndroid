package com.shadowwingz.wanandroid.network

import com.shadowwingz.wanandroid.data.db.ArticleListDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleRepository private constructor(
  private val articleListDao: ArticleListDao,
  private val network: WanAndroidNetwork
) {
  suspend fun getArticleList() = withContext(Dispatchers.IO) {
    var list = network.fetchArticleList(0)
    list
  }

  companion object {
    private var instance: ArticleRepository? = null

    fun getInstance(
      articleListDao: ArticleListDao,
      network: WanAndroidNetwork
    ): ArticleRepository {
      if (instance == null) {
        synchronized(ArticleRepository::class.java) {
          if (instance == null) {
            instance = ArticleRepository(articleListDao, network)
          }
        }
      }
      return instance!!
    }
  }
}