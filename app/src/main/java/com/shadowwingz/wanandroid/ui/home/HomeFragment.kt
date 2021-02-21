package com.shadowwingz.wanandroid.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.ui.BaseFragment
import com.shadowwingz.wanandroid.ui.article.ArticleListAdapter
import com.shadowwingz.wanandroid.ui.home.adapter.TopBannerAdapter
import com.shadowwingz.wanandroid.ui.widget.OnLoadMoreListener
import com.shadowwingz.wanandroid.utils.InjectorUtil
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
  
  private val viewModel by lazy {
    ViewModelProviders.of(this, InjectorUtil.getArticleModeFactory()).get(HomeFragmentViewModel::class.java)
  }
  
  lateinit var articleListAdapter: ArticleListAdapter
  
  lateinit var topBannerAdapter: TopBannerAdapter
  
  lateinit var skeletonScreen: RecyclerViewSkeletonScreen
  
  override fun getLayoutId(): Int {
    return R.layout.fragment_home
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    init()
    viewModel.loadData()
    observe()
  }
  
  private fun observe() {
    viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
      if (isLoading) {
        showProgressDialog()
      } else {
        closeProgressDialog()
      }
    })
    viewModel.articleDataChanged.observe(viewLifecycleOwner, Observer {
      articleListAdapter.notifyDataSetChanged()
      topBannerAdapter.notifyDataSetChanged()
      refresh.isRefreshing = false
      skeletonScreen.hide()
    })
  }
  
  private fun init() {
    initAdapter()
    
    initRefreshListener()
    
    rvHomeFragment.addOnScrollListener(object : OnLoadMoreListener() {
      override fun onLoadMore() {
        viewModel.queryArticle(++viewModel.pageId)
      }
    })
  }
  
  private fun initRefreshListener() {
    refresh.setColorSchemeResources(
            android.R.color.holo_blue_dark,
            android.R.color.holo_blue_light,
            android.R.color.holo_green_light,
            android.R.color.holo_green_light);
    
    refresh.setOnRefreshListener {
      refresh.isRefreshing = true
      viewModel.loadData()
    }
  }
  
  private fun initAdapter() {
    topBannerAdapter = TopBannerAdapter(viewModel.banner)
    articleListAdapter = ArticleListAdapter(viewModel.dataList)
    val concatAdapter = ConcatAdapter(topBannerAdapter, articleListAdapter)
    
    rvHomeFragment.layoutManager = LinearLayoutManager(activity)
    rvHomeFragment.adapter = concatAdapter
    
    skeletonScreen = Skeleton.bind(rvHomeFragment)
            .adapter(concatAdapter)
            .load(R.layout.layout_default_item_skeleton)
            .show()
  }
  
  companion object {
    @JvmStatic
    fun newInstance() = HomeFragment()
  }
  
}