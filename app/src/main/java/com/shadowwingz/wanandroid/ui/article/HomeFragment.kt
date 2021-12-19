package com.shadowwingz.wanandroid.ui.article

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.bean.BannerBean
import com.shadowwingz.wanandroid.listeners.OnItemClickListener
import com.shadowwingz.wanandroid.base.BaseFragment
import com.shadowwingz.wanandroid.ui.article.banner.BannerAdapter
import com.shadowwingz.wanandroid.ui.article.banner.HomeViewModel
import com.shadowwingz.wanandroid.ui.web.WebActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {
  
  private val viewModel by lazy {
    ViewModelProvider(this).get(HomeViewModel::class.java)
  }
  
  private val pagingAdapter = ArticleAdapter()
  private val bannerAdapter = BannerAdapter()
  
  override fun getLayoutId(): Int {
    return R.layout.fragment_home
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    init()
    observe()
  }
  
  private fun init() {
    initAdapter()
    initRefreshListener()
    viewModel.loadData()
  }
  
  private fun observe() {
    viewModel.bannerRequest.bannerLiveData.observe(viewLifecycleOwner, object : Observer<BannerBean> {
      override fun onChanged(bannerBean: BannerBean) {
        bannerAdapter.setItems(bannerBean.data)
      }
    })
    
    viewModel.articleLiveData.observe(viewLifecycleOwner, object : Observer<PagingData<ArticleListBean>> {
      override fun onChanged(pagingData: PagingData<ArticleListBean>) {
        lifecycleScope.launch {
          pagingAdapter.submitData(pagingData)
        }
      }
    })
  }
  
  private fun initRefreshListener() {
    with(refresh) {
      setColorSchemeResources(
        android.R.color.holo_blue_dark,
        android.R.color.holo_blue_light,
        android.R.color.holo_green_light,
        android.R.color.holo_green_light
      )
      
      setOnRefreshListener {
        handleRefresh()
      }
    }
    
    pagingAdapter.addLoadStateListener {
      when (it.refresh) {
        LoadState.Loading -> {
          refresh.isRefreshing = true
        }
        else -> {
          refresh.isRefreshing = false
        }
      }
    }
  }
  
  private fun initAdapter() {
    pagingAdapter.setOnItemClickListener(object : OnItemClickListener<ArticleListBean> {
      override fun onItemClick(data: ArticleListBean) {
        val intent = Intent(activity, WebActivity::class.java)
        intent.putExtra("url", data.link)
        activity?.startActivity(intent)
      }
    })
    val concatAdapter = ConcatAdapter(bannerAdapter, pagingAdapter)
    with(rvArticleList) {
      layoutManager = LinearLayoutManager(activity)
      adapter = concatAdapter
    }
  }
  
  private fun handleRefresh() {
    viewModel.loadData()
    pagingAdapter.refresh()
  }
}