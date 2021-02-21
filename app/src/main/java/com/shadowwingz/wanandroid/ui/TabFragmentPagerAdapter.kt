package com.shadowwingz.wanandroid.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabFragmentPagerAdapter(fm: FragmentManager, behavior: Int, fragments: List<Fragment>) :
        FragmentPagerAdapter(fm, behavior) {
  
  private val mFragments = fragments
  
  override fun getItem(position: Int): Fragment {
    return mFragments[position]
  }
  
  override fun getCount(): Int {
    return mFragments.size
  }
  
}