package me.blinq.apps.challenge.core.decoder

interface StringDecoder {
    fun decodeString(encodedString: String): String
}