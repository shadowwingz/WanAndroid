package com.shadowwingz.wanandroid.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shadowwingz.wanandroid.bean.ArticleListBean

@Dao
interface WanAndroidDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(item: List<ArticleListBean>)

  @Query("SELECT * from article_list")
  fun getItems(): List<ArticleListBean>
}