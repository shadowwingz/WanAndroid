package com.shadowwingz.wanandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shadowwingz.wanandroid.bean.ArticleBean
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.bean.BannerBean
import com.shadowwingz.wanandroid.bean.BannerData
import com.shadowwingz.wanandroid.network.ArticleRepository
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val repository: ArticleRepository) : ViewModel() {

  var isLoading = MutableLiveData<Boolean>()

  var dataChanged = MutableLiveData<Int>()
  
  var bannerDataChanged = MutableLiveData<Int>()

  private lateinit var articles: ArticleBean

  var dataList = ArrayList<ArticleListBean>()
  
  var banner = ArrayList<BannerData>()

  fun getArticleList() {
    launch {
      articles = repository.getArticleList()
      dataList.addAll(articles.data.articleListBean)
      val banners: BannerBean = repository.getBanner()
      banner.addAll(banners.data)
    }
  }

  private fun launch(block: suspend () -> Unit) = viewModelScope.launch {
    try {
      isLoading.value = true
      dataList.clear()
      block()
      dataChanged.value = dataChanged.value?.plus(1)
      isLoading.value = false
    } catch (t: Throwable) {
      t.printStackTrace()
      dataChanged.value = dataChanged.value?.plus(1)
      isLoading.value = false
    }
  }
}