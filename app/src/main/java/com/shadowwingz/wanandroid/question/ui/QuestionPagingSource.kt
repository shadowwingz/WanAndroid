package com.shadowwingz.wanandroid.question.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shadowwingz.wanandroid.question.data.api.QuestionService
import com.shadowwingz.wanandroid.question.data.model.Question
import javax.inject.Inject

class QuestionPagingSource @Inject constructor(private val service: QuestionService) : PagingSource<Int, Question>() {

  override fun getRefreshKey(state: PagingState<Int, Question>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Question> {
    // params.key 是即将加载的页面索引
    val position = params.key ?: 0
    return try {
      val response = service.fetchQuestions(position)
      val articleList = response.data.data.sortedByDescending { it.publishTime }
      // 如果获取的 articleList 没有数据，说明不需要再加载下一页了，此时 nextKey 设置为 null
      // 如果获取的 articleList 有数据，就把 nextKey 值在之前的基础上加 1。
      val nextKey = if (articleList.isEmpty()) {
        null
      } else {
        position + 1
      }
      LoadResult.Page(
        data = articleList,
        prevKey = if (position == 0) null else position - 1,
        nextKey = nextKey
      )
    } catch (exception: Exception) {
      return LoadResult.Error(exception)
    }
  }
}