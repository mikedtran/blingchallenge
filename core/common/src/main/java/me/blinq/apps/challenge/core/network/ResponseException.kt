package me.blinq.apps.challenge.core.network

import java.io.IOException

/**
 * Base for default response exceptions.
 */
open class ResponseException(
    override val message: String?
) : IOException()


class ApiResponseException(
    override val message: String?
) : ResponseException(message)
