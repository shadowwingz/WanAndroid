package com.shadowwingz.wanandroid.home.data.article

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shadowwingz.wanandroid.home.data.article.model.ArticleListBean

@Dao
interface ArticleDao {
  
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertArticle(item: List<ArticleListBean>)
  
  @Query("SELECT * from article_list ORDER BY publishTime DESC")
  fun getArticles(): List<ArticleListBean>
  
  @Query("DELETE FROM article_list")
  fun deleteArticles()
}