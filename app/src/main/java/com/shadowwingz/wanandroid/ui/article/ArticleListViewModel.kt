package com.shadowwingz.wanandroid.ui.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.network.ArticleRepository
import kotlinx.coroutines.launch

class ArticleListViewModel(private val repository: ArticleRepository) : ViewModel() {

  private var isLoading = MutableLiveData<Boolean>()

  private var dataChanged = MutableLiveData<Int>()

  private lateinit var articles: MutableList<ArticleListBean>

  private val dataList = ArrayList<ArticleListBean>()

  fun getArticleList() {
    launch {
      articles = repository.getArticleList()
      dataList.addAll(articles)
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