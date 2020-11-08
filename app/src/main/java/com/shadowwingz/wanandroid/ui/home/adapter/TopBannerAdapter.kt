package com.shadowwingz.wanandroid.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.bean.BannerBean
import com.shadowwingz.wanandroid.databinding.LayoutBannerBinding
import com.youth.banner.indicator.RectangleIndicator
import com.youth.banner.util.BannerUtils
import kotlinx.android.synthetic.main.layout_banner.view.*

class TopBannerAdapter() :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private val dataList: List<BannerBean> = listOf(
          BannerBean("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png", "")
  )

  private var mContext: Context? = null
  private var bind: LayoutBannerBinding? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    mContext = parent.context
    val v = LayoutInflater.from(mContext).inflate(R.layout.layout_banner, parent, false)
    return BannerViewHolder(v)
  }

  override fun getItemCount(): Int {
    return dataList.size
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    holder.itemView.banner.apply {
      adapter = ImageNetAdapter(dataList)
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