package com.shadowwingz.wanandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shadowwingz.wanandroid.base.BaseViewModel
import com.shadowwingz.wanandroid.base.WanAndroidApp
import com.shadowwingz.wanandroid.bean.ArticleListBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentViewModel : BaseViewModel() {

  var isLoading = MutableLiveData<Boolean>()

  var articleDataChanged = MutableLiveData<Int>()

  var bannerDataChanged = MutableLiveData<Int>()

  private lateinit var articles: List<ArticleListBean>

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
    viewModelScope.launch(Dispatchers.Main) {
      val banners = withContext(Dispatchers.IO) {
        WanAndroidApp.repository.getBanner();
      }
      allData.add(banners.data);
      bannerDataChanged.value?.plus(1)
      queryArticle(pageId)
    }
  }

  private fun reset() {
    pageId = 0
    dataList = mutableListOf()
    allData = mutableListOf()
  }

  fun queryArticle(pageId: Int) {
    viewModelScope.launch(Dispatchers.Main) {
      isLoading.value = true
      articles = withContext(Dispatchers.IO) {
        WanAndroidApp.repository.getArticleList(pageId)
      }

      if (isLastPage()) {
        isLoading.value = false
        return@launch
      }

      dataList.addAll(articles)
      allData.addAll(dataList)

      articleDataChanged.value = articleDataChanged.value?.plus(1)
      isLoading.value = false
    }
  }

  private fun isLastPage(): Boolean {
    // todo 由于 repository 返回的是一个列表 ArticleListBean，不再是原始的 ArticleBean，
    // 因此在 ViewModel 无法拿到当前页信息，待后续研究如何解决。
    //return if (articles.data.curPage == articles.data.pageCount) {
    //  ToastUtil.show(WanAndroidApp.context, "已经是最后一页了")
    //  true
    //} else {
    //  false
    //}
    return false
  }

}