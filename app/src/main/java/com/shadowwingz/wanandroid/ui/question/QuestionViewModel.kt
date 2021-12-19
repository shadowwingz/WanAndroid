package com.shadowwingz.wanandroid.ui.question

import androidx.lifecycle.viewModelScope
import com.shadowwingz.wanandroid.base.BaseViewModel
import kotlinx.coroutines.launch

class QuestionViewModel : BaseViewModel() {
  
  val questionRequest = QuestionRequest()
  
  fun getQuestions(pageId: Int) {
    viewModelScope.launch {
      questionRequest.getQuestions(pageId)
    }
  }
}