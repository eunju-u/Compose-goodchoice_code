package com.example.around.data.di

import com.example.around.data.dataSource.AroundDataSource
import com.example.around.data.repository.AroundRepositoryImpl
import com.example.around.domain.repository.AroundRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AroundRepositoryModule {
    @Provides
    fun provideAroundRepository(
        dataSource: AroundDataSource
    ): AroundRepository =
        AroundRepositoryImpl(dataSource = dataSource)

}