package com.shadowwingz.wanandroid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.shadowwingz.wanandroid.BaseActivity
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.ui.article.HomeFragment
import com.shadowwingz.wanandroid.ui.mine.ProfileFragment
import com.shadowwingz.wanandroid.ui.question.QuestionFragment
import com.shadowwingz.wanandroid.ui.system.SystemFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
  
  override fun getLayoutId(): Int = R.layout.activity_main
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    viewpager.apply {
      adapter = object : FragmentStateAdapter(this@MainActivity) {
        override fun getItemCount(): Int {
          return 4
        }
        
        override fun createFragment(position: Int): Fragment {
          return when (position) {
            0 -> HomeFragment()
            1 -> QuestionFragment()
            2 -> SystemFragment()
            else -> ProfileFragment()
          }
        }
      }
      
      // 禁止左右滑动
      isUserInputEnabled = false
    }
    
    // 当 ViewPager 切换页面时，改变 ViewPager 的显示
    navView.setOnNavigationItemSelectedListener {
      
      fun performClickNavigationItem(index: Int) {
        if (viewpager.currentItem != index) {
          if (!viewpager.isFakeDragging) {
            viewpager.currentItem = index
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
    
    navView.setOnClickListener { /*Do nothing*/ }
  }
  
}