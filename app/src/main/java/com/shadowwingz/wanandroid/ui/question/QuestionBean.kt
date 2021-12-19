package com.shadowwingz.wanandroid.bean;

import com.google.gson.annotations.SerializedName

/**
 * created by shadowwingz on 2021-06-29 20:28
 */
data class QuestionBean(
  var `data`: Data,
  var errorCode: Int,
  var errorMsg: String
)

data class Data(
  var curPage: Int,
  @SerializedName("datas")
  var data: List<Question>,
  var offset: Int,
  var over: Boolean,
  var pageCount: Int,
  var size: Int,
  var total: Int
)

data class Question(
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
  var host: String,
  var id: Int,
  var link: String,
  var niceDate: String,
  var niceShareDate: String,
  var origin: String,
  var prefix: String,
  var projectLink: String,
  var publishTime: Long,
  var realSuperChapterId: Int,
  var selfVisible: Int,
  var shareDate: Long,
  var shareUser: String,
  var superChapterId: Int,
  var superChapterName: String,
  var tags: List<Tag>,
  var title: String,
  var type: Int,
  var userId: Int,
  var visible: Int,
  var zan: Int
)

data class Tag(
  var name: String,
  var url: String
)