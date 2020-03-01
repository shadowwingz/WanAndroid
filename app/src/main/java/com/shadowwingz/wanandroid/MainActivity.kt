package com.shadowwingz.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
    }
}
