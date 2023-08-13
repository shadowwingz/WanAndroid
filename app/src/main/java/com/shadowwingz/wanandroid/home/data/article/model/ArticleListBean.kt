package com.shadowwingz.wanandroid.home.data.article.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_list")
data class ArticleListBean(
  var apkLink: String,
  var audit: Int,
  var author: String,
  var canEdit: Boolean,
  var chapterId: Int,
  var chapterName: String,
  var collect: Boolean,
  var courseId: Int,
  var desc: String,
  var descMd: String,
  var envelopePic: String,
  var fresh: Boolean,
  @PrimaryKey var id: Int,
  var link: String,
  var niceDate: String,
  var niceShareDate: String,
  var origin: String,
  var prefix: String,
  var projectLink: String,
  var publishTime: Long,
  var selfVisible: Int,
  var shareDate: Long,
  var shareUser: String,
  var superChapterId: Int,
  var superChapterName: String,
  //var tags: List<Any>,
  var title: String,
  var type: Int,
  var userId: Int,
  var visible: Int,
  var zan: Int,
  var pageId: Int
)