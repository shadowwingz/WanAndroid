package com.shadowwingz.wanandroid.question.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.map
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.base.BaseFragment
import com.shadowwingz.wanandroid.databinding.FragmentQuestionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class QuestionFragment @Inject constructor() : BaseFragment() {

  private lateinit var binding: FragmentQuestionBinding

  private val viewModel by viewModels<QuestionViewModel>()

  private val adapter: QuestionAdapter by lazy { QuestionAdapter() }
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentQuestionBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun getLayoutId(): Int {
    return R.layout.fragment_question
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.rvQuestion.adapter = adapter

    lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.question.collect {
          Timber.d("question update")
          adapter.submitData(it.map { question -> question.toQuestionUiModel(activity) })
        }
      }
    }
  }
}