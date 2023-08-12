package com.shadowwingz.wanandroid.home.ui.article

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shadowwingz.wanandroid.home.data.article.model.ArticleListBean
import com.shadowwingz.wanandroid.home.data.api.ArticleService

private const val WANANDROID_STARTING_PAGE_INDEX = 0

class ArticlePagingSource(private val service: ArticleService) : PagingSource<Int, ArticleListBean>() {

  override fun getRefreshKey(state: PagingState<Int, ArticleListBean>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleListBean> {
    // params.key 是即将加载的页面索引
    val position = params.key ?: WANANDROID_STARTING_PAGE_INDEX
    return try {
      val response = service.searchRepos(position)
      val articleList = response.data.articleListBean.sortedByDescending { it.publishTime }
      // 如果获取的 articleList 没有数据，说明不需要再加载下一页了，此时 nextKey 设置为 null
      // 如果获取的 articleList 有数据，就把 nextKey 值在之前的基础上加 1。
      val nextKey = if (articleList.isEmpty()) {
        null
      } else {
        position + 1
      }
      LoadResult.Page(
        data = articleList,
        prevKey = if (position == WANANDROID_STARTING_PAGE_INDEX) null else position - 1,
        nextKey = nextKey
      )
    } catch (exception: Exception) {
      return LoadResult.Error(exception)
    }
  }
}