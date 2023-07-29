package com.shadowwingz.wanandroid.ui.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shadowwingz.wanandroid.R
import javax.inject.Inject

class SystemFragment @Inject constructor() : Fragment() {
  
  companion object {
    fun newInstance() = SystemFragment()
  }
  
  private val viewModel by viewModels<SystemViewModel>()
  
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.system_fragment, container, false)
  }
}