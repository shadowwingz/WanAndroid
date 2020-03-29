package com.shadowwingz.wanandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shadowwingz.bean.ArticleBean
import com.shadowwingz.bean.ArticleListBean
import com.shadowwingz.net.ApiCallback
import com.shadowwingz.net.ApiClient
import com.shadowwingz.utils.SLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mCompositeDisposable = CompositeDisposable()

    private val items = ArrayList<ArticleListBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = ArticleListAdapter(items)
        queryData()
    }

    fun queryData() {
        val subscribe = ApiClient.retrofit().loadData("0")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<ArticleBean>() {
                override fun onSuccess(model: ArticleBean) {
                    val data = model.data.articleListBean as ArrayList
                    val articleListAdapter = rv_main.adapter as ArticleListAdapter
                    articleListAdapter.setData(data)
                }

                override fun onFailure(msg: String?) {
                    SLog.d("失败 $msg")
                }

                override fun onFinish() {
                    SLog.d("完成")
                }
            })

        mCompositeDisposable.add(subscribe)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mCompositeDisposable.size() > 0) {
            mCompositeDisposable.clear()
        }
    }
}
