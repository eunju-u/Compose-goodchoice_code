package com.example.recent_seen.data.di

import com.example.domain.repository.RecentSeenRepository
import com.example.recent_seen.data.dataSource.RecentSeenDataSource
import com.example.recent_seen.data.repository.RecentSeenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RecentSeenRepositoryModule {

    @Provides
    fun provideRecentSeenRepository(
        dataSource: RecentSeenDataSource
    ): RecentSeenRepository =
        RecentSeenRepositoryImpl(dataSource = dataSource)

}