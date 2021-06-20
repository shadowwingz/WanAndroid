package com.shadowwingz.wanandroid.architecture.testpage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.utils.LogUtil

class TestArticleActivity : AppCompatActivity() {

  private val articleArticle by lazy {
    ViewModelProvider(this).get(TestArticleViewModel::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_test_article)

    articleArticle.articleRequest.fetchArticle(1)

    articleArticle.articleRequest.getArticleLiveData().observe(this) { dataResult ->
      if (!dataResult.getResponseStatus().success) {
        return@observe
      }
      LogUtil.d(dataResult.getResult().toString())
    }
  }
}