package com.shadowwingz.wanandroid.profile.data

import com.shadowwingz.wanandroid.R
import com.shadowwingz.wanandroid.di.coroutines.IoDispatcher
import com.shadowwingz.wanandroid.profile.data.model.ProfileItemUiModel
import com.shadowwingz.wanandroid.profile.data.model.ProfileResponseData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileLocalDataSource @Inject constructor(
  private val profileDao: ProfileDao,
  @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

  suspend fun insertProfile(profileResponseData: ProfileResponseData) {
    withContext(dispatcher) {
      profileDao.insertProfile(profileResponseData)
    }
  }

  fun getProfileItems(): MutableList<ProfileItemUiModel> {
    return mutableListOf(
      ProfileItemUiModel(id = "coin", icon = R.drawable.ic_coin, leftDesc = "我的积分"),
      ProfileItemUiModel(id = "share", icon = R.drawable.ic_share_article, leftDesc = "我的分享"),
      ProfileItemUiModel(id = "collect", icon = R.drawable.ic_collect, leftDesc = "我的收藏"),
      ProfileItemUiModel(id = "later", icon = R.drawable.ic_read_later, leftDesc = "稍后阅读"),
      ProfileItemUiModel(id = "history", icon = R.drawable.ic_read_record, leftDesc = "阅读历史"),
      ProfileItemUiModel(id = "github", icon = R.drawable.ic_github, leftDesc = "开源项目"),
      ProfileItemUiModel(id = "about", icon = R.drawable.ic_about, leftDesc = "关于作者"),
      ProfileItemUiModel(id = "setting", icon = R.drawable.ic_setting, leftDesc = "系统设置"),
    )
  }
}