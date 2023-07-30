package com.shadowwingz.wanandroid.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shadowwingz.wanandroid.bean.ArticleListBean
import com.shadowwingz.wanandroid.bean.QuestionBean
import com.shadowwingz.wanandroid.ui.account.AccountBean
import com.shadowwingz.wanandroid.ui.account.AccountService
import com.shadowwingz.wanandroid.ui.account.UserBean
import com.shadowwingz.wanandroid.ui.article.ArticlePagingSource
import com.shadowwingz.wanandroid.ui.article.ArticleService
import com.shadowwingz.wanandroid.ui.question.QuestionService
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataRepository {
  
  private const val NETWORK_PAGE_SIZE = 20
  
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
  
  suspend fun requestLogin(userBean: UserBean): AccountBean {
    return retrofit.create(AccountService::class.java).login(userBean.name, userBean.password)
  }
  
  // suspend fun requestBanner(): BannerBean {
  //   return retrofit.create(ArticleService::class.java).getBanner()
  // }
  
  suspend fun requestQuestions(pageId: Int): QuestionBean {
    return retrofit.create(QuestionService::class.java).fetchQuestions(pageId)
  }
  
}