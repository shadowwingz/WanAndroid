package com.shadowwingz.wanandroid.ui.question

import com.shadowwingz.wanandroid.base.BaseViewModel

class QuestionViewModel : BaseViewModel() {
  
  val questionRequest = QuestionRequest()
  
  fun getQuestions(pageId: Int) {
    questionRequest.getQuestions(pageId)
  }
}