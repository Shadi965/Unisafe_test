package ru.unisafe.data.auth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.unisafe.data.auth.AuthAPI
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthAPIModule {

    @Provides
    @Singleton
    fun provideAuthAPI(retrofit: Retrofit): AuthAPI {
        return retrofit.create(AuthAPI::class.java)
    }

}