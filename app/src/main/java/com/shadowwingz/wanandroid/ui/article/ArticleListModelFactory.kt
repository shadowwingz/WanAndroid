package com.shadowwingz.wanandroid.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shadowwingz.wanandroid.ui.home.HomeFragmentViewModel

class ArticleListModelFactory() :
        ViewModelProvider.NewInstanceFactory() {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return HomeFragmentViewModel() as T
  }
}