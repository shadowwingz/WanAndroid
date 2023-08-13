package com.shadowwingz.wanandroid.home.data.article

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shadowwingz.wanandroid.core.Config
import com.shadowwingz.wanandroid.home.data.article.model.ArticleListBean
import com.shadowwingz.wanandroid.home.ui.article.ArticlePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepository @Inject constructor(
  private val pagingSource: ArticlePagingSource
) {

  val article: Flow<PagingData<ArticleListBean>> = Pager(
    config = PagingConfig(
      pageSize = Config.NETWORK_PAGE_SIZE,
      enablePlaceholders = false
    ),
    pagingSourceFactory = { pagingSource }
  ).flow
}