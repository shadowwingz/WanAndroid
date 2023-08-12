package com.shadowwingz.wanandroid.home.ui.article

import android.app.Activity
import android.content.Intent
import androidx.annotation.DrawableRes
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.ui.web.WebActivity

data class ArticleListUiModel(
  val author: String,
  val date: String,
  val title: String,
  val superChapterName: String,
  val chapterName: String,
  @DrawableRes val collectIcon: Int,
  val link: String,
  val itemClickListener: () -> Unit
)

fun ArticleListBean.toArticleListUiModel(activity: Activity?): ArticleListUiModel {
  return ArticleListUiModel(
    author = author,
    date = niceDate,
    title = title,
    superChapterName = superChapterName,
    chapterName = chapterName,
    collectIcon = if (collect) R.drawable.ic_like else R.drawable.ic_like,
    link = link,
    itemClickListener = {
      activity?.apply {
        val intent = Intent(activity, WebActivity::class.java).apply {
          putExtra("url", link)
        }
        startActivity(intent)
      }
    }
  )
}