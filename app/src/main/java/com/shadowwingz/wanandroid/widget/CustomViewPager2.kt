package com.shadowwingz.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.core.view.size
import androidx.viewpager2.widget.ViewPager2
import cn.bingoogolapple.bgabanner.BGABanner
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.extension.isTouching
import timber.log.Timber
import kotlin.math.abs

/**
 * 解决 ViewPager2 滑动冲突
 * 当用户在 Banner 上滑动时，如果不是在最后一个 Banner，就让 Banner 响应滑动事件。
 * 如果是在最后一个 Banner，就判断手指是否是从右往左滑动，如果是，就让 ViewPager2 响应滑动事件，否则让 Banner 响应滑动事件。
 */
class CustomViewPager2 @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
  FrameLayout(context, attrs, defStyleAttr) {

  var startX = 0f
  var startY = 0f

  private val viewpager: ViewPager2 by lazy { findViewById(R.id.viewpager) }
  private val banner: BGABanner by lazy { findViewById(R.id.banner) }

  override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
    when (ev.action) {
      MotionEvent.ACTION_DOWN -> {
        startX = ev.x
        startY = ev.y
      }
      MotionEvent.ACTION_MOVE -> {
        viewpager.isUserInputEnabled =
          if (viewpager.currentItem != 0) {
            true
          } else {
            val deltaX = ev.x - startX
            val distanceX = abs(deltaX)
            val distanceY = abs(ev.y - startY)
            val bannerCount = banner.size
            val userWantSwipeBanner = banner.currentItem < bannerCount || (banner.currentItem == bannerCount && distanceX > distanceY && deltaX > 0)
            Timber.d("isTouchingBanner: ${banner.isTouching(ev)}, userWantSwipeBanner: $userWantSwipeBanner")
            !(banner.isTouching(ev) && userWantSwipeBanner)
          }
      }
    }
    return super.onInterceptTouchEvent(ev)
  }
}