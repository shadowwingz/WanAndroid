package com.shadowwingz.wanandroid.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.ui.BaseFragment
import com.shadowwingz.wanandroid.ui.article.ArticleListAdapter
import com.shadowwingz.wanandroid.ui.widget.OnLoadMoreListener
import com.shadowwingz.wanandroid.utils.InjectorUtil
import com.shadowwingz.wanandroid.utils.LogUtil
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
  
  private val viewModel by lazy {
    ViewModelProviders.of(this, InjectorUtil.getArticleModeFactory()).get(HomeFragmentViewModel::class.java)
  }
  
  lateinit var articleListAdapter: ArticleListAdapter
  
  override fun getLayoutId(): Int {
    return R.layout.fragment_home
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    LogUtil.d("HomeFragment onViewCreated")
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
      refresh.isRefreshing = false
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
    articleListAdapter = ArticleListAdapter(viewModel.dataList)

    rvHomeFragment.layoutManager = LinearLayoutManager(activity)
    rvHomeFragment.adapter = articleListAdapter
  }
  
}