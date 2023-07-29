package com.shadowwingz.wanandroid.ui.question

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QuestionViewModel : ViewModel() {
  
  val questionRequest = QuestionRequest()
  
  fun getQuestions(pageId: Int) {
    viewModelScope.launch {
      questionRequest.getQuestions(pageId)
    }
  }
}