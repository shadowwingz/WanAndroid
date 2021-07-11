package com.shadowwingz.wanandroid.ui.article

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

  private val viewModel by lazy {
    ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
  }

  var adapter = MultiTypeAdapter()

  private val pagingAdapter = ArticleAdapter()

  private var loadJob: Job? = null

  override fun getLayoutId(): Int {
    return R.layout.fragment_home
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    init()
    // viewModel.loadData()

    loadJob?.cancel()
    loadJob = lifecycleScope.launch {
      viewModel.loadArticles().collectLatest {
        pagingAdapter.submitData(it)
      }
    }

    observe()
  }

  private fun init() {
    initAdapter()

    initRefreshListener()
  }

  private fun observe() {
  }

  private fun initRefreshListener() {
    refresh.setColorSchemeResources(
      android.R.color.holo_blue_dark,
      android.R.color.holo_blue_light,
      android.R.color.holo_green_light,
      android.R.color.holo_green_light
    )

    refresh.setOnRefreshListener {
      pagingAdapter.refresh()
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
//    articleListAdapter.setHasStableIds(true)

    rvArticleList.layoutManager = LinearLayoutManager(activity)
    rvArticleList.adapter = pagingAdapter

    // adapter.register(BannerItemViewBinder())
    // adapter.register(ArticleItemViewBinder())

    // adapter.items = viewModel.allData
    // adapter.notifyDataSetChanged()
  }

}