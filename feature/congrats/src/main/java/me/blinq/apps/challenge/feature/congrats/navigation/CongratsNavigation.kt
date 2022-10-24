package me.blinq.apps.challenge.feature.congrats.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.composable
import me.blinq.apps.challenge.core.decoder.StringDecoder
import me.blinq.apps.challenge.feature.congrats.CongratsRoute
import me.blinq.apps.challenge.feature.congrats.CongratsType

internal const val congratsTypeArg = "congratsType"

class CongratsArgs(val congratsType: String) {
    constructor(savedStateHandle: SavedStateHandle, stringDecoder: StringDecoder) :
            this(stringDecoder.decodeString(checkNotNull(savedStateHandle[congratsTypeArg])))
}

fun NavController.navigateToCongrats(congratsType: CongratsType) {
    val encodedString = Uri.encode(congratsType.name)
    this.navigate("congrats_route/$encodedString") {
        popUpTo(graph.findStartDestination().id)
    }
}

fun NavGraphBuilder.congratsScreen(
    onCloseClick: () -> Unit
) {
    composable(
        route = "congrats_route/{$congratsTypeArg}",
        arguments = listOf(
            navArgument(congratsTypeArg) { type = NavType.StringType }
        )
    ) {
        CongratsRoute(onCloseClick)
    }
}
