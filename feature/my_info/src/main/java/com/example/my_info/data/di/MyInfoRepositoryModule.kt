package com.example.my_info.data.di

import com.example.my_info.domain.repository.MyInfoRepository
import com.example.my_info.data.dataSource.MyInfoDataSource
import com.example.my_info.data.repository.MyInfoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MyInfoRepositoryModule {

    @Provides
    fun provideMyInfoRepository(
        dataSource: MyInfoDataSource
    ): MyInfoRepository =
        MyInfoRepositoryImpl(dataSource = dataSource)

}