package com.shadowwingz.wanandroid.profile.ui

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
import com.shadowwingz.wanandroid.di.coroutines.ApplicationScope
import com.shadowwingz.wanandroid.profile.data.model.ProfileUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AvatarItemViewBinder @Inject constructor(
  @ApplicationScope private val scope: CoroutineScope
) : ItemViewBinder<ProfileUiModel, AvatarItemViewBinder.AvatarHolder>() {

  private val _clickEvent: MutableSharedFlow<Unit> = MutableSharedFlow()
  val clickEvent: SharedFlow<Unit> = _clickEvent

  class AvatarHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivAvatar: ImageView = itemView.findViewById(R.id.civUserIcon)
    val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
    val tvLevel: TextView = itemView.findViewById(R.id.tv_level)
    val tvRank: TextView = itemView.findViewById(R.id.tv_rank)
  }

  override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): AvatarHolder {
    return AvatarHolder(inflater.inflate(R.layout.item_profile_avatar, parent, false))
  }

  override fun onBindViewHolder(holder: AvatarHolder, item: ProfileUiModel) {
    holder.ivAvatar.load(item.header) {
      transformations(CircleCropTransformation())
    }
    holder.tvUserName.text = item.username
    holder.itemView.setOnClickListener {
      scope.launch {
        _clickEvent.emit(Unit)
      }
    }
    holder.tvLevel.text = holder.itemView.resources.getString(R.string.level, item.level)
    holder.tvRank.text = holder.itemView.resources.getString(R.string.rank, item.rank)
  }
}