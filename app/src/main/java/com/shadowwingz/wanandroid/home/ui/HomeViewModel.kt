package com.shadowwingz.wanandroid.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shadowwingz.wanandroid.article.domain.model.BannerUiModel
import com.shadowwingz.wanandroid.article.domain.model.toBannerUiModel
import com.shadowwingz.wanandroid.core.data.Result.Success
import com.shadowwingz.wanandroid.home.data.article.ArticleRepository
import com.shadowwingz.wanandroid.home.data.article.model.ArticleListBean
import com.shadowwingz.wanandroid.home.data.banner.BannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.zip
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val bannerRepository: BannerRepository,
  articleRepository: ArticleRepository
) : ViewModel() {

  private val articleFlow = articleRepository.article.cachedIn(viewModelScope)

  private val bannerFlow: Flow<List<BannerUiModel>> = flow {
    val result = bannerRepository.search()
    if (result is Success) {
      emit(result.data.data.map { it.toBannerUiModel() }.toList())
    } else {
      emit(emptyList())
    }
  }

  val articleCombinedFlow: Flow<Pair<List<BannerUiModel>, PagingData<ArticleListBean>>> =
    bannerFlow.zip(articleFlow) { banner, article ->
      Timber.d("123")
      banner to article
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), Pair(emptyList(), PagingData.empty()))
}