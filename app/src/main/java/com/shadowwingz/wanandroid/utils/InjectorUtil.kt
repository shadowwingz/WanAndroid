package com.shadowwingz.wanandroid.utils

import com.shadowwingz.wanandroid.base.WanAndroidApp
import com.shadowwingz.wanandroid.database.WanAndroidDatabase
import com.shadowwingz.wanandroid.network.WanAndroidNetwork
import com.shadowwingz.wanandroid.network.WanAndroidRepository
import com.shadowwingz.wanandroid.ui.article.ArticleListModelFactory

object InjectorUtil {

  private fun getArticleRepository() = WanAndroidRepository.getInstance(
    WanAndroidDatabase.getDatabase(WanAndroidApp.context).wanandroidDao(),
    WanAndroidNetwork.getInstance()
  )

  fun getArticleModeFactory() = ArticleListModelFactory()
}