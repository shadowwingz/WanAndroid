package com.shadowwingz.wanandroid.ui.mine.viewbinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.drakeet.multitype.ItemViewBinder
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.ui.mine.viewbinder.item.AvatarItem

class AvatarViewBinder : ItemViewBinder<AvatarItem, AvatarViewBinder.HeaderHolder>() {

  class HeaderHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val ivAvatar: ImageView = itemView.findViewById(R.id.civUserIcon)
    val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
  }

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): HeaderHolder {
    return HeaderHolder(inflater.inflate(R.layout.item_profile_avatar, parent, false))
  }

  override fun onBindViewHolder(holder: HeaderHolder, item: AvatarItem) {
    holder.ivAvatar.load(item.header) {
      transformations(CircleCropTransformation())
    }
    holder.tvUserName.text = item.username
  }
}