package com.example.filter.data.di

import com.example.filter.domain.repository.FilterRepository
import com.example.filter.data.dataSource.FilterDataSource
import com.example.filter.data.repository.FilterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FilterRepositoryModule {

    @Provides
    fun provideFilterRepository(
        dataSource: FilterDataSource
    ): FilterRepository =
        FilterRepositoryImpl(dataSource = dataSource)
}