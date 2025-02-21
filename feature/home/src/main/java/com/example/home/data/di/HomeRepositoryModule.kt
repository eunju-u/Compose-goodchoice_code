package com.example.home.data.di

import com.example.home.data.repository.HomeRepositoryImpl
import com.example.home.domain.repository.HomeRepository
import com.example.home.data.dataSource.HomeDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HomeRepositoryModule {
    @Provides
    fun provideHomeRepository(
        dataSource: HomeDataSource
    ): HomeRepository = HomeRepositoryImpl(dataSource = dataSource)
}