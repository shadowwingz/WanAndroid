package com.shadowwingz.wanandroid.ui.home.adapter

import android.os.Build
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.BannerData
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.util.BannerUtils

class ImageNetAdapter(mDatas: List<BannerData?>) : BannerAdapter<BannerData?, ImageHolder?>(mDatas) {
  
  override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ImageHolder {
    val imageView = BannerUtils.getView(parent!!, R.layout.banner_image) as ImageView
    //通过裁剪实现圆角
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      BannerUtils.setBannerRound(imageView, 20f)
    }
    return ImageHolder(imageView)
  }
  
  override fun onBindView(holder: ImageHolder?, data: BannerData?, position: Int, size: Int) {
    Glide.with(holder!!.itemView)
            .load(data!!.imagePath)
            .thumbnail(Glide.with(holder.itemView).load(R.drawable.loading))
            .into(holder.imageView)
  }
}