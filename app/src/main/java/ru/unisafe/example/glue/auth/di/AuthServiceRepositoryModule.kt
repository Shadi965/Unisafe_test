package ru.unisafe.example.glue.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.auth.domain.repositories.AuthServiceRepository
import ru.unisafe.example.glue.auth.AuthServiceIntermediateRepository

@Module
@InstallIn(SingletonComponent::class)
interface AuthServiceRepositoryModule {

    @Binds
    fun bindAuthServiceRepository(
        authServiceRepository: AuthServiceIntermediateRepository
    ): AuthServiceRepository

}