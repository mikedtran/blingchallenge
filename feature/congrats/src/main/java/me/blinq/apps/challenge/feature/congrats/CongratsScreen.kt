package me.blinq.apps.challenge.feature.congrats

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*

@Composable
fun CongratsRoute(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CongratsViewModel = hiltViewModel(),
) {
    val congratsType by viewModel.congratsType.collectAsState()

    CongratsScreen(
        onCloseClick = onCloseClick,
        modifier = modifier,
        type = congratsType
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CongratsScreen(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: CongratsType
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(
        if (type == CongratsType.Registered) R.raw.registered else R.raw.success
    ))

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.title_congrats))
                },
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
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition,
                modifier = modifier
                    .width(250.dp)
                    .height(250.dp),
                isPlaying = true,
                iterations = LottieConstants.IterateForever,
                clipToCompositionBounds = true
            )
        }
    }
}