package me.blinq.apps.challenge.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import me.blinq.apps.challenge.designsystem.theme.BlinqTheme
import me.blinq.apps.challenge.navigation.BlinqNavHost

@OptIn(ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun BlinqApp(
    windowSizeClass: WindowSizeClass,
    appState: BlinqAppState = rememberBlinqAppState(windowSizeClass)
) {
    BlinqTheme {
        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
        ) { padding ->
            BlinqNavHost(
                navController = appState.navController,
                onBackClick = appState::onBackClick,
                onCloseClick = appState::onCloseClick,
                modifier = Modifier
                    .padding(padding)
                    .consumedWindowInsets(padding)
            )
        }
    }
}