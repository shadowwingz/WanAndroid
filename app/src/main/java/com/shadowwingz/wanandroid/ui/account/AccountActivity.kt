package com.shadowwingz.wanandroid.ui.account

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shadowwingz.wanandroid.BaseActivity
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.utils.LogUtil
import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : BaseActivity() {
  
  private val mViewModel by lazy {
    ViewModelProvider(this).get(AccountViewModel::class.java)
  }
  
  override fun getLayoutId(): Int = R.layout.activity_account
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    btnLogin.setOnClickListener {
      val user = UserBean(etUserName.text.toString(), etPassword.text.toString())
      mViewModel.login(user)
      
      mViewModel.accountRequest.tokenLiveData.observe(this, object: Observer<AccountBean>{
        override fun onChanged(accountBean: AccountBean) {
          LogUtil.d(accountBean.toString())
        }
      })
    }
    
  }
  
}