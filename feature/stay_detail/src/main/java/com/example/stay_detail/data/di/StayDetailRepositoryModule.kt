package com.example.stay_detail.data.di

import com.example.stay_detail.data.repository.StayDetailRepositoryImpl
import com.example.stay_detail.domain.repository.StayDetailRepository
import com.example.stay_detail.data.dataSource.StayDetailDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object StayDetailRepositoryModule {
    @Provides
    fun provideStayDetailRepository(
        dataSource: StayDetailDataSource
    ): StayDetailRepository =
        StayDetailRepositoryImpl(dataSource = dataSource)

}