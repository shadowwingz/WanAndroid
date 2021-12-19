package com.shadowwingz.wanandroid.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shadowwingz.wanandroid.bean.ArticleListBean

@Dao
interface WanAndroidDao {
  
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertArticle(item: List<ArticleListBean>)
  
  @Query("SELECT * from article_list ORDER BY publishTime DESC")
  fun getArticles(): List<ArticleListBean>
  
  @Query("DELETE FROM article_list")
  fun deleteArticles()
}