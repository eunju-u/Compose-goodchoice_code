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
    fun provideSearchRepository(
        dataSource: SearchDataSource
    ): SearchRepository = SearchRepositoryImpl(dataSource = dataSource)

    @Provides
    fun provideAroundRepository(
        dataSource: AroundDataSource
    ): AroundRepository = AroundRepositoryImpl(dataSource = dataSource)

    @Provides
    fun provideLikeRepository(
        dataSource: LikeDataSource
    ): LikeRepository = LikeRepositoryImpl(dataSource = dataSource)

    @Provides
    fun provideMyInfoRepository(
        dataSource: MyInfoDataSource
    ): MyInfoRepository = MyInfoRepositoryImpl(dataSource = dataSource)

    @Provides
    fun provideFilterRepository(
        dataSource: FilterDataSource
    ): FilterRepository = FilterRepositoryImpl(dataSource = dataSource)


    @Provides
    fun provideRecentSeenRepository(
        dataSource: RecentSeenDataSource
    ): RecentSeenRepository = RecentSeenRepositoryImpl(dataSource = dataSource)

    @Provides
    fun provideStayDetailRepository(
        dataSource: StayDetailDataSource
    ): StayDetailRepository = StayDetailRepositoryImpl(dataSource = dataSource)

    @Provides
    fun provideDetailSearchRepository(
        dataSource: DetailSearchDataSource
    ): DetailSearchRepository = DetailSearchRepositoryImpl(dataSource = dataSource)
}