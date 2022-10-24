package me.blinq.apps.challenge.feature.invite

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.blinq.apps.challenge.feature.invitation.R

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun InviteRoute(
    onCloseClick: () -> Unit,
    navigateToCongrats: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InviteViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(
        minActiveState = Lifecycle.State.STARTED
    )
    val isOffline by viewModel.isOffline.collectAsStateWithLifecycle()

    if (uiState.isRegistered) {
        DisposableEffect(Unit) {
            navigateToCongrats()

            onDispose {
                viewModel.handleEvent(InviteEvent.OnNavigateToCongrats)
            }
        }
    }

    InviteScreen(
        onCloseClick = onCloseClick,
        modifier = modifier,
        isOffline = isOffline,
        state = uiState,
        handleEvent = viewModel::handleEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@VisibleForTesting
@Composable
internal fun InviteScreen(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    isOffline: Boolean,
    state: InviteState,
    handleEvent: (event: InviteEvent) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        val notConnected = stringResource(R.string.invite_not_connected)
        LaunchedEffect(isOffline) {
            if (isOffline) snackbarHostState.showSnackbar(
                message = notConnected,
                duration = SnackbarDuration.Indefinite
            )
        }
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                InviteForm(
                    modifier = Modifier.fillMaxSize(),
                    name = state.name,
                    isNameError = state.isNameError,
                    onNameChanged = { name ->
                        handleEvent(InviteEvent.NameChanged(name))
                    },
                    email = state.email,
                    isEmailError = state.isEmailError,
                    onEmailChanged = { email ->
                        handleEvent(
                            InviteEvent.EmailChanged(email)
                        )
                    },
                    confirmEmail = state.confirmEmail,
                    onConfirmEmailChanged = { email ->
                        handleEvent(
                            InviteEvent.ConfirmEmailChanged(email)
                        )
                    },
                    isConfirmEmailError = state.isConfirmEmailError,
                    onSend = {
                        handleEvent(InviteEvent.Send)
                    }
                )
                state.error?.let { error ->
                    InviteErrorDialog(
                        error = error,
                        dismissError = {
                            handleEvent(InviteEvent.ErrorDismissed)
                        }
                    )
                }
            }
        }
    }
}

