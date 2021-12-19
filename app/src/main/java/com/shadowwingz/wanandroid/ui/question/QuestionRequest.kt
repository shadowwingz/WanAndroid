package com.shadowwingz.wanandroid.ui.question;

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.shadowwingz.wanandroid.architecture.domain.BaseRequest
import com.shadowwingz.wanandroid.architecture.domain.DataRepository
import com.shadowwingz.wanandroid.architecture.response.DataResult
import com.shadowwingz.wanandroid.bean.QuestionBean

/**
 * created by shadowwingz on 2021-06-29 20:39
 */
class QuestionRequest : BaseRequest(), DefaultLifecycleObserver {
  
  val questionLiveData: MutableLiveData<DataResult<QuestionBean>> = MutableLiveData()
  
  /**
   * 每日问答
   */
  fun getQuestions(pageId: Int) {
    DataRepository.requestQuestions(pageId, object : DataResult.Result<QuestionBean> {
      override fun onResult(dataResult: DataResult<QuestionBean>?) {
        questionLiveData.postValue(dataResult)
      }
    })
  }
  
  private fun cancelRequestQuestions() {
    DataRepository.cancelRequestQuestions()
  }
  
  override fun onStop(owner: LifecycleOwner) {
    super.onStop(owner)
    cancelRequestQuestions()
  }
}
