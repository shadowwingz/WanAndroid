package com.shadowwingz.wanandroid.data.db

object WanAndroidDatabase {
  
  private var articleListDao: ArticleListDao? = null
  
  fun getArticleListDao(): ArticleListDao {
    if (articleListDao == null) {
      articleListDao = ArticleListDao()
    }
    return articleListDao!!
  }
  
}