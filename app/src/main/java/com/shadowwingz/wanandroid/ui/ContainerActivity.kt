package com.shadowwingz.wanandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.ui.home.HomeFragment
import com.shadowwingz.wanandroid.ui.mine.MineFragment
import kotlinx.android.synthetic.main.activity_container.*

class ContainerActivity : AppCompatActivity() {

  private val mDataList: List<String> = listOf("首页", "问答", "体系", "我的")
  private val mIconList: List<Int> = listOf(
          R.drawable.ic_bottom_bar_home,
          R.drawable.ic_bottom_bar_wechat,
          R.drawable.ic_bottom_bar_navi,
          R.drawable.ic_bottom_bar_mine
  )

  private val mFragments: List<Fragment> = listOf(
          HomeFragment.newInstance(),
          HomeFragment.newInstance(),
          HomeFragment.newInstance(),
          MineFragment.newInstance()
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_container)

    val navController = Navigation.findNavController(this, R.id.fragmentBottomNav)
    val config = AppBarConfiguration.Builder(bottomNavigationView.menu).build()
    NavigationUI.setupActionBarWithNavController(this, navController, config)
    NavigationUI.setupWithNavController(bottomNavigationView, navController)

  }
}