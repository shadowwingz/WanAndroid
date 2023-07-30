package com.shadowwingz.wanandroid.ui.article.banner;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.article.domain.model.BannerUiModel

/**
 * created by shadowwingz on 2021-07-11 15:33
 */
class BannerAdapter : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {
  
  private val mItems: MutableList<BannerUiModel> = mutableListOf()
  
  fun setItems(items: List<BannerUiModel>) {
    mItems.clear()
    mItems.addAll(items)
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
    val list = mutableListOf<View>()
    for (data in mItems) {
      val rootView = View.inflate(holder.banner.context, R.layout.item_banner, null)
      val imageView: ImageView = rootView.findViewById(R.id.ivBanner)
      Glide.with(holder.banner.context).load(data.imagePath).into(imageView)
      list.add(imageView)
    }
    holder.banner.setData(list as List<View>?)
  }
  
  override fun getItemCount(): Int {
    return 1
  };
}
