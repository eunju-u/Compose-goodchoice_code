package com.example.data.di

import com.example.data.dataSource.*
import com.example.data.repository.*
import com.example.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun provideHomeRepository(
        dataSource: HomeDataSource
    ): HomeRepository = HomeRepositoryImpl(dataSource = dataSource)

    @Provides
    fun provideLikeRepository(
        dataSource: LikeDataSource
    ): LikeRepository = LikeRepositoryImpl(dataSource = dataSource)
}