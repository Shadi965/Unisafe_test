package ru.unisafe.example.navigation.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.auth.presentation.AuthRouter
import ru.unisafe.example.navigation.auth.AuthRouterImpl

@Module
@InstallIn(SingletonComponent::class)
interface AuthRouterModule {

    @Binds
    fun bindAuthRouter(
        authRouter: AuthRouterImpl
    ): AuthRouter

}