package com.example.recent_seen.data.di

import com.example.domain.repository.RecentSeenRepository
import com.example.recent_seen.data.repository.RecentSeenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RecentSeenRepositoryModule {

    @Binds
    fun provideRecentSeenRepository(
        impl: RecentSeenRepositoryImpl
    ): RecentSeenRepository
}