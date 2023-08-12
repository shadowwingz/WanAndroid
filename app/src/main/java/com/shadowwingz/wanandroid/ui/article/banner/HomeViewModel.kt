package com.shadowwingz.wanandroid.ui.article.banner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shadowwingz.wanandroid.article.domain.model.BannerUiModel
import com.shadowwingz.wanandroid.article.domain.model.toBannerUiModel
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.network.DataRepository
import com.shadowwingz.wanandroid.network.Result.Success
import com.shadowwingz.wanandroid.ui.article.BannerRepository
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
  private val bannerRepository: BannerRepository
) : ViewModel() {

  private val articleFlow = DataRepository.getArticleResultStream().cachedIn(viewModelScope)

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