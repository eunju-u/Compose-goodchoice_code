package com.example.search.data.di

import com.example.search.domain.repository.DetailSearchRepository
import com.example.search.domain.repository.SearchRepository
import com.example.search.data.repository.DetailSearchRepositoryImpl
import com.example.search.data.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface SearchRepositoryModule {

    @Binds
    fun provideSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    fun provideDetailSearchRepository(
        detailSearchRepositoryImpl: DetailSearchRepositoryImpl
    ): DetailSearchRepository
}