package com.github.repos.di

import com.github.repos.data.framework.remote.RepoService
import com.github.repos.data.repository.RepoRepositoryImpl
import com.github.repos.domain.repository.RepoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author omerakkus
 * @since 07/28/2022
 */

@Module
@InstallIn(SingletonComponent::class)
object UserRepoModule {

    @Provides
    @Singleton
    fun provideReposApi(): RepoService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RepoService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: RepoService): RepoRepository {
        return RepoRepositoryImpl(api= api)
    }
}