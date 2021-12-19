package com.shadowwingz.wanandroid.ui.account

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.shadowwingz.wanandroid.BaseActivity
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.User
import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : BaseActivity() {
  
  private val mState by lazy {
    ViewModelProvider(this).get(AccountViewModel::class.java)
  }
  
  override fun getLayoutId(): Int = R.layout.activity_account
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    btnLogin.setOnClickListener {
      val user = User(etUserName.text.toString(), etPassword.text.toString())
      mState.accountRequest.requestLogin(user)
    }
    
  }
  
}