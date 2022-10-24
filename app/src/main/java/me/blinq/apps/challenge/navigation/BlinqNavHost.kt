package me.blinq.apps.challenge.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import me.blinq.apps.challenge.feature.congrats.CongratsType
import me.blinq.apps.challenge.feature.congrats.navigation.congratsScreen
import me.blinq.apps.challenge.feature.congrats.navigation.navigateToCongrats
import me.blinq.apps.challenge.feature.home.navigation.homeGraph
import me.blinq.apps.challenge.feature.home.navigation.homeGraphRoutePattern
import me.blinq.apps.challenge.feature.invite.navigation.inviteScreen
import me.blinq.apps.challenge.feature.invite.navigation.navigateToInvite

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun BlinqNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onBackClick: () -> Unit = {},
    onCloseClick: () -> Unit,
    startDestination: String = homeGraphRoutePattern
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph(
            navigateToInvite = {
                navController.navigateToInvite()
            },
            navigateToCongrats = {
                navController.navigateToCongrats(CongratsType.Unregistered)
            },
            nestedGraphs = {
                inviteScreen(onBackClick) {
                    navController.navigateToCongrats(CongratsType.Registered)
                }
                congratsScreen(onCloseClick)
            }
        )
    }
}