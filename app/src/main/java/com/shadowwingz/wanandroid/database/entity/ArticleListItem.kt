package com.shadowwingz.wanandroid.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "article_list")
data class ArticleListItem(
  @PrimaryKey @ColumnInfo(name = "id") val id: Int,
  @ColumnInfo(name = "author") val author: String,
  @ColumnInfo(name = "niceDate") val niceDate: String,
  @ColumnInfo(name = "title") val title: String,
  @ColumnInfo(name = "superChapterName") val superChapterName: String,
  @ColumnInfo(name = "collect") val collect: Boolean
) : Parcelable