package com.shadowwingz.wanandroid.ui.question;

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.shadowwingz.wanandroid.architecture.domain.BaseRequest
import com.shadowwingz.wanandroid.architecture.domain.DataRepository
import com.shadowwingz.wanandroid.bean.QuestionBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * created by shadowwingz on 2021-06-29 20:39
 */
class QuestionRequest : BaseRequest(), DefaultLifecycleObserver {
  
  val questionLiveData: MutableLiveData<QuestionBean> = MutableLiveData()
  
  /**
   * 每日问答
   */
  suspend fun getQuestions(pageId: Int) {
    withContext(Dispatchers.IO) {
      questionLiveData.postValue(DataRepository.requestQuestions(pageId))
    }
  }
}
