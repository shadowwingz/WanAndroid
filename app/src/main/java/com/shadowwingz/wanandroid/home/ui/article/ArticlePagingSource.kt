package com.shadowwingz.wanandroid.home.ui.article

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shadowwingz.wanandroid.home.data.api.ArticleService
import com.shadowwingz.wanandroid.home.data.article.model.ArticleListBean
import com.shadowwingz.wanandroid.home.data.article.model.ArticleResponse
import safeApiCall
import java.io.IOException
import javax.inject.Inject

class ArticlePagingSource @Inject constructor(
  private val service: ArticleService
) : PagingSource<Int, ArticleListBean>() {

  override fun getRefreshKey(state: PagingState<Int, ArticleListBean>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleListBean> {
    // params.key 是即将加载的页面索引
    val position = params.key ?: 0
    return try {
      val response = search(position)
      val articleList = response.getOrNull()?.let {
        it.data.articleListBean.sortedByDescending { it.publishTime }
      } ?: emptyList()
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

  private suspend fun search(position: Int) = safeApiCall(
    call = { requestSearch(position) },
    errorMessage = "文章加载失败"
  )

  private suspend fun requestSearch(position: Int): Result<ArticleResponse> {
    val response = service.searchRepos(position)
    if (response.isSuccessful) {
      val body = response.body()
      if (body != null) {
        return Result.success(body)
      }
    }
    return Result.failure(IOException("${response.code()} ${response.message()}"))
  }
}