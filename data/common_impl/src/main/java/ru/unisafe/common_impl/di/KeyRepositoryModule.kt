package ru.unisafe.common_impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.common_impl.KeyDataRepositorySharedPreferencesImpl
import ru.unisafe.data_common.auth.KeyDataRepository

@Module
@InstallIn(SingletonComponent::class)
interface KeyRepositoryModule {

    @Binds
    fun bindKeyDataRepository(
        keyDataRepository: KeyDataRepositorySharedPreferencesImpl
    ): KeyDataRepository

}