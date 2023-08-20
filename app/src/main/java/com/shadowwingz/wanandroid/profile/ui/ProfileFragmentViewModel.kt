package com.shadowwingz.wanandroid.profile.ui

import androidx.lifecycle.ViewModel
import com.shadowwingz.wanandroid.di.coroutines.ApplicationScope
import com.shadowwingz.wanandroid.profile.data.model.ProfileUiModel
import com.shadowwingz.wanandroid.profile.domain.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(
  private val profileUseCase: ProfileUseCase,
  @ApplicationScope private val scope: CoroutineScope
) : ViewModel() {

  private val _errorTip: MutableSharedFlow<String> = MutableSharedFlow()
  val errorTip: Flow<String> = _errorTip

  val profile: StateFlow<ProfileUiModel> = flow {
    profileUseCase(Unit)
      .onSuccess {
        emit(it)
      }
      .onFailure {
        _errorTip.emit(it.toString())
        emit(ProfileUiModel())
      }
  }.stateIn(scope, SharingStarted.WhileSubscribed(), ProfileUiModel())
}