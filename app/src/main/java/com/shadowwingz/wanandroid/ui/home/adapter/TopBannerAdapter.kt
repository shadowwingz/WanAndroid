package com.shadowwingz.wanandroid.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.BannerData
import com.shadowwingz.wanandroid.databinding.LayoutBannerBinding
import com.youth.banner.indicator.RectangleIndicator
import com.youth.banner.util.BannerUtils
import kotlinx.android.synthetic.main.layout_banner.view.*

class TopBannerAdapter(var mDataList: ArrayList<BannerData>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {


  private var mContext: Context? = null
  private var bind: LayoutBannerBinding? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    mContext = parent.context
    val v = LayoutInflater.from(mContext).inflate(R.layout.layout_banner, parent, false)
    return BannerViewHolder(v)
  }

  override fun getItemCount(): Int {
    return 1
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    holder.itemView.banner.apply {
      adapter = ImageNetAdapter(mDataList)
      indicator = RectangleIndicator(mContext)
      setIndicatorSpace((BannerUtils.dp2px(4F).toInt()))
      setIndicatorRadius(0)
    }
  }

  inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {
      var banner: View = itemView.findViewById(R.id.banner)
    }
  }
}