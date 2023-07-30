package com.shadowwingz.wanandroid.extension

import android.content.Context
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

fun View.isTouching(event: MotionEvent): Boolean {
  val rect = Rect()
  getHitRect(rect)
  return rect.contains(event.x.toInt(), event.y.toInt())
}

fun Context.emptyImageView(width: Int, height: Int): ImageView {
  return ImageView(this).apply {
    layoutParams = ViewGroup.LayoutParams(
      width,
      height
    )
    scaleType = ImageView.ScaleType.FIT_XY
  }
}