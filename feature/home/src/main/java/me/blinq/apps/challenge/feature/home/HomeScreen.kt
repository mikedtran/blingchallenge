package me.blinq.apps.challenge.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun HomeRoute(
    navigateToInvite: () -> Unit,
    navigateToCongrats: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val isRegistered by viewModel.isRegistered.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isUnRegistered) {
        DisposableEffect(Unit) {
            navigateToCongrats()
            onDispose {
                viewModel.handleEvent(HomeEvent.OnNavigateToCongrats)
            }
        }
    }

    HomeScreen(
        modifier = modifier,
        navigateToInvite = navigateToInvite,
        isRegistered = isRegistered,
        state = uiState,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
internal fun HomeScreen(
    navigateToInvite: () -> Unit,
    modifier: Modifier = Modifier,
    isRegistered: Boolean,
    state: HomeUiState,
    handleEvent: (HomeEvent) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp),
                painter = painterResource(id = R.drawable.sandwiched),
                contentDescription = "sandwiched"
            )
            Spacer(modifier = Modifier.height(50.dp))
            if (isRegistered) {
                Text(
                    text = stringResource(id = R.string.desc_you_already_registered),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(onClick = {
                    handleEvent(HomeEvent.CancelInvite)
                }) {
                    Text(text = stringResource(id = R.string.button_cancel_invite))
                }
            } else {
                Button(onClick = navigateToInvite) {
                    Text(text = stringResource(id = R.string.button_request_invite))
                }
            }
        }
        state.userMessage?.let {
            CancelInviteDialog(
                modifier = Modifier,
                stringResource(id = it),
                dismissCancelInvite = {
                    handleEvent(HomeEvent.CancelInviteDismiss)
                },
                confirmCancelInvite = {
                    handleEvent(HomeEvent.CancelInviteConfirm)
                }
            )
        }
    }
}