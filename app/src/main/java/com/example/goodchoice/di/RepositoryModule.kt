package com.example.goodchoice.di

import com.example.goodchoice.data.dataSource.RecentSeenDataSource
import com.example.goodchoice.data.repository.RecentSeenRepositoryImpl
import com.example.goodchoice.domain.repository.RecentSeenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun provideRecentSeenRepository(
        dataSource: RecentSeenDataSource
    ): RecentSeenRepository = RecentSeenRepositoryImpl(dataSource = dataSource)
}