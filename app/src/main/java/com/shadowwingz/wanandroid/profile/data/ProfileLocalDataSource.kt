package com.shadowwingz.wanandroid.profile.data

import com.shadowwingz.wanandroid.di.coroutines.IoDispatcher
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
}