package com.shadowwingz.wanandroid.question.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shadowwingz.wanandroid.question.data.QuestionRepository
import com.shadowwingz.wanandroid.question.data.model.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
  repository: QuestionRepository
) : ViewModel() {

  val question: Flow<PagingData<Question>> = repository.question.cachedIn(viewModelScope)
}