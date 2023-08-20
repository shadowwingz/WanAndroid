package com.shadowwingz.wanandroid.profile.ui

import androidx.lifecycle.ViewModel
import com.shadowwingz.wanandroid.profile.data.ProfileRepository
import com.shadowwingz.wanandroid.profile.data.model.ProfileItemUiModel
import com.shadowwingz.wanandroid.profile.data.model.ProfileUiModel
import com.shadowwingz.wanandroid.profile.domain.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(
  private val profileUseCase: ProfileUseCase,
  private val profileRepository: ProfileRepository,
) : ViewModel() {

  private val _errorTip: MutableSharedFlow<String> = MutableSharedFlow()
  val errorTip: Flow<String> = _errorTip

  private val profileListItems: Flow<List<ProfileItemUiModel>> = flow {
    emit(profileRepository.queryProfileItems())
  }

  private val topProfile: Flow<ProfileUiModel> = flow {
    profileUseCase(Unit)
      .onSuccess {
        emit(it)
      }
      .onFailure {
        _errorTip.emit(it.toString())
        emit(ProfileUiModel())
      }
  }

  val profiles =
    topProfile.zip(profileListItems) { profile, profileList ->
      profileList.find {
        it.id == "coin"
      }?.let {
        it.rightDesc = profile.coinCount.toString()
      }
      profile to profileList
    }
}