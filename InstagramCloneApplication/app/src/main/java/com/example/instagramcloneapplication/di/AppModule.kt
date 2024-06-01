package com.example.instagramcloneapplication.di

import com.example.instagramcloneapplication.internal.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePostRepository(): PostRepository {
        return PostRepository()
    }
}
