package com.shadowwingz.wanandroid.ui.article

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shadowwingz.wanandroid.architecture.domain.DataRepository
import com.shadowwingz.wanandroid.base.BaseViewModel
import com.shadowwingz.wanandroid.bean.ArticleListBean
import kotlinx.coroutines.flow.Flow

class HomeFragmentViewModel() : BaseViewModel() {

  val bannerRequest = BannerRequest()

  fun loadBanner() {
    bannerRequest.getBanner()
  }

  fun loadArticles(): Flow<PagingData<ArticleListBean>> {
    return DataRepository.getArticleResultStream().cachedIn(viewModelScope)
  }
}