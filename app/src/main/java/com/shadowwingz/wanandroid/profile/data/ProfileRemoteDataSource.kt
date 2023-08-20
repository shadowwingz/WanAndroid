package com.shadowwingz.wanandroid.profile.data

import com.shadowwingz.wanandroid.profile.data.api.ProfileService
import com.shadowwingz.wanandroid.profile.data.model.ProfileResponse
import safeApiCall
import java.io.IOException
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(
  private val profileService: ProfileService
) {

  suspend fun queryProfile() = safeApiCall(
    call = { requestQuery() },
    errorMessage = "加载个人信息失败"
  )

  private suspend fun requestQuery(): Result<ProfileResponse> {
    val response = profileService.query()
    if (response.isSuccessful) {
      val body = response.body()
      body?.let {
        if (body.errorCode == 0) {
          return Result.success(body)
        } else {
          return Result.failure(IOException("${body.errorCode} ${body.errorMsg}"))
        }
      }
    }
    return Result.failure(IOException("${response.code()} ${response.message()}"))
  }
}