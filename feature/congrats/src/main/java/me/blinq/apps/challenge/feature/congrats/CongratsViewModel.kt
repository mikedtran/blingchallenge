package me.blinq.apps.challenge.feature.congrats

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import me.blinq.apps.challenge.core.decoder.StringDecoder
import me.blinq.apps.challenge.feature.congrats.navigation.CongratsArgs
import javax.inject.Inject

enum class CongratsType {
    Registered,
    Unregistered
}

@HiltViewModel
class CongratsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    stringDecoder: StringDecoder,
) : ViewModel() {
    private val congratsArgs: CongratsArgs = CongratsArgs(savedStateHandle, stringDecoder)

    val congratsType = snapshotFlow {
        congratsArgs.congratsType
    }.map {
        CongratsType.valueOf(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CongratsType.Registered
    )
}