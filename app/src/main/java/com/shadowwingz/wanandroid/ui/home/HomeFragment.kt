package com.shadowwingz.wanandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shadowwingz.wanandroid.ArticleListAdapter
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.ArticleBean
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.model.ArticleListModel
import com.shadowwingz.wanandroid.network.ApiCallback
import com.shadowwingz.wanandroid.network.ApiClient
import com.shadowwingz.wanandroid.ui.article.ArticleListViewModel
import com.shadowwingz.wanandroid.utils.InjectorUtil
import com.shadowwingz.wanandroid.utils.LogUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(
            this,
            InjectorUtil.getArticleModeFactory()
        ).get(ArticleListViewModel::class.java)
    }

    lateinit var articleListModel: ArticleListModel
    lateinit var articleListAdapter: ArticleListAdapter

    private var mCompositeDisposable = CompositeDisposable()

    private val items = ArrayList<ArticleListBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        queryData()
    }

    private fun init() {
//        val binding = DataBindingUtil.bind<ActivityMainBindingImpl>(this)
        rvHomeFragment.layoutManager = LinearLayoutManager(activity)
        rvHomeFragment.adapter = ArticleListAdapter(items)
        articleListAdapter = rvHomeFragment.adapter as ArticleListAdapter
        articleListModel = ViewModelProviders.of(this).get(ArticleListModel::class.java)
        articleListModel.getArticleList().observe(this, object : Observer<List<ArticleListBean>> {
            override fun onChanged(result: List<ArticleListBean>?) {
                if (result != null) {
                    articleListAdapter.setData(result as ArrayList<ArticleListBean>)
                }
            }
        })
    }

    private fun queryData() {
        val subscribe = ApiClient.retrofit().loadData("0")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<ArticleBean>() {
                override fun onSuccess(model: ArticleBean) {
                    val data = model.data.articleListBean as ArrayList
                    articleListModel.setArticleList(data)
                }

                override fun onFailure(msg: String?) {
                    LogUtil.d("失败 $msg")
                }

                override fun onFinish() {
                    LogUtil.d("完成")
                }
            })

        mCompositeDisposable.add(subscribe)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mCompositeDisposable.size() > 0) {
            mCompositeDisposable.clear()
        }
    }
}