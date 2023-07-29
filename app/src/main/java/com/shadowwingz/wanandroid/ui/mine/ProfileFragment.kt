package com.shadowwingz.wanandroid.ui.mine

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drakeet.multitype.MultiTypeAdapter
import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.databinding.FragmentProfileBinding
import com.shadowwingz.wanandroid.ui.account.AccountActivity
import com.shadowwingz.wanandroid.ui.mine.viewbinder.AvatarViewBinder
import com.shadowwingz.wanandroid.ui.mine.viewbinder.ProfileViewBinder
import com.shadowwingz.wanandroid.ui.mine.viewbinder.item.AvatarItem
import com.shadowwingz.wanandroid.ui.mine.viewbinder.item.ProfileItem

class ProfileFragment : Fragment() {

  private lateinit var binding: FragmentProfileBinding

  private val adapter = MultiTypeAdapter()
  private val items = ArrayList<Any>()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val avatarViewBinder = AvatarViewBinder()
    adapter.register(avatarViewBinder)
    adapter.register(ProfileViewBinder())
    binding.rvProfile.adapter = adapter

    avatarViewBinder.apply {
      setOnAvatarClickListener(object : AvatarViewBinder.OnAvatarClickListener {
        override fun avatarClick() {
          startActivity(Intent(activity, AccountActivity::class.java))
        }
      })
    }

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