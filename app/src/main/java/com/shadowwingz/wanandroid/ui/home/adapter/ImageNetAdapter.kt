package com.shadowwingz.wanandroid.ui.home.adapter

import android.os.Build
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.BannerBean
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.util.BannerUtils

class ImageNetAdapter(mDatas: List<BannerBean?>) : BannerAdapter<BannerBean?, ImageHolder?>(mDatas) {

  override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ImageHolder {
    val imageView = BannerUtils.getView(parent!!, R.layout.banner_image) as ImageView
    //通过裁剪实现圆角
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      BannerUtils.setBannerRound(imageView, 20f)
    }
    return ImageHolder(imageView)
  }

  override fun onBindView(holder: ImageHolder?, data: BannerBean?, position: Int, size: Int) {
    Glide.with(holder!!.itemView)
            .load(data!!.imageUrl)
            .thumbnail(Glide.with(holder.itemView).load(R.drawable.loading)) //                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
            .into(holder.imageView)
  }
}