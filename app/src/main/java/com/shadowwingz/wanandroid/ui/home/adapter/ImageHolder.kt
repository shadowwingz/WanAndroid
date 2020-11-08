package com.shadowwingz.wanandroid.ui.home.adapter

import android.view.View
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class ImageHolder(@NonNull view: View?) : RecyclerView.ViewHolder(view!!) {
  var imageView: ImageView

  init {
    imageView = view as ImageView
  }
}