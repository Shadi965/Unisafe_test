package ru.unisafe.example.glue.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.auth.domain.repositories.KeyRepository
import ru.unisafe.example.glue.auth.AuthKeyIntermediateRepository

@Module
@InstallIn(SingletonComponent::class)
interface AuthKeyIntermediateRepositoryModule {

    @Binds
    fun bindAuthKeyIntermediateRepository(
        keyRepository: AuthKeyIntermediateRepository
    ): KeyRepository

}