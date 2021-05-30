package com.shadowwingz.wanandroid.ui.home

import androidx.lifecycle.MutableLiveData
import com.shadowwingz.wanandroid.base.BaseViewModel
import com.shadowwingz.wanandroid.base.WanAndroidApplication
import com.shadowwingz.wanandroid.bean.ArticleBean
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.bean.BannerBean
import com.shadowwingz.wanandroid.network.ArticleRepository
import com.shadowwingz.wanandroid.ui.widget.ToastUtil

class HomeFragmentViewModel(private val repository: ArticleRepository) : BaseViewModel() {

  var isLoading = MutableLiveData<Boolean>()

  var articleDataChanged = MutableLiveData<Int>()

  var bannerDataChanged = MutableLiveData<Int>()

  private lateinit var articles: ArticleBean

  /**
   * 文章列表数据
   */
  var dataList = mutableListOf<ArticleListBean>()

  /**
   * allData 是给 MultiType 使用的数据
   */
  var allData = mutableListOf<Any>()

  /**
   * 当前页面索引
   */
  var pageId: Int = 0

  fun loadData() {
    reset()
    launch({
      val banners: BannerBean = repository.getBanner()

      allData.add(banners.data)

      bannerDataChanged.value?.plus(1)
    })
    queryArticle(pageId)
  }

  private fun reset() {
    pageId = 0
    dataList = mutableListOf()
    allData = mutableListOf()
  }

  fun queryArticle(pageId: Int) {
    launch({
      isLoading.value = true

      articles = repository.getArticleList(pageId)
      if (isLastPage()) {
        isLoading.value = false
        return@launch
      }

      dataList.addAll(articles.data.articleListBean)
      allData.addAll(dataList)

      articleDataChanged.value = articleDataChanged.value?.plus(1)
      isLoading.value = false
    }, {
      articleDataChanged.value = articleDataChanged.value?.plus(1)
      isLoading.value = false
    })
  }

  private fun isLastPage(): Boolean {
    return if (articles.data.curPage == articles.data.pageCount) {
      ToastUtil.show(WanAndroidApplication.context, "已经是最后一页了")
      true
    } else {
      false
    }
  }

}