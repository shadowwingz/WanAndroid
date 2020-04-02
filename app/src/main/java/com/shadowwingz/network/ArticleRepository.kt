package com.shadowwingz.network

import com.shadowwingz.data.db.ArticleListDao
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

}