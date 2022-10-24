package me.blinq.apps.challenge.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.blinq.apps.challenge.core.domain.CancelInviteUseCase
import me.blinq.apps.challenge.core.domain.GetUserDataUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    userDataUseCase: GetUserDataUseCase,
    private val cancelInviteUseCase: CancelInviteUseCase
) : ViewModel() {
    val isRegistered: StateFlow<Boolean> =
        userDataUseCase()
            .map {
                it.name.isNotEmpty() && it.email.isNotEmpty()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.CancelInvite -> _uiState.update {
                it.copy(userMessage = R.string.alert_are_you_sure_you_want_to_cancel_invite)
            }
            is HomeEvent.CancelInviteConfirm -> {
                _uiState.update {
                    it.copy(userMessage = null)
                }
                cancelInvite()
            }
            is HomeEvent.CancelInviteDismiss -> _uiState.update {
                it.copy(
                    userMessage = null
                )
            }

            is HomeEvent.OnNavigateToCongrats -> _uiState.update {
                it.copy(
                    isUnRegistered = false
                )
            }
        }
    }

    private fun cancelInvite() {
        viewModelScope.launch {
            cancelInviteUseCase()

            withContext(Dispatchers.Main) {
                _uiState.update {
                    it.copy(
                        isUnRegistered = true
                    )
                }
            }
        }
    }
}

sealed interface HomeEvent {
    object CancelInvite : HomeEvent
    object CancelInviteConfirm : HomeEvent
    object CancelInviteDismiss : HomeEvent

    object OnNavigateToCongrats : HomeEvent
}

data class HomeUiState(
    val isUnRegistered: Boolean = false,
    val userMessage: Int? = null
)