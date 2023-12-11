package ru.unisafe.data.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.data.auth.source.AuthSource
import ru.unisafe.data.auth.source.AuthSourceRetrofitImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthSourceModule {

    @Binds
    fun bindAuthSource(
        authSource: AuthSourceRetrofitImpl
    ): AuthSource
}