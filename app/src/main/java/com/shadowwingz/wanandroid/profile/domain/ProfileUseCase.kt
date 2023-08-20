package com.shadowwingz.wanandroid.profile.domain

import com.shadowwingz.wanandroid.core.domain.usebase.CoroutineUseCase
import com.shadowwingz.wanandroid.di.coroutines.IoDispatcher
import com.shadowwingz.wanandroid.profile.data.ProfileRepository
import com.shadowwingz.wanandroid.profile.data.model.ProfileUiModel
import com.shadowwingz.wanandroid.profile.data.model.toProfileUiModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
  private val profileRepository: ProfileRepository,
  @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : CoroutineUseCase<Unit, Result<ProfileUiModel>>(dispatcher) {

  override suspend fun execute(parameters: Unit): Result<ProfileUiModel> {
    return profileRepository.queryProfile().map {
      it.data.toProfileUiModel()
    }
  }
}