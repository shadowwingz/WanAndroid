package com.shadowwingz.wanandroid.ui.article.banner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shadowwingz.wanandroid.architecture.domain.DataRepository
import com.shadowwingz.wanandroid.base.BaseViewModel
import com.shadowwingz.wanandroid.bean.ArticleListBean
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
  
  val bannerRequest = BannerRequest()
  var articleLiveData: MutableLiveData<PagingData<ArticleListBean>> = MutableLiveData()
  
  fun loadData() {
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