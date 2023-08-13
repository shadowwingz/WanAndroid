package com.shadowwingz.wanandroid.question.ui

import android.app.Activity
import android.content.Intent
import com.shadowwingz.wanandroid.question.data.model.Question
import com.shadowwingz.wanandroid.ui.web.WebActivity

data class QuestionUiModel(
  val author: String,
  val name: String,
  val date: String,
  val title: String,
  val description: String,
  val chapter: String,
  val itemClick: () -> Unit
)

fun Question.toQuestionUiModel(activity: Activity?): QuestionUiModel {
  return QuestionUiModel(
    author = author,
    name = tags[0].name,
    date = niceDate,
    title = title,
    description = desc,
    chapter = superChapterName,
    itemClick = {
      activity?.apply {
        val intent = Intent(activity, WebActivity::class.java).apply {
          putExtra("url", link)
        }
        startActivity(intent)
      }
    }
  )
}
