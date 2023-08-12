package com.shadowwingz.wanandroid.home.data.article

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shadowwingz.wanandroid.bean.ArticleListBean

@Database(entities = [ArticleListBean::class], version = 1, exportSchema = false)
abstract class WanAndroidDatabase : RoomDatabase() {
  
  abstract fun wanandroidDao(): WanAndroidDao
  
  companion object {
    private var INSTANCE: WanAndroidDatabase? = null
    
    fun getDatabase(context: Context): WanAndroidDatabase {
      //if (INSTANCE != null) {
      //  return INSTANCE as WanAndroidDatabase
      //}
      // todo 这里如果直接对 INSTANCE 判空并 return，需要把 INSTANCE 强转为 WanAndroidDatabase
      // 但是如果把 INSTANCE 先赋值给变量，再对变量判空并 return，就不需要强转，待研究。
      val tempInstance = INSTANCE;
      if (tempInstance != null) {
        return tempInstance
      }
      
      synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          WanAndroidDatabase::class.java,
          "wanandroid_database"
        ).build()
        INSTANCE = instance;
        return instance
      }
    }
  }
}