package com.example.search.data.di

import com.example.search.data.dataSource.DetailSearchDataSource
import com.example.search.domain.repository.DetailSearchRepository
import com.example.search.domain.repository.SearchRepository
import com.example.search.data.dataSource.SearchDataSource
import com.example.search.data.repository.DetailSearchRepositoryImpl
import com.example.search.data.repository.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SearchRepositoryModule {

    @Provides
    fun provideSearchRepository(
        dataSource: SearchDataSource
    ): SearchRepository =
        SearchRepositoryImpl(dataSource = dataSource)

    @Provides
    fun provideDetailSearchRepository(
        dataSource: DetailSearchDataSource
    ): DetailSearchRepository =
        DetailSearchRepositoryImpl(dataSource = dataSource)
}