package com.shadowwingz.wanandroid.architecture.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shadowwingz.wanandroid.architecture.response.DataResult
import com.shadowwingz.wanandroid.architecture.response.ResponseStatus
import com.shadowwingz.wanandroid.architecture.response.ResultSource
import com.shadowwingz.wanandroid.architecture.testpage.TestWanAndroidService
import com.shadowwingz.wanandroid.bean.ArticleBean
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.bean.QuestionBean
import com.shadowwingz.wanandroid.bean.User
import com.shadowwingz.wanandroid.ui.account.AccountBean
import com.shadowwingz.wanandroid.ui.account.AccountService
import com.shadowwingz.wanandroid.ui.article.ArticlePagingSource
import com.shadowwingz.wanandroid.ui.article.ArticleService
import com.shadowwingz.wanandroid.ui.question.QuestionService
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataRepository {

  const val NETWORK_PAGE_SIZE = 20

  private val retrofit: Retrofit

  init {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder()
      .connectTimeout(8, TimeUnit.SECONDS)
      .readTimeout(8, TimeUnit.SECONDS)
      .writeTimeout(8, TimeUnit.SECONDS)
      .addInterceptor(logging)
      .build()
    retrofit = Retrofit.Builder()
      .baseUrl(APIs.BASE_URL)
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  fun getArticleResultStream(): Flow<PagingData<ArticleListBean>> {
    return Pager(
      config = PagingConfig(
        pageSize = NETWORK_PAGE_SIZE,
        enablePlaceholders = false
      ),
      pagingSourceFactory = { ArticlePagingSource(retrofit.create(ArticleService::class.java)) }
    ).flow
  }

  private var mArticleCall: Call<ArticleBean>? = null

  fun fetchArticle(pageId: Int, result: DataResult.Result<ArticleBean>) {
    mArticleCall = retrofit.create(TestWanAndroidService::class.java).getArticleList(pageId)
    mArticleCall?.enqueue(object : Callback<ArticleBean> {
      override fun onResponse(call: Call<ArticleBean>, response: Response<ArticleBean>) {
        val responseStatus = ResponseStatus(response.code().toString(), response.isSuccessful, ResultSource.NETWORK)
        result.onResult(DataResult(response.body(), responseStatus))
      }

      override fun onFailure(call: Call<ArticleBean>, t: Throwable) {
        result.onResult(DataResult(null, ResponseStatus(t.message, false, ResultSource.NETWORK)))
        mArticleCall = null
      }
    })
  }

  fun cancelFetch() {
    mArticleCall?.cancel()
    mArticleCall = null
  }

  private var mAccountCall: Call<AccountBean>? = null

  fun requestLogin(user: User, result: DataResult.Result<AccountBean>) {
    mAccountCall = retrofit.create(AccountService::class.java).login(user.name, user.password)
    mAccountCall?.enqueue(object : Callback<AccountBean> {
      override fun onResponse(call: Call<AccountBean>, response: Response<AccountBean>) {
        val responseStatus = ResponseStatus(response.code().toString(), response.isSuccessful, ResultSource.NETWORK)
        result.onResult(DataResult(response.body(), responseStatus))
      }

      override fun onFailure(call: Call<AccountBean>, t: Throwable) {
        result.onResult(DataResult(null, ResponseStatus(t.message, false, ResultSource.NETWORK)))
        mAccountCall = null
      }

    })
  }

  fun cancelLogin() {
    mAccountCall?.cancel()
    mAccountCall = null
  }

  private var mQuestionCall: Call<QuestionBean>? = null

  fun requestQuestions(pageId: Int, result: DataResult.Result<QuestionBean>) {
    mQuestionCall = retrofit.create(QuestionService::class.java).fetchQuestions(pageId)
    mQuestionCall?.enqueue(object : Callback<QuestionBean> {
      override fun onResponse(call: Call<QuestionBean>, response: Response<QuestionBean>) {
        val responseStatus = ResponseStatus(response.code().toString(), response.isSuccessful, ResultSource.NETWORK)
        result.onResult(DataResult(response.body(), responseStatus))
      }

      override fun onFailure(call: Call<QuestionBean>, t: Throwable) {
        result.onResult(DataResult(null, ResponseStatus(t.message, false, ResultSource.NETWORK)))
        mQuestionCall = null
      }

    })
  }

  fun cancelRequestQuestions() {
    mQuestionCall?.cancel()
    mQuestionCall = null
  }
}