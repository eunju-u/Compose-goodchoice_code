package com.example.alarm.data.di

import com.example.alarm.data.repository.AlarmRepositoryImpl
import com.example.alarm.domain.repository.AlarmRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AlarmRepositoryModule {

    @Binds
    fun provideAlarmRepository(
        impl: AlarmRepositoryImpl
    ): AlarmRepository
}