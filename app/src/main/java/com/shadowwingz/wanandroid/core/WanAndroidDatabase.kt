package com.shadowwingz.wanandroid.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shadowwingz.wanandroid.home.data.article.ArticleDao
import com.shadowwingz.wanandroid.home.data.article.model.ArticleListBean
import com.shadowwingz.wanandroid.profile.data.ProfileDao
import com.shadowwingz.wanandroid.profile.data.model.ProfileResponseData

@Database(
  entities = [ArticleListBean::class, ProfileResponseData::class],
  version = 1,
  exportSchema = false
)
abstract class WanAndroidDatabase : RoomDatabase() {

  abstract fun articleDao(): ArticleDao

  abstract fun profileDao(): ProfileDao
}