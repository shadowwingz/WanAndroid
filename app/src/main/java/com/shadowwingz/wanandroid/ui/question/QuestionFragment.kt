package com.shadowwingz.wanandroid.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.QuestionBean
import kotlinx.android.synthetic.main.question_fragment.*

class QuestionFragment : Fragment() {
  
  private val viewModel by viewModels<QuestionViewModel>()
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.question_fragment, container, false)
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
    val adapter = QuestionAdapter()
    rvQuestion.adapter = adapter
    
    viewModel.getQuestions(1)
    
    viewModel.questionRequest.questionLiveData.observe(viewLifecycleOwner, object : Observer<QuestionBean> {
      override fun onChanged(questionBean: QuestionBean) {
        adapter.submitList(questionBean.data.data)
      }
    })
  }
}