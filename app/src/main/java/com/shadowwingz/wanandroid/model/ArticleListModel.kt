package com.shadowwingz.wanandroid.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.utils.LogUtil

class ArticleListModel(application: Application) : AndroidViewModel(application) {
  
  var mutableLiveData = MutableLiveData<List<ArticleListBean>>()
  
  /**
   * pageIndex 表示当前是第几页，当上滑加载到页面底部时，pageIndex 会自动加 1。
   */
  var mPageIndex = MutableLiveData<Boolean>()
  
  fun setArticleList(articleList: List<ArticleListBean>) {
    mutableLiveData.value = articleList
  }
  
  fun getArticleList(): MutableLiveData<List<ArticleListBean>> {
    return mutableLiveData
  }
  
  override fun onCleared() {
    super.onCleared()
    LogUtil.d("ArticleListModel onCleared")
  }
}