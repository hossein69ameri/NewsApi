package com.ameri.data.di

import com.ameri.data.repository.NewsRepositoryImpl
import com.ameri.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl,
    ): NewsRepository
}
