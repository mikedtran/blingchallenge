package me.blinq.apps.challenge.feature.invite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import me.blinq.apps.challenge.feature.invite.InviteRoute

const val inviteNavigationRoute = "invite"

fun NavController.navigateToInvite(navOptions: NavOptions? = null) {
    this.navigate(inviteNavigationRoute, navOptions)
}

fun NavGraphBuilder.inviteScreen(
    onCloseClick: () -> Unit,
    navigateToCongrats: () -> Unit
) {
    composable(
        route = inviteNavigationRoute,
    ) {
        InviteRoute(onCloseClick, navigateToCongrats)
    }
}
