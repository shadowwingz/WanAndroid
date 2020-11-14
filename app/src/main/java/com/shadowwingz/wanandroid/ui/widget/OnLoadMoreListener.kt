package com.shadowwingz.wanandroid.ui.widget

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 上拉加载更多
 */
abstract class OnLoadMoreListener : RecyclerView.OnScrollListener() {
  
  /**
   * 判断页面是否正在向上滑动
   */
  private var isSlidingUp: Boolean = false
  
  override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
    super.onScrollStateChanged(recyclerView, newState)
    val manager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
      val lastItemPos = manager.findLastVisibleItemPosition()
      val itemCount = manager.itemCount
      
      if (lastItemPos == (itemCount - 1) && isSlidingUp) {
        onLoadMore()
      }
    }
  }
  
  override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
    super.onScrolled(recyclerView, dx, dy)
    isSlidingUp = dy > 0
  }
  
  abstract fun onLoadMore()
}