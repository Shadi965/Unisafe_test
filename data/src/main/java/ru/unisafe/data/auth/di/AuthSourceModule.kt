package ru.unisafe.data.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.data.auth.AuthSource
import ru.unisafe.data.retrofit.AuthSourceRetrofitImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthSourceModule {

    @Binds
    fun bindAuthSource(
        authSource: AuthSourceRetrofitImpl
    ): AuthSource
}