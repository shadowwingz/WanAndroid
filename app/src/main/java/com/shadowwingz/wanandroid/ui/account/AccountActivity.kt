package com.shadowwingz.wanandroid.ui.account

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.base.BaseActivity
import com.shadowwingz.wanandroid.databinding.ActivityAccountBinding
import com.shadowwingz.wanandroid.utils.LogUtil

class AccountActivity : BaseActivity() {

  private val binding by lazy { ActivityAccountBinding.inflate(layoutInflater) }

  private val viewModel by viewModels<AccountViewModel>()

  override fun getLayoutId(): Int = R.layout.activity_account

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    binding.btnLogin.setOnClickListener {
      val user = UserBean(binding.etUserName.text.toString(), binding.etPassword.text.toString())
      viewModel.login(user)

      viewModel.accountRequest.tokenLiveData.observe(this, object : Observer<AccountBean> {
        override fun onChanged(accountBean: AccountBean) {
          LogUtil.d(accountBean.toString())
        }
      })
    }
  }
}