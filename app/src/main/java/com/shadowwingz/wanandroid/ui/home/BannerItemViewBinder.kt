package com.shadowwingz.wanandroid.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.drakeet.multitype.ItemViewBinder
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.BannerData

class BannerItemViewBinder : ItemViewBinder<List<BannerData>, BannerItemViewBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val banner: BGABanner = itemView.findViewById(R.id.banner)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.layout_banner, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: List<BannerData>) {
        val list = mutableListOf<View>()
        for (data in item) {
            val rootView = View.inflate(holder.banner.context, R.layout.item_banner, null)
            val imageView: ImageView = rootView.findViewById(R.id.ivBanner)
            Glide.with(holder.banner.context).load(data.imagePath).into(imageView)
            list.add(imageView)
        }
        holder.banner.setData(list as List<View>?)
    }
}