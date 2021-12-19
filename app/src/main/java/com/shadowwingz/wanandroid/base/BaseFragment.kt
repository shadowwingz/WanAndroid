package com.shadowwingz.wanandroid.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
  
  private var progressDialog: ProgressDialog? = null
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(getLayoutId(), container, false)
  }
  
  abstract fun getLayoutId(): Int
  
  fun showProgressDialog() {
    if (progressDialog == null) {
      progressDialog = ProgressDialog(activity)
      progressDialog?.setMessage("正在加载...")
      progressDialog?.setCanceledOnTouchOutside(false)
    }
    progressDialog?.show()
  }
  
  fun closeProgressDialog() {
    progressDialog?.dismiss()
  }
}