package com.shadowwingz.wanandroid.architecture.domain

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.shadowwingz.wanandroid.architecture.response.DataResult
import com.shadowwingz.wanandroid.bean.ArticleBean

class ArticleRequest : BaseRequest(), DefaultLifecycleObserver {

  private val articleLiveData: MutableLiveData<DataResult<ArticleBean>> = MutableLiveData()

  fun getArticleLiveData(): MutableLiveData<DataResult<ArticleBean>> {
    return articleLiveData
  }

  fun fetchArticle(pageId: Int) {
    DataRepository.fetchArticle(pageId, object : DataResult.Result<ArticleBean> {
      override fun onResult(dataResult: DataResult<ArticleBean>?) {
        articleLiveData.postValue(dataResult)
      }
    })
  }

  private fun cancelFetchArticle() {
    DataRepository.cancelFetch()
  }

  /**
   * 页面即将退出时，及时通知数据层取消本次请求，以避免资源浪费和一系列不可预期的问题。
   */
  override fun onStop(owner: LifecycleOwner) {
    super.onStop(owner)
    cancelFetchArticle()
  }
}