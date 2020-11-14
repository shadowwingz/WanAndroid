package com.shadowwingz.wanandroid.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shadowwingz.wanandroid.network.ArticleRepository
import com.shadowwingz.wanandroid.ui.home.HomeFragmentViewModel

class ArticleListModelFactory(private val repository: ArticleRepository) :
        ViewModelProvider.NewInstanceFactory() {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return HomeFragmentViewModel(repository) as T
  }
}