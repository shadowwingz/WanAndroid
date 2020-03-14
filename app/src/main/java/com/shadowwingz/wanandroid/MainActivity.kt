package com.shadowwingz.wanandroid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shadowwingz.bean.ArticleBean
import com.shadowwingz.net.ApiCallback
import com.shadowwingz.net.ApiClient
import com.shadowwingz.utils.SLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mCompositeDisposable = CompositeDisposable()

    private val items = listOf(
        ArticleListBean("2020-01-01", "给初学者的RxJava2.0教程（七）: Flowable", "111", true),
        ArticleListBean("2020-01-01", "Android之View的诞生之谜", "111", true),
        ArticleListBean("2020-01-01", "Android之自定义View的死亡三部曲之Measure", "111", true),
        ArticleListBean("2020-01-01", "Using ThreadPoolExecutor in Android ", "111", true),
        ArticleListBean("2020-01-01", "Android之自定义 ", "111", true),
        ArticleListBean("2020-01-01", "Android之自定义 ", "111", true),
        ArticleListBean("2020-01-01", "Android之自定义 ", "111", true),
        ArticleListBean("2020-01-01", "Android之自定义 ", "111", true),
        ArticleListBean("2020-01-01", "Using ThreadPoolExecutor in Android ", "111", true),
        ArticleListBean("2020-01-01", "Kotlin 泛型定义与 Java 类似，但有着更多特性支持。", "111", true),
        ArticleListBean("2020-01-01", "Android 高质量录音库。", "111", true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = ArticleListAdapter(items)

        val subscribe = ApiClient.retrofit().loadData("101190201").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<ArticleBean>() {
                override fun onSuccess(model: ArticleBean) {
                    SLog.d(model.weatherinfo.toString())
                    Toast.makeText(
                        this@MainActivity,
                        model.weatherinfo.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(msg: String?) {
                    SLog.d("失败 " + msg)
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
