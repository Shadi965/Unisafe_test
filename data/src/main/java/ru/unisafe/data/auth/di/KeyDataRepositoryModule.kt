package ru.unisafe.data.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.data.KeyDataRepository
import ru.unisafe.data.auth.KeyDataRepositorySharedPreferencesImpl

@Module
@InstallIn(SingletonComponent::class)
interface KeyDataRepositoryModule {

    @Binds
    fun bindKeyDataRepository(
        keyDataRepository: KeyDataRepositorySharedPreferencesImpl
    ): KeyDataRepository

}