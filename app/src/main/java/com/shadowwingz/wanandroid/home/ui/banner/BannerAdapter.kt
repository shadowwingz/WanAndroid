package com.shadowwingz.wanandroid.home.ui.banner;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.extension.dp
import com.shadowwingz.wanandroid.extension.emptyImageView

/**
 * created by shadowwingz on 2021-07-11 15:33
 */
class BannerAdapter : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

  private val items: MutableList<BannerUiModel> = mutableListOf()
  val list = mutableListOf<View>()

  fun setItems(items: List<BannerUiModel>) {
    this.items.clear()
    this.items.addAll(items)
    notifyDataSetChanged()
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val banner: BGABanner = itemView.findViewById(R.id.banner)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      LayoutInflater.from(parent.context)
        .inflate(R.layout.layout_banner, parent, false)
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    list.clear()
    val context = holder.itemView.context
    for (data in items) {
      val imageView = context.emptyImageView(width = ViewGroup.LayoutParams.MATCH_PARENT, height = 200.dp)
      Glide.with(context).load(data.imagePath).into(imageView)
      list.add(imageView)
    }
    holder.banner.setData(list)
  }

  override fun getItemCount(): Int {
    return 1
  };
}
