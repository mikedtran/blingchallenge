package me.blinq.apps.challenge.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import me.blinq.apps.challenge.feature.home.HomeRoute

const val homeGraphRoutePattern = "home_graph"
private const val homeRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeGraph(
    navigateToInvite: () -> Unit,
    navigateToCongrats: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = homeGraphRoutePattern,
        startDestination = homeRoute,
    ) {
        composable(route = homeRoute) {
            HomeRoute(
                navigateToInvite = navigateToInvite,
                navigateToCongrats = navigateToCongrats
            )
        }
        nestedGraphs()
    }
}
