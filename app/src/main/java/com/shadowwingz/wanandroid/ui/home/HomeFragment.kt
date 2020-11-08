package com.shadowwingz.wanandroid.ui.home

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.ui.article.ArticleListAdapter
import com.shadowwingz.wanandroid.ui.article.ArticleListViewModel
import com.shadowwingz.wanandroid.ui.home.adapter.TopBannerAdapter
import com.shadowwingz.wanandroid.utils.InjectorUtil
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
  
  private var progressDialog: ProgressDialog? = null
  
  val HomeFragmentViewModel: ViewModel? = null
  
  private val viewModel by lazy {
    ViewModelProviders.of(this, InjectorUtil.getArticleModeFactory()).get(ArticleListViewModel::class.java)
  }
  
  lateinit var articleListAdapter: ArticleListAdapter
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_home, container, false)
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    init()
    queryHomeArticleList()
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
    viewModel.dataChanged.observe(viewLifecycleOwner, Observer {
      articleListAdapter.notifyDataSetChanged()
    })
  }
  
  private fun init() {
    val topBannerAdapter = TopBannerAdapter()
    articleListAdapter = ArticleListAdapter(viewModel.dataList)
    val mergeAdapter = ConcatAdapter(topBannerAdapter, articleListAdapter)
    
    rvHomeFragment.layoutManager = LinearLayoutManager(activity)
    rvHomeFragment.adapter = mergeAdapter
  }
  
  private fun queryHomeArticleList() {
    viewModel.getArticleList()
  }
  
  companion object {
    @JvmStatic
    fun newInstance() = HomeFragment()
  }
  
  private fun showProgressDialog() {
    if (progressDialog == null) {
      progressDialog = ProgressDialog(activity)
      progressDialog?.setMessage("正在加载...")
      progressDialog?.setCanceledOnTouchOutside(false)
    }
    progressDialog?.show()
  }
  
  private fun closeProgressDialog() {
    progressDialog?.dismiss()
  }
  
}