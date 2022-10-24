package me.blinq.apps.challenge.core.decoder.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.blinq.apps.challenge.core.decoder.StringDecoder
import me.blinq.apps.challenge.core.decoder.UriDecoder

@Module
@InstallIn(SingletonComponent::class)
abstract class StringDecoderModule {
    @Binds
    abstract fun bindStringDecoder(uriDecoder: UriDecoder): StringDecoder
}
