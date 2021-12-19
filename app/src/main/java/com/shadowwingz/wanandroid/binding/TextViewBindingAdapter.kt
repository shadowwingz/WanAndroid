package com.shadowwingz.wanandroid.binding

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

object TextViewBindingAdapter {
  
  @BindingAdapter("htmlText")
  @JvmStatic
  fun loadHtmlText(view: TextView, text: String) {
    view.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
  }
}