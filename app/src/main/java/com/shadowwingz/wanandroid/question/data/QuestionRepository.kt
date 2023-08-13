package com.shadowwingz.wanandroid.question.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shadowwingz.wanandroid.core.Config
import com.shadowwingz.wanandroid.question.data.model.Question
import com.shadowwingz.wanandroid.question.ui.QuestionPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuestionRepository @Inject constructor(
  private val pagingSource: QuestionPagingSource
) {

  val question: Flow<PagingData<Question>> = Pager(
    config = PagingConfig(
      pageSize = Config.NETWORK_PAGE_SIZE,
      enablePlaceholders = false
    ),
    pagingSourceFactory = { pagingSource }
  ).flow
}