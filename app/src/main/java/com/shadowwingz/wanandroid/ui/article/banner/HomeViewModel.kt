package com.shadowwingz.wanandroid.ui.article.banner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.network.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

  // @Inject
  // lateinit var vmData: VMData

  val bannerRequest = BannerRequest()
  // @Inject
  // lateinit var bannerRequest: BannerRequest
  var articleLiveData: MutableLiveData<PagingData<ArticleListBean>> = MutableLiveData()

  fun loadData() {
    // Timber.d("vmData: $vmData")
    viewModelScope.launch {
      bannerRequest.getBanner()
      loadArticles().collectLatest {
        articleLiveData.postValue(it)
      }
    }
  }

  fun loadArticles(): Flow<PagingData<ArticleListBean>> {
    return DataRepository.getArticleResultStream().cachedIn(viewModelScope)
  }
}