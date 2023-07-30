package com.shadowwingz.wanandroid.ui.article

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.base.BaseFragment
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.databinding.FragmentHomeBinding
import com.shadowwingz.wanandroid.listeners.OnItemClickListener
import com.shadowwingz.wanandroid.ui.article.banner.BannerAdapter
import com.shadowwingz.wanandroid.ui.article.banner.BannerRequest
import com.shadowwingz.wanandroid.ui.article.banner.HomeViewModel
import com.shadowwingz.wanandroid.ui.web.WebActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() : BaseFragment() {

  private lateinit var binding: FragmentHomeBinding

  @Inject
  lateinit var bannerRequest: BannerRequest

  private val viewModel: HomeViewModel by viewModels()

  private val pagingAdapter = ArticleAdapter()
  private val bannerAdapter = BannerAdapter()

  override fun getLayoutId(): Int {
    return R.layout.fragment_home
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    init()
    observe()
  }

  private fun init() {
    initAdapter()
    initRefreshListener()
  }

  private fun observe() {
    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.articleCombinedFlow.collect {
          bannerAdapter.setItems(it.first)
          pagingAdapter.submitData(it.second)
          binding.refresh.isRefreshing = false
        }
      }
    }
  }

  private fun initRefreshListener() {
    with(binding.refresh) {
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
          binding.refresh.isRefreshing = true
        }
        else -> {
          binding.refresh.isRefreshing = false
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
    with(binding.rvArticleList) {
      layoutManager = LinearLayoutManager(activity)
      adapter = concatAdapter
    }
  }

  private fun handleRefresh() {
    observe()
  }
}