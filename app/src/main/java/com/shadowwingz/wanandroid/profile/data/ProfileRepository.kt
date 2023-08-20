package com.shadowwingz.wanandroid.profile.data

import com.shadowwingz.wanandroid.profile.data.model.ProfileResponse
import timber.log.Timber
import javax.inject.Inject

class ProfileRepository @Inject constructor(
  private val profileRemoteDataSource: ProfileRemoteDataSource,
  private val profileLocalDataSource: ProfileLocalDataSource
) {

  suspend fun queryProfile(): Result<ProfileResponse> {
    return profileRemoteDataSource
      .queryProfile()
      .onSuccess {
        profileLocalDataSource.insertProfile(it.data)
      }
      .onFailure {
        Timber.d(it)
      }
  }
}