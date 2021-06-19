package com.shadowwingz.wanandroid.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drakeet.multitype.MultiTypeAdapter
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.ui.mine.viewbinder.AvatarViewBinder
import com.shadowwingz.wanandroid.ui.mine.viewbinder.ProfileViewBinder
import com.shadowwingz.wanandroid.ui.mine.viewbinder.item.AvatarItem
import com.shadowwingz.wanandroid.ui.mine.viewbinder.item.ProfileItem
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

  private val adapter = MultiTypeAdapter()
  private val items = ArrayList<Any>()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_profile, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    adapter.register(AvatarViewBinder())
    adapter.register(ProfileViewBinder())
    rvProfile.adapter = adapter

    val avatarItem = AvatarItem(R.drawable.ic_avatar, "shadowwingz")
    val subItems = listOf(
      ProfileItem.MY_COIN,
      ProfileItem.MY_SHARE,
      ProfileItem.MY_COLLECTION,
      ProfileItem.READ_LATER,
      ProfileItem.READ_RECORD,
      ProfileItem.GITHUB,
      ProfileItem.ABOUT,
      ProfileItem.SETTINGS
    )

    items.add(avatarItem)
    items.addAll(subItems)
    adapter.items = items
    adapter.notifyDataSetChanged()
  }
}