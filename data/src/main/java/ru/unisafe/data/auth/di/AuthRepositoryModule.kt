package ru.unisafe.data.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.data.AuthDataRepositoryImpl
import ru.unisafe.data.auth.AuthDataRepository

@Module
@InstallIn(SingletonComponent::class)
interface AuthRepositoryModule {

    @Binds
    fun bindAuthDataRepository(
        authDataRepository: AuthDataRepositoryImpl
    ): AuthDataRepository

}