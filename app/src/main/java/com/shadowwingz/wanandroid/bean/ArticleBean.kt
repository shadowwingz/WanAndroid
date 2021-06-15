package com.shadowwingz.wanandroid.bean

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
data class ArticleBean(
  val `data`: ArticleData,
  val errorCode: Int,
  val errorMsg: String
)

@Keep
data class ArticleData(
  val curPage: Int,
  @SerializedName("datas")
  val articleListBean: List<ArticleListBean>,
  val offset: Int,
  val over: Boolean,
  val pageCount: Int,
  val size: Int,
  val total: Int
)

@Keep
@Parcelize
@Entity(tableName = "article_list")
data class ArticleListBean(
  var apkLink: String,
  var audit: Int,
  @ColumnInfo(name = "author") var author: String,
  var canEdit: Boolean,
  var chapterId: Int,
  var chapterName: String,
  @ColumnInfo(name = "collect") var collect: Boolean,
  var courseId: Int,
  var desc: String,
  var descMd: String,
  var envelopePic: String,
  var fresh: Boolean,
  @PrimaryKey @ColumnInfo(name = "id") var id: Int,
  var link: String,
  @ColumnInfo(name = "niceDate") var niceDate: String,
  var niceShareDate: String,
  var origin: String,
  var prefix: String,
  var projectLink: String,
  var publishTime: Long,
  var selfVisible: Int,
  var shareDate: Long,
  var shareUser: String,
  var superChapterId: Int,
  @ColumnInfo(name = "superChapterName") var superChapterName: String,
  //var tags: List<Any>,
  @ColumnInfo(name = "title") var title: String,
  var type: Int,
  var userId: Int,
  var visible: Int,
  var zan: Int
) : Parcelable