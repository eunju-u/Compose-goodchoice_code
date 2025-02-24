package com.example.stay_detail.data.di

import com.example.stay_detail.domain.repository.StayDetailRepository
import com.example.stay_detail.data.repository.StayDetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface StayDetailRepositoryModule {

    @Binds
    fun provideStayDetailRepository(
        repositoryImpl: StayDetailRepositoryImpl
    ): StayDetailRepository
}
