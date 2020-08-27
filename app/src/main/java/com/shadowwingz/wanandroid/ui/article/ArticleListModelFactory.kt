package com.shadowwingz.wanandroid.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shadowwingz.wanandroid.network.ArticleRepository

class ArticleListModelFactory(private val repository: ArticleRepository) :
  ViewModelProvider.NewInstanceFactory() {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return ArticleListViewModel(repository) as T
  }
}