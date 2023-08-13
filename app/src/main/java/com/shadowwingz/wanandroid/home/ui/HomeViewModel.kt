package com.shadowwingz.wanandroid.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shadowwingz.wanandroid.core.data.Result
import com.shadowwingz.wanandroid.core.data.Result.Success
import com.shadowwingz.wanandroid.home.data.article.ArticleRepository
import com.shadowwingz.wanandroid.home.data.article.model.ArticleListBean
import com.shadowwingz.wanandroid.home.domain.banner.SearchBannerUserCase
import com.shadowwingz.wanandroid.home.ui.banner.BannerUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val bannerUserCase: SearchBannerUserCase,
  articleRepository: ArticleRepository
) : ViewModel() {

  private val _errorTip: MutableSharedFlow<String> = MutableSharedFlow()
  val errorTip: Flow<String> = _errorTip

  private val articleFlow = articleRepository.article.cachedIn(viewModelScope)

  private val bannerFlow: Flow<List<BannerUiModel>> = flow {
    val result: Result<List<BannerUiModel>> = bannerUserCase(Unit)
    if (result is Success) {
      emit(result.data)
    } else {
      _errorTip.emit(result.toString())
      emit(emptyList())
    }
  }

  val articleCombinedFlow: Flow<Pair<List<BannerUiModel>, PagingData<ArticleListBean>>> =
    bannerFlow.zip(articleFlow) { banner, article ->
      banner to article
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), Pair(emptyList(), PagingData.empty()))
}