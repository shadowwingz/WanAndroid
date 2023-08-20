package com.shadowwingz.wanandroid.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.base.BaseActivity
import com.shadowwingz.wanandroid.databinding.ActivityMainBinding
import com.shadowwingz.wanandroid.home.ui.HomeFragment
import com.shadowwingz.wanandroid.profile.ui.ProfileFragment
import com.shadowwingz.wanandroid.question.ui.QuestionFragment
import com.shadowwingz.wanandroid.ui.system.SystemFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

  @Inject
  lateinit var homeFragment: HomeFragment

  @Inject
  lateinit var questionFragment: QuestionFragment

  @Inject
  lateinit var systemFragment: SystemFragment

  @Inject
  lateinit var profileFragment: ProfileFragment

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

  override fun getLayoutId(): Int = R.layout.activity_main

  @RequiresApi(Build.VERSION_CODES.R)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    binding.viewpager.apply {
      adapter = object : FragmentStateAdapter(this@MainActivity) {
        override fun getItemCount(): Int {
          return 4
        }

        override fun createFragment(position: Int): Fragment {
          return when (position) {
            0 -> homeFragment
            1 -> questionFragment
            2 -> systemFragment
            else -> profileFragment
          }
        }
      }

      registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
          binding.navView.apply {
            selectedItemId = menu.getItem(position).itemId
          }
        }
      })
    }

    // 当 ViewPager 切换页面时，改变 ViewPager 的显示
    binding.navView.setOnNavigationItemSelectedListener {

      fun performClickNavigationItem(index: Int) {
        if (binding.viewpager.currentItem != index) {
          if (!binding.viewpager.isFakeDragging) {
            binding.viewpager.currentItem = index
          }
        }
      }

      when (it.itemId) {
        R.id.navigation_home -> performClickNavigationItem(0)
        R.id.navigation_question -> performClickNavigationItem(1)
        R.id.navigation_system -> performClickNavigationItem(2)
        R.id.navigation_mine -> performClickNavigationItem(3)
      }
      true
    }

    binding.navView.setOnClickListener { /*Do nothing*/ }

    window.apply {
      setDecorFitsSystemWindows(true)
    }
    // supportActionBar?.hide()
  }
}