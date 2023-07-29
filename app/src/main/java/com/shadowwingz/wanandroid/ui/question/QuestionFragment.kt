package com.shadowwingz.wanandroid.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.shadowwingz.wanandroid.bean.QuestionBean
import com.shadowwingz.wanandroid.databinding.FragmentQuestionBinding
import javax.inject.Inject

class QuestionFragment @Inject constructor() : Fragment() {

  private lateinit var binding: FragmentQuestionBinding

  private val viewModel by viewModels<QuestionViewModel>()
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentQuestionBinding.inflate(inflater, container, false)
    return binding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val adapter = QuestionAdapter()
    binding.rvQuestion.adapter = adapter
    
    viewModel.getQuestions(1)
    
    viewModel.questionRequest.questionLiveData.observe(viewLifecycleOwner, object : Observer<QuestionBean> {
      override fun onChanged(questionBean: QuestionBean) {
        adapter.submitList(questionBean.data.data)
      }
    })
  }
}