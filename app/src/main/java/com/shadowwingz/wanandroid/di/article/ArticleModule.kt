package com.shadowwingz.wanandroid.di.article

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shadowwingz.wanandroid.core.Config.NETWORK_PAGE_SIZE
import com.shadowwingz.wanandroid.home.data.api.ArticleService
import com.shadowwingz.wanandroid.home.data.article.model.ArticleListBean
import com.shadowwingz.wanandroid.home.ui.article.ArticlePagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ArticleModule {
  @Provides
  fun provideArticleFlow(
    pagingSource: ArticlePagingSource
  ): Flow<PagingData<ArticleListBean>> {
    return Pager(
      config = PagingConfig(
        pageSize = NETWORK_PAGE_SIZE,
        enablePlaceholders = false
      ),
      pagingSourceFactory = { pagingSource }
    ).flow
  }

  @Provides
  fun provideArticleService(
    retrofit: Retrofit
  ): ArticleService = retrofit.create(ArticleService::class.java)
}