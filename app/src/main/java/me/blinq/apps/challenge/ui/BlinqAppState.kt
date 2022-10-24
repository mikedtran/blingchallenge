package me.blinq.apps.challenge.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

@Composable
fun rememberBlinqAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController()
): BlinqAppState {
    return remember(navController, windowSizeClass) {
        BlinqAppState(navController, windowSizeClass)
    }
}

@Stable
class BlinqAppState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    fun onBackClick() {
        navController.popBackStack()
    }

    fun onCloseClick() {
        navController.popBackStack()
    }
}