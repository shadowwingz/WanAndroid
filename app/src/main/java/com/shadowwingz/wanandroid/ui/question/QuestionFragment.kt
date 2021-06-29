package com.shadowwingz.wanandroid.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.architecture.response.DataResult
import com.shadowwingz.wanandroid.bean.QuestionBean
import kotlinx.android.synthetic.main.question_fragment.*

class QuestionFragment : Fragment() {

  private val mState by lazy {
    ViewModelProvider(this).get(QuestionViewModel::class.java)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.question_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val adapter = QuestionAdapter()
    rvQuestion.adapter = adapter

    mState.getQuestions(1)

    mState.questionRequest.questionLiveData.observe(viewLifecycleOwner, object : Observer<DataResult<QuestionBean>> {
      override fun onChanged(t: DataResult<QuestionBean>?) {
        adapter.submitList(t?.getResult()?.data?.data)
      }
    })
  }
}