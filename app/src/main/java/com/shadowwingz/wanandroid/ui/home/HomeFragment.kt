package com.shadowwingz.wanandroid.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.ui.BaseFragment
import com.shadowwingz.wanandroid.ui.widget.OnLoadMoreListener
import com.shadowwingz.wanandroid.utils.InjectorUtil
import com.shadowwingz.wanandroid.utils.LogUtil
import kotlinx.android.synthetic.main.fragment_home.*

@SuppressLint("NotifyDataSetChanged")
class HomeFragment : BaseFragment() {

  private val viewModel by lazy {
    ViewModelProviders.of(this, InjectorUtil.getArticleModeFactory()).get(HomeFragmentViewModel::class.java)
  }

  var adapter = MultiTypeAdapter()

  override fun getLayoutId(): Int {
    return R.layout.fragment_home
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    LogUtil.d("HomeFragment onViewCreated")
    init()
    viewModel.loadData()
    observe()
  }

  private fun init() {
    initAdapter()

    initRefreshListener()

    rvArticleList.addOnScrollListener(object : OnLoadMoreListener() {
      override fun onLoadMore() {
        viewModel.queryArticle(++viewModel.pageId)
      }
    })
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
      // 如果前后两次的数据源是同一个对象，submitList 方法内部会 return，不会触发 RecyclerView 的刷新，
      // 解决方法：用 ArrayList 将数据源包装一层，保证每次传入 submitList 方法的对象都是新对象。
//      articleListAdapter.submitList(ArrayList(viewModel.dataList))
      refresh.isRefreshing = false

      adapter.items = viewModel.allData
      adapter.notifyDataSetChanged()
    })
  }

  private fun initRefreshListener() {
    refresh.setColorSchemeResources(
      android.R.color.holo_blue_dark,
      android.R.color.holo_blue_light,
      android.R.color.holo_green_light,
      android.R.color.holo_green_light
    );

    refresh.setOnRefreshListener {
      refresh.isRefreshing = true
      viewModel.loadData()
    }
  }

  private fun initAdapter() {
//    articleListAdapter.setHasStableIds(true)

    rvArticleList.layoutManager = LinearLayoutManager(activity)
    rvArticleList.adapter = adapter

    adapter.register(BannerItemViewBinder())
    adapter.register(ArticleItemViewBinder())

    adapter.items = viewModel.allData
    adapter.notifyDataSetChanged()
  }

}