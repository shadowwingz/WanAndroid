package com.shadowwingz.wanandroid.profile.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.drakeet.multitype.MultiTypeAdapter
import com.shadowwingz.wanandroid.account.ui.AccountActivity
import com.shadowwingz.wanandroid.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment @Inject constructor() : Fragment() {

  private val viewModel: ProfileFragmentViewModel by viewModels()

  private lateinit var binding: FragmentProfileBinding

  @Inject
  lateinit var avatarItemViewBinder: AvatarItemViewBinder

  private val adapter by lazy {
    MultiTypeAdapter().apply {
      register(avatarItemViewBinder)
      register(ProfileItemViewBinder())
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    binding.rvProfile.adapter = adapter

    lifecycleScope.launch {
      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.profiles.collect { (topProfile, profileItems) ->
            adapter.items = mutableListOf<Any>().apply {
              add(topProfile)
              addAll(profileItems)
            }
            adapter.notifyDataSetChanged()
          }
        }

        launch {
          viewModel.errorTip.collect {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
          }
        }

        launch {
          avatarItemViewBinder.clickEvent.collect {
            startActivity(Intent(activity, AccountActivity::class.java))
          }
        }
      }
    }
  }
}