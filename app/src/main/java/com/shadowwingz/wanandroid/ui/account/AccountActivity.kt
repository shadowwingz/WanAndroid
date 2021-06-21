package com.shadowwingz.wanandroid.ui.account

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.User
import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : AppCompatActivity() {

  private val mState by lazy {
    ViewModelProvider(this).get(AccountViewModel::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_account)

    btnLogin.setOnClickListener {
      val user = User(etUserName.text.toString(), etPassword.text.toString())
      mState.accountRequest.requestLogin(user)
    }

  }

}