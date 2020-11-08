package com.shadowwingz.wanandroid.utils

import com.shadowwingz.wanandroid.data.db.WanAndroidDatabase
import com.shadowwingz.wanandroid.network.ArticleRepository
import com.shadowwingz.wanandroid.network.WanAndroidNetwork
import com.shadowwingz.wanandroid.ui.article.ArticleListModelFactory

object InjectorUtil {

  private fun getArticleRepository() = ArticleRepository.getInstance(
          WanAndroidDatabase.getArticleListDao(),
          WanAndroidNetwork.getInstance()
  )

  fun getArticleModeFactory() = ArticleListModelFactory(getArticleRepository())
}