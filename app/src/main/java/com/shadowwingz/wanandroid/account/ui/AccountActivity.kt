package com.shadowwingz.wanandroid.account.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.base.BaseActivity
import com.shadowwingz.wanandroid.databinding.ActivityAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class AccountActivity : BaseActivity() {

  private val binding by lazy { ActivityAccountBinding.inflate(layoutInflater) }

  private val viewModel by viewModels<AccountViewModel>()

  override fun getLayoutId(): Int = R.layout.activity_account

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    binding.btnLogin.setOnClickListener {
      lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
          launch {
            val username = binding.etUserName.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(username, password)
          }
          launch {
            viewModel.loginResult.collect {
              if (it) {
                Timber.d("登录成功")
                showToast("登录成功")
                finish()
              } else {
                Timber.d("登录失败")
                showToast("登录失败")
              }
            }
          }
        }
      }
    }
  }

  private fun showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }
}