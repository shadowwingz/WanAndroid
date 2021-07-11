package com.shadowwingz.wanandroid.ui.article

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.architecture.response.DataResult
import com.shadowwingz.wanandroid.bean.BannerBean
import com.shadowwingz.wanandroid.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

  private val viewModel by lazy {
    ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
  }

  private val pagingAdapter = ArticleAdapter()
  private val bannerAdapter = BannerAdapter()

  private var loadJob: Job? = null

  override fun getLayoutId(): Int {
    return R.layout.fragment_home
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    init()
    loadData()
    observe()
  }

  private fun loadData() {
    viewModel.loadBanner()
    loadJob?.cancel()
    loadJob = lifecycleScope.launch {
      viewModel.loadArticles().collectLatest {
        pagingAdapter.submitData(it)
      }
    }
  }

  private fun init() {
    initAdapter()
    initRefreshListener()
  }

  private fun observe() {
    viewModel.bannerRequest.bannerLiveData.observe(viewLifecycleOwner, object : Observer<DataResult<BannerBean>> {
      override fun onChanged(t: DataResult<BannerBean>?) {
        bannerAdapter.setItems(t?.getResult()?.data)
      }
    })
  }

  private fun initRefreshListener() {
    refresh.setColorSchemeResources(
      android.R.color.holo_blue_dark,
      android.R.color.holo_blue_light,
      android.R.color.holo_green_light,
      android.R.color.holo_green_light
    )

    refresh.setOnRefreshListener {
      handleRefresh()
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
    val concatAdapter = ConcatAdapter(bannerAdapter, pagingAdapter)
    rvArticleList.layoutManager = LinearLayoutManager(activity)
    rvArticleList.adapter = concatAdapter
  }

  private fun handleRefresh() {
    viewModel.loadBanner()
    pagingAdapter.refresh()
  }
}