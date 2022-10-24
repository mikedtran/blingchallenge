package me.blinq.apps.challenge.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val blinqDispatcher: BlinqDispatchers)

enum class BlinqDispatchers {
    IO
}
