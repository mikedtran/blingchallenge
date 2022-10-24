package me.blinq.apps.challenge.feature.invite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.blinq.apps.challenge.core.data.util.NetworkMonitor
import me.blinq.apps.challenge.core.domain.RequestInviteUseCase
import me.blinq.apps.challenge.core.network.ApiResponseException
import me.blinq.apps.challenge.core.result.Result
import me.blinq.apps.challenge.core.result.asResult
import javax.inject.Inject

fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@HiltViewModel
class InviteViewModel @Inject constructor(
    networkMonitor: NetworkMonitor,
    private val requestInviteUseCase: RequestInviteUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(InviteState())
    val uiState = _uiState.asStateFlow()

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    fun handleEvent(inviteEvent: InviteEvent) {
        when(inviteEvent) {
            is InviteEvent.NameChanged -> {
                val name = inviteEvent.name
                _uiState.update {
                    it.copy(
                        name = name
                    )
                }
                validateName(name)
            }
            is InviteEvent.EmailChanged -> {
                val email = inviteEvent.email
                _uiState.update {
                    it.copy(
                        email = email
                    )
                }
                validateEmail(email)
            }
            is InviteEvent.ConfirmEmailChanged -> {
                val email = inviteEvent.email
                _uiState.update {
                    it.copy(
                        confirmEmail = email,
                    )
                }
                validateConfirmEmail(email)
            }
            is InviteEvent.ErrorDismissed -> {
                _uiState.update {
                    it.copy(error = null)
                }
            }
            is InviteEvent.Send -> {
                sendInvite()
            }
            is InviteEvent.OnNavigateToCongrats -> _uiState.update {
                it.copy(
                    isRegistered = false
                )
            }
        }
    }

    private fun validateName(name: String?): Boolean {
        val valid = name?.let { it.length > 3 } ?: false
        _uiState.update {
            it.copy(
                isNameError = valid.not()
            )
        }.also {
            return valid
        }
    }

    private fun validateEmail(email: String?): Boolean {
        val valid = email?.let { isEmailValid(it) } ?: false
        _uiState.update {
            it.copy(
                isEmailError = valid.not()
            )
        }.also {
            return valid
        }
    }

    private fun validateConfirmEmail(email: String?): Boolean {
        val valid = email?.let { _uiState.value.email == it } ?: false
        _uiState.update {
            it.copy(
                isConfirmEmailError = valid.not()
            )
        }.also {
            return valid
        }
    }

    private fun validate() {
        validateName(_uiState.value.name)
        validateEmail(_uiState.value.email)
        validateConfirmEmail(_uiState.value.confirmEmail)
    }

    private fun sendInvite() {
        viewModelScope.launch {
            if (_uiState.value.isFormValid()) {
                requestInviteUseCase(_uiState.value.name!!, _uiState.value.email!!)
                    .asResult()
                    .collectLatest { result ->
                        when (result) {
                            is Result.Loading -> _uiState.update {
                                it.copy(isLoading = true)
                            }
                            is Result.Success -> _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    isRegistered = true
                                )
                            }
                            is Result.Error -> {
                                val error = when (val exception = result.exception) {
                                    is ApiResponseException -> {
                                        exception.message
                                    }
                                    else -> "Something went wrong!"
                                }
                                _uiState.update {
                                    it.copy(
                                        isLoading = false,
                                        error = error
                                    )
                                }
                            }
                        }
                    }
            } else {
                validate()
            }
        }
    }
}

sealed interface InviteEvent {
    class NameChanged(val name: String) : InviteEvent
    class EmailChanged(val email: String) : InviteEvent
    class ConfirmEmailChanged(val email: String) : InviteEvent
    object ErrorDismissed : InviteEvent
    object Send : InviteEvent

    object OnNavigateToCongrats : InviteEvent
}

data class InviteState(
    val name: String? = null,
    val isNameError: Boolean = false,
    val email: String? = null,
    val isEmailError: Boolean = false,
    val confirmEmail: String? = null,
    val isConfirmEmailError: Boolean = false,
    val isLoading: Boolean = false,
    val isRegistered: Boolean = false,
    val error: String? = null
) {
    fun isFormValid(): Boolean {
        return (email.isNullOrEmpty() || name.isNullOrEmpty() || email != confirmEmail ||
                isEmailError || isConfirmEmailError || isNameError).not()
    }
}
