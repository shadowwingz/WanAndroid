package com.shadowwingz.wanandroid.profile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.drakeet.multitype.ItemViewBinder
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.profile.data.model.ProfileItemUiModel

class ProfileItemViewBinder : ItemViewBinder<ProfileItemUiModel, ProfileItemViewBinder.ProfileHolder>() {
  
  class ProfileHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
    val tvSubTitle: TextView = itemView.findViewById(R.id.tvSubTitle)
    val tvSubDesc: TextView = itemView.findViewById(R.id.tvSubDesc)
  }
  
  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ProfileHolder {
    return ProfileHolder(inflater.inflate(R.layout.item_profile, parent, false))
  }
  
  override fun onBindViewHolder(holder: ProfileHolder, item: ProfileItemUiModel) {
    holder.ivIcon.load(item.icon)
    holder.tvSubTitle.text = item.leftDesc
    holder.tvSubDesc.text = item.rightDesc
  }
}