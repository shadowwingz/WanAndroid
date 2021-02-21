package com.shadowwingz.wanandroid.ui.wrapper

import android.content.Context
import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
  
  private var mViews = SparseArray<View>()
  
  companion object {
    @JvmStatic
    fun createViewHolder(context: Context, itemView: View): ViewHolder {
      return ViewHolder(context, itemView)
    }
  }
  
  
}