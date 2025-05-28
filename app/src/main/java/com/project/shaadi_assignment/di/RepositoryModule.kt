package com.project.shaadi_assignment.di

import com.project.shaadi_assignment.data.db.UserProfileDao
import com.project.shaadi_assignment.data.network.RandomUserApi
import com.project.shaadi_assignment.data.repository.UserProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserProfileRepository(
        userProfileDao: UserProfileDao,
        randomUserApi: RandomUserApi
    ): UserProfileRepository {
        return UserProfileRepository(userProfileDao, randomUserApi)
    }
} 