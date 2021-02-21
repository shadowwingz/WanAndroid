package com.shadowwingz.wanandroid.ui.home

import androidx.lifecycle.MutableLiveData
import com.shadowwingz.wanandroid.base.BaseViewModel
import com.shadowwingz.wanandroid.base.WanAndroidApplication
import com.shadowwingz.wanandroid.bean.ArticleBean
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.bean.BannerBean
import com.shadowwingz.wanandroid.bean.BannerData
import com.shadowwingz.wanandroid.network.ArticleRepository
import com.shadowwingz.wanandroid.ui.widget.ToastUtil

class HomeFragmentViewModel(private val repository: ArticleRepository) : BaseViewModel() {
  
  var isLoading = MutableLiveData<Boolean>()
  
  var articleDataChanged = MutableLiveData<Int>()
  
  var bannerDataChanged = MutableLiveData<Int>()
  
  private lateinit var articles: ArticleBean
  
  var dataList = ArrayList<ArticleListBean>()
  
  var banner = ArrayList<BannerData>()
  
  var pageId: Int = 0
  
  fun loadData() {
    pageId = 0
    launch({
      queryBanner()
      bannerDataChanged.value?.plus(1)
    })
    dataList.clear()
    queryArticle(pageId)
  }
  
  private suspend fun queryBanner() {
    val banners: BannerBean = repository.getBanner()
    banner.addAll(banners.data)
  }
  
  fun queryArticle(pageId: Int) {
    launch({
      isLoading.value = true
  
      articles = repository.getArticleList(pageId)
      checkMaxPage()
      dataList.addAll(articles.data.articleListBean)
  
      articleDataChanged.value = articleDataChanged.value?.plus(1)
      isLoading.value = false
    }, {
      articleDataChanged.value = articleDataChanged.value?.plus(1)
      isLoading.value = false
    })
  }
  
  private fun checkMaxPage() {
    if (articles.data.curPage == articles.data.pageCount) {
      ToastUtil.show(WanAndroidApplication.context, "已经是最后一页了")
      throw Exception()
    }
  }
  
}