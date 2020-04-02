package com.shadowwingz.ui.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shadowwingz.bean.ArticleListBean
import com.shadowwingz.network.ArticleRepository
import kotlinx.coroutines.launch

class ArticleListViewModel(private val repository: ArticleRepository) : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()

    var dataChanged = MutableLiveData<Int>()

    lateinit var articles: MutableList<ArticleListBean>

    val dataList = ArrayList<ArticleListBean>()

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