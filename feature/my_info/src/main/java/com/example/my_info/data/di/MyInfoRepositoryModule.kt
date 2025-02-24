package com.example.my_info.data.di

import com.example.my_info.domain.repository.MyInfoRepository
import com.example.my_info.data.repository.MyInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface MyInfoRepositoryModule {

    @Binds
    fun provideMyInfoRepository(
        dataSource: MyInfoRepositoryImpl
    ): MyInfoRepository
}