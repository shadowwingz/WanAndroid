package com.shadowwingz.wanandroid.question.ui

import com.shadowwingz.wanandroid.databinding.ItemQuestionBinding

object QuestionItemBinder {
  fun bind(view: ItemQuestionBinding, data: QuestionUiModel) {
    view.apply {
      tvAuthor.text = data.author
      tvTag.text = data.name
      tvNiceDate.text = data.date
      tvTitle.text = data.title
      tvDesc.text = data.description
      tvchapterName.text = data.chapter
      root.setOnClickListener {
        data.itemClick()
      }
    }
  }
}